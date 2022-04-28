package com.example.blockchain.testblockchain.util;

import com.example.blockchain.testblockchain.domain.Block;

public interface BlockHasher {
    String hash(Block block);
}
