package cf.domone.android.httptask.asynctask.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * Strategy class to download something through a buffer.
 * download(inputStream) method is still not implemented in the class.
 * It is why this class is abstract.
 * @param <T> Target object to be downloaded
 */
public abstract class BufferDownloading<T> implements Downloading<T> {
    /** The size of the buffer */
    protected int bufferSize;

    /** The constant that means the default size of the buffer */
    protected static final int DEFAULT_BUFFER_SIZE = 2048;

    public BufferDownloading() {
        this.bufferSize = DEFAULT_BUFFER_SIZE;
    }

    /**
     * @param bufferSize The size of download buffer
     */
    public BufferDownloading(int bufferSize) {
        setBufferSize(bufferSize);
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
