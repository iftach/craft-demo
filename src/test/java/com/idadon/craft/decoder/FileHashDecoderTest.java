package com.idadon.craft.decoder;

import com.idadon.craft.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;

import static org.mockito.Mockito.*;

public class FileHashDecoderTest {

    private FileHashDecoder decoder;

    @BeforeTest
    private void setUp() {
        decoder = new FileHashDecoder();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecode_negativeReaderNull() throws Exception {
        decoder.decode(null, mock(OutputStream.class), "mockH0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecode_negativeWriterNull() throws Exception {
        decoder.decode(mock(RandomAccessFile.class), null, "mockH0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecode_negativeH0Null() throws Exception {
        decoder.decode(mock(RandomAccessFile.class), mock(OutputStream.class), null);
    }

    @Test
    public void testDecode_negativeInvalidHashReaderLengthLessThanBlockSize()
            throws IOException {
        RandomAccessFile reader = mock(RandomAccessFile.class);
        OutputStream writer = mock(OutputStream.class);

        doReturn((long)Constants.CHUNK_SIZE).when(reader).length();
        doNothing().when(reader).seek(0);
        doReturn(Constants.CHUNK_SIZE)
                .when(reader).read(any(byte[].class));

        Assert.assertFalse(decoder.decode(reader, writer, "mock"));
    }

    @Test
    public void testDecode_negativeInvalidHashReaderLengthLargerThanBlockSize()
            throws IOException {
        RandomAccessFile reader = mock(RandomAccessFile.class);
        OutputStream writer = mock(OutputStream.class);

        doReturn((long)Constants.BLOCK_SIZE * 3)
                .when(reader).length();
        doNothing().when(reader).seek(0);
        doReturn(Constants.BLOCK_SIZE)
                .when(reader).read(any(byte[].class));

        Assert.assertFalse(decoder.decode(reader, writer, "mock"));
    }

}
