package com.example.blockchain.testblockchain.blockchain;

import com.example.blockchain.testblockchain.domain.Block;
import com.example.blockchain.testblockchain.domain.Transaction;
import com.example.blockchain.testblockchain.domain.dtos.BlockchainDto;
import com.example.blockchain.testblockchain.domain.dtos.BlockchainResponse;
import com.example.blockchain.testblockchain.util.BlockHasher;
import com.example.blockchain.testblockchain.util.BlockHasherImpl;
import com.example.blockchain.testblockchain.util.ProofOfWork;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Scope("singleton")
@Service
public final class Blockchain {
    private List<Block> chain;
    private final List<Transaction> currentTransactions;
    private final BlockHasher hasher;
    private final ProofOfWork proofOfWork;
    private final Set<String> nodes;

    public Blockchain(ProofOfWork proofOfWork) {
        this.proofOfWork = proofOfWork;
        this.nodes = new HashSet<>();
        this.chain = new LinkedList<>();
        this.currentTransactions = new LinkedList<>();
        this.hasher = new BlockHasherImpl();

        // Create genesis block
        createNewBlock(100, "1");
    }

    public Block createNewBlock(int proof, @Nullable String previousHash) {
        var hash = previousHash != null ? previousHash : hasher.hash(chain.get(chain.size() - 1));
        var block = new Block(chain.size(), Instant.now(), List.copyOf(currentTransactions), proof, hash);

        // Reset transactions
        currentTransactions.clear();
        chain.add(block);

        return block;
    }

    public int createNewTransaction(String sender, String recipient, double amount) {
        var transaction = new Transaction(sender, recipient, amount);

        currentTransactions.add(transaction);

        // TODO: Check if this works
        return chain.size();
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public int getLength() {
        return chain.size();
    }

    public boolean isValidChain(List<Block> chain) {
        var lastBlock = chain.get(chain.size() - 1);
        var currentIndex = 1;

        while (currentIndex < chain.size()) {
            var block = chain.get(currentIndex);

            if (block.previousHash().equals(hasher.hash(lastBlock))) {
                return false;
            }

            if (!proofOfWork.isValidProof(lastBlock.proof(), block.proof())) {
                return false;
            }

            lastBlock = block;
            currentIndex++;
        }

        return true;
    }

    public boolean resolveConflicts() {
        final RestTemplate restTemplate = new RestTemplate();


        var neighbors = nodes;
        List<Block> newChain = null;
        var maxLength = this.chain.size();

        for (final String node : neighbors) {
            final String url = String.format("http://%s/chain", node);
            var response = restTemplate.getForEntity(url, BlockchainResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                var body = response.getBody();

                if (body.length() > maxLength && isValidChain(body.blockchain().chain())) {
                    maxLength = body.length();
                    newChain = body.blockchain().chain();
                }
            }
        }

        if (newChain != null) {
            this.chain = newChain;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "chain=" + chain +
                '}';
    }

    public BlockchainDto toDto() {
        return new BlockchainDto(this.chain, this.currentTransactions);
    }

    public void registerNodes(List<String> nodes) {
        this.nodes.addAll(nodes);
    }

    public List<String> getNodes() {
        return nodes.stream().toList();
    }

    public List<Block> getChain() {
        return chain;
    }
}
