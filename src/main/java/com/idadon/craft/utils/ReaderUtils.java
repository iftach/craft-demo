package com.idadon.craft.utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReaderUtils {
    public static byte[] read(RandomAccessFile reader, long pos, int length) throws IOException {
        if(length <= 0) {
            throw new IllegalArgumentException("Read size have to be greater than zero");
        }
        byte[] data;
        data = new byte[length];
        reader.seek(pos);
        reader.read(data);
        return data;
    }
}
