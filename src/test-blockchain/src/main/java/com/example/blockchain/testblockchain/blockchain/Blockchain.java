package com.example.blockchain.testblockchain.blockchain;

import com.example.blockchain.testblockchain.domain.Block;
import com.example.blockchain.testblockchain.domain.Transaction;
import com.example.blockchain.testblockchain.util.BlockHasher;
import com.example.blockchain.testblockchain.util.BlockHasherImpl;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public final class Blockchain {
    private final List<Block> chain;
    private final List<Transaction> currentTransactions;
    private final BlockHasher hasher;

    public Blockchain() {
        this.chain = new LinkedList<>();
        this.currentTransactions = new LinkedList<>();
        this.hasher = new BlockHasherImpl();

        // Create genesis block
        createNewBlock("100", "1");
    }

    public Block createNewBlock(String proof, @Nullable String previousHash) {
        var hash = previousHash != null ? previousHash : hasher.hash(chain.get(chain.size() - 1));
        var block = new Block(chain.size(), Instant.now(), currentTransactions, proof, hash);

        // Reset transactions
        currentTransactions.clear();
        chain.add(block);

        return block;
    }

    public double createNewTransaction(String sender, String recipient, double amount) {
        var transaction = new Transaction(sender, recipient, amount);

        currentTransactions.add(transaction);

        // TODO: Check if this works
        return chain.size();
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }
}
