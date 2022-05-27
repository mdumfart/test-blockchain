package com.example.blockchain.testblockchain.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record Block(int index, Instant timestamp, List<Transaction> transactions, int proof, String previousHash) implements Serializable {

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timestamp=" + timestamp +
                ", transactions=" + transactions +
                ", proof='" + proof + '\'' +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }
}
