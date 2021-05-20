package com.idadon.craft.utils;

import com.idadon.craft.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class ReaderUtilsTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRead_negativeReadLengthNegative() throws IOException {
        RandomAccessFile reader = mock(RandomAccessFile.class);

        ReaderUtils.read(reader, 0, -1);
    }

    @Test
    public void testReadPositive() throws IOException {
        RandomAccessFile reader = mock(RandomAccessFile.class);

        doReturn((long)Constants.CHUNK_SIZE)
                .when(reader).length();

        doNothing().when(reader).seek(0);

        doReturn(Constants.CHUNK_SIZE)
                .when(reader).read(any(byte[].class));

        doAnswer(invocation -> {
            byte[] data = invocation.getArgument(0);
            Assert.assertEquals(data.length, Constants.CHUNK_SIZE);
            return null;
        }).when(reader).read(any(byte[].class));

        ReaderUtils.read(reader, 0, Constants.CHUNK_SIZE);
    }
}
