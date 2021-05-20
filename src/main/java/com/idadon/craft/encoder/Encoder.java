package com.idadon.craft.encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public interface Encoder {
    /**
     * @param reader stream to encode
     * @param writer stream to write encoded output
     * @return SHA256 hash of encoded stream first block
     * @throws IOException
     */
    String encode(RandomAccessFile reader, OutputStream writer ) throws IOException;
}
