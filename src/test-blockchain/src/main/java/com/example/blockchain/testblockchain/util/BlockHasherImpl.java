package com.example.blockchain.testblockchain.util;

import com.example.blockchain.testblockchain.domain.Block;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.nio.charset.StandardCharsets;

@Service
@Scope("singleton")
public class BlockHasherImpl implements BlockHasher {
    private final Logger logger = LoggerFactory.getLogger(BlockHasherImpl.class);

    @Override
    public String hash(Block block) {
        logger.debug("Attempting to hash: " + block.toString());
        return Hashing.sha256().hashString(block.toString(), StandardCharsets.UTF_8).toString();
    }
}
