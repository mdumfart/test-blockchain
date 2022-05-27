package com.example.blockchain.testblockchain.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Scope("singleton")
@Service
public class NodeIdentifierImpl implements NodeIdentifier {
    private final String value;

    public NodeIdentifierImpl() {
        var uuid = UUID.randomUUID();
        this.value = uuid.toString().replace("-", "");
    }

    @Override
    public String getValue() {
        return value;
    }
}
