package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder {
    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder) { // Component 가 여러개일 때, @Qualifier로 사용하면 된다.

        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
