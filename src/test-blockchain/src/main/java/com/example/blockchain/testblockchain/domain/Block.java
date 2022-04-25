package com.example.blockchain.testblockchain.domain;

import java.time.Instant;
import java.util.List;

public final class Block {
    private final Long index;
    private final Instant timestamp;
    private final List<Transaction> transactions;
    private final String proof;
    private final String previousHash;

    public Block(Long index, Instant timestamp, List<Transaction> transactions, String proof, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.proof = proof;
        this.previousHash = previousHash;
    }

    public Long getIndex() {
        return index;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getProof() {
        return proof;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
