package com.example.blockchain.testblockchain.blockchain;

import java.util.List;

public class Blockchain {
    private final List<String> chain;
    private final List<String> currentTransactions;

    public Blockchain() {
        this.chain = null;
        this.currentTransactions = null;
    }
}
