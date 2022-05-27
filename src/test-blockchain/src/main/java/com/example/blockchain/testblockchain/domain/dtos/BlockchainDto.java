package com.example.blockchain.testblockchain.domain.dtos;

import com.example.blockchain.testblockchain.domain.Block;
import com.example.blockchain.testblockchain.domain.Transaction;
import java.util.List;

public record BlockchainDto(List<Block> chain, List<Transaction> currentTransactions) {
}
