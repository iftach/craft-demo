package com.idadon.craft.encoder;

import com.idadon.craft.Constants;
import com.idadon.craft.utils.ReaderUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.LinkedList;

public class FileHashEncoder implements Encoder {
    public String encode(RandomAccessFile reader, OutputStream writer) throws IOException {

        if (reader == null || writer == null) {
            throw new IllegalArgumentException("Encoder parameter can't be null");
        }

        LinkedList<byte[]> blockList = new LinkedList<>();
        long fileLength = reader.length();
        int bytesToRead = (int) fileLength % Constants.CHUNK_SIZE;
        if(bytesToRead == 0) {
            bytesToRead = Constants.CHUNK_SIZE;
        }
        byte[] hash = null;
        byte[] chunk;
        for (long position = fileLength - bytesToRead; position >= 0; position -= bytesToRead) {
            chunk = ReaderUtils.read(reader, position, bytesToRead);
            if (hash == null) {
                hash = DigestUtils.getSha256Digest().digest(chunk);
                blockList.addFirst(chunk);
            } else {
                byte[] block = ArrayUtils.addAll(chunk, hash);
                blockList.addFirst(block);
                hash = DigestUtils.getSha256Digest().digest(block);
            }
            bytesToRead = Constants.CHUNK_SIZE;
        }

        for (byte[] block : blockList) {
            writer.write(block);
        }
        return DigestUtils.sha256Hex(blockList.getFirst());
    }

}
