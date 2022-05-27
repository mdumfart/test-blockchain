package com.example.blockchain.testblockchain.util;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ProofOfWorkImpl implements ProofOfWork {
    private static final String GUESS_MATCHER = "0000";

    @Override
    public int work(int lastProof) {
        int proof = 0;

        while (!isValidProof(lastProof, proof)) {
            proof++;
        }

        return proof;
    }

    @Override
    public boolean isValidProof(int lastProof, int proof) {
        var guess = String.format("{%d}{%d}", lastProof, proof);
        var hashedGuess = Hashing.sha256().hashString(guess, StandardCharsets.UTF_8).toString();

        return  hashedGuess.substring(0, 4).equals(GUESS_MATCHER);
    }
}
