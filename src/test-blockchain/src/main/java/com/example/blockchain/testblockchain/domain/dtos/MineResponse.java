package com.example.blockchain.testblockchain.domain.dtos;

import com.example.blockchain.testblockchain.domain.Transaction;
import java.util.List;

public record MineResponse(String message, int index, List<Transaction> transactions, int proof, String previousHash) {
}
