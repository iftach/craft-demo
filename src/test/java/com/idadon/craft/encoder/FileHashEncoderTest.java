package com.idadon.craft.encoder;

import com.idadon.craft.Constants;
import com.idadon.craft.utils.ReaderUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.mockito.MockedStatic;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class FileHashEncoderTest {

    private FileHashEncoder encoder;

    @BeforeTest
    private void setUp() {
        encoder = new FileHashEncoder();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecode_negativeReaderNull() throws Exception {
        encoder.encode(null, mock(OutputStream.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecode_negativeWriterNull() throws Exception {
        encoder.encode(mock(RandomAccessFile.class), null);
    }
}
