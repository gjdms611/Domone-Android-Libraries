package cf.domone.android.httptask.asynctask.strategy;

import java.io.InputStream;

/**
 * Strategy interface to download something such as a string or a file
 * @param <T> Target object to be downloaded
 */
public interface Downloading<T> {

    /**
     * Implement downloading T object into this method.
     * @param inputStream The input stream to download something such as a string or a file
     * @return The T object downloaded from the input stream
     */
    T download(InputStream inputStream);

}
