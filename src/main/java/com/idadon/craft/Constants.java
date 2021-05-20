package com.idadon.craft;

public final class Constants {

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
    public static final int CHUNK_SIZE = 1024;
    public static final int HASH_SIZE = 32;
    public static final int BLOCK_SIZE = CHUNK_SIZE + HASH_SIZE;
}
