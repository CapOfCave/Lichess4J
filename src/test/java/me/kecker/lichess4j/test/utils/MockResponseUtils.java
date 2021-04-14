package me.kecker.lichess4j.test.utils;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okio.Buffer;

public final class MockResponseUtils {

    /**
     * Creates a MockResponse with the content of the file that is loaded.
     * 
     * @param fileName  Name of the file to be read
     * @param baseClass Class whose class loader will be used
     * @return A {@link MockResponse} with response code 200 and the file's content
     *         as its body
     * @throws IOException
     * @see {@link #getBufferFromFile(String, Class)}
     */
    public static MockResponse fromFile(String fileName, Class<?> baseClass) throws IOException {
        return new MockResponse().setBody(getBufferFromFile(fileName, baseClass));
    }

    /**
     * Reads a file and returns its content in a buffer, using the passed Class's
     * class loader.
     * 
     * @param fileName  Name of the file to be read
     * @param baseClass Class whose class loader will be used
     * @return A {@link Buffer} containing the file's content
     * @throws IOException
     * @see {@link Buffer#readFrom(java.io.InputStream)}
     */
    private static Buffer getBufferFromFile(String fileName, Class<?> baseClass)
            throws IOException {
        Buffer buffer = new Buffer();
        buffer.readFrom(baseClass.getResourceAsStream(fileName));
        return buffer;
    }

    private MockResponseUtils() {
        // this class should not be instantiated
    }

}
