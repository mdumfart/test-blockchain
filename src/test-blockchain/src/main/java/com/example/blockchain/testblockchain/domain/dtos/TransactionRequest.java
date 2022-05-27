package com.example.blockchain.testblockchain.domain.dtos;

public record TransactionRequest(String sender, String recipient, int amount) {
}
