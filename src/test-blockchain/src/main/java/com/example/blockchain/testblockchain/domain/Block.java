package com.example.blockchain.testblockchain.domain;

import java.time.Instant;
import java.util.List;

public record Block(int index, Instant timestamp, List<Transaction> transactions, String proof, String previousHash) {
}
