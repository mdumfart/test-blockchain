package com.example.blockchain.testblockchain.util;

public interface ProofOfWork {
    int work(int lastProof);
    boolean isValidProof(int lastProof, int proof);
}
