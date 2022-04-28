package com.example.blockchain.testblockchain.blockchain;

import com.example.blockchain.testblockchain.domain.Block;
import com.example.blockchain.testblockchain.domain.Transaction;

import java.util.LinkedList;
import java.util.List;

public final class Blockchain {
    private final List<Block> chain;
    private final List<Transaction> currentTransactions;

    public Blockchain() {
        this.chain = new LinkedList<>();
        this.currentTransactions = new LinkedList<>();
    }

    public void createNewBlock(String proof, String previousHash) {

    }

    public double createNewTransaction(String sender, String recipient, double amount) {
        var transaction = new Transaction(sender, recipient, amount);

        currentTransactions.add(transaction);

        return chain.size();
    }

    public Block getLastBlock() {
        return null;
    }
}
