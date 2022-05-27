package com.example.blockchain.testblockchain.api;

import com.example.blockchain.testblockchain.domain.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TestBlockchainApi {
    @PostMapping("transactions/new")
    ResponseEntity<String> newTransaction(@RequestBody() TransactionRequest body);

    @PostMapping("mine")
    ResponseEntity<MineResponse> mineBlock();

    @GetMapping("chain")
    ResponseEntity<BlockchainResponse> getChain();

    @PostMapping("nodes/register")
    ResponseEntity<RegisterNodesResponse> registerNodes(@RequestBody() RegisterNodesRequest body);

    @GetMapping("nodes/resolve")
    ResponseEntity<ConsensusResponse> consensus();
}
