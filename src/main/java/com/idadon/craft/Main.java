package com.idadon.craft;

import com.idadon.craft.decoder.Decoder;
import com.idadon.craft.decoder.FileHashDecoder;
import com.idadon.craft.encoder.Encoder;
import com.idadon.craft.encoder.FileHashEncoder;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String h0;
        Encoder encoder = new FileHashEncoder();
        Decoder decoder = new FileHashDecoder();
        try (
            OutputStream writer = new FileOutputStream("/Users/idadon/craft/src/main/resources/zero-encoded.bin");
            RandomAccessFile reader = new RandomAccessFile("/Users/idadon/craft/src/main/resources/zero.bin", "r")
        ) {
            h0 = encoder.encode(reader, writer);
        }

        try(
            RandomAccessFile reader = new RandomAccessFile("/Users/idadon/craft/src/main/resources/zero-encoded.bin", "r");
            FileOutputStream writer = new FileOutputStream("/Users/idadon/craft/src/main/resources/zero-decoded.bin")
        ) {
            System.out.println(decoder.decode(reader, writer, h0));
        }
    }
}
