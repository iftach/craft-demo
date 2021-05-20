package com.idadon.craft.decoder;

import com.idadon.craft.Constants;
import com.idadon.craft.exception.InvalidHashException;
import com.idadon.craft.utils.ReaderUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;

public class FileHashDecoder implements Decoder {

    private static final String INVALID_HASH_EXCEPTION_MSG = "Invalid hash exception";

    public boolean decode(RandomAccessFile reader, OutputStream writer, String h0)
            throws IOException, IllegalArgumentException {
        if( reader == null || writer == null || h0 == null) {
            throw new IllegalArgumentException("Decoder parameter can't be null");
        }
        String hash = h0;
        long pos = 0;
        byte[] blockHash;
        byte[] data;
        long size = reader.length();
        try {
            for (; pos + Constants.BLOCK_SIZE <= size; pos += Constants.BLOCK_SIZE) {
                data = ReaderUtils.read(reader, pos, Constants.CHUNK_SIZE);
                blockHash = ReaderUtils.read(reader, pos + Constants.CHUNK_SIZE, Constants.HASH_SIZE);
                validateHash(ArrayUtils.addAll(data, blockHash), hash);
                writer.write(data);
                hash = Hex.encodeHexString(blockHash);
            }
            if (pos < size) {
                int readSize = (int) (size - pos);
                data = ReaderUtils.read(reader, pos, readSize);
                validateHash(data, hash);
                writer.write(data);
            }
        } catch (InvalidHashException e) {
            System.out.println(INVALID_HASH_EXCEPTION_MSG);
            return false;
        }
        return true;
    }

    private void validateHash(byte[] data, String hash) throws InvalidHashException {
        if(!DigestUtils.sha256Hex(data).equals(hash)) {
            throw new InvalidHashException();
        }
    }
}
