package com.github.edumaxsantos.urlshortener.hash;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Primary
@Component
public class FowlerNollVoHash implements Hash {
    private final BigInteger FNV_OFFSET_BASIS = new BigInteger("2166136261");
    private final BigInteger FNV_PRIME = new BigInteger("16777619");
    @Override
    public String hash(String value) {
        BigInteger hsh = FNV_OFFSET_BASIS;

        byte[] data = value.getBytes();

        for (Byte d: data) {
            hsh = hsh.multiply(FNV_PRIME);
            hsh = hsh.xor(new BigInteger(d.toString()));
        }
        int pos = value.length();
        return hsh.toString(16).substring(pos, pos + 6);
    }
}
