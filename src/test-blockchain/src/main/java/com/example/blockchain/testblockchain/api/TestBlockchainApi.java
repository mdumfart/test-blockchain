package com.example.blockchain.testblockchain.api;

import com.example.blockchain.testblockchain.domain.dtos.BlockchainResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface TestBlockchainApi {
    @PostMapping("transactions/new")
    void newTransaction();
    @PostMapping("mine")
    void mineBlock();
    @GetMapping("chain")
    ResponseEntity<BlockchainResponse> getChain();
}
