package com.idadon.craft.decoder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public interface Decoder {
    /**
     * @param reader encoded file stream
     * @param writer stream to write decoded output
     * @param h0 SHA256 hash of encoded file first block
     * @return true if encoded file is valid
     * @throws IOException
     */
    boolean decode(RandomAccessFile reader, OutputStream writer, String h0) throws IOException;
}
