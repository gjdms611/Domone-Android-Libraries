package cf.domone.android.httptask.asynctask.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Strategy class to download a string from an input stream
 */
public class StringDownloading extends BufferDownloading<String> {

    public StringDownloading() {
        super();
    }

    /**
     * @param bufferSize The size of download buffer
     */
    public StringDownloading(int bufferSize) {
        super(bufferSize);
    }

    /**
     * This method downloads a string through its buffer.
     * @param inputStream The input stream to download something such as a string or a file
     * @return The downloaded string or null
     */
    @Override
    public String download(InputStream inputStream) {
        StringBuilder downloadedStringBuilder = new StringBuilder();
        String downloadedString;

        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ){
            // Download string content from the input stream into a string builder.
            while (true) {
                char[] buffer = new char[bufferSize];
                int readingCount = reader.read(buffer);
                if (readingCount < 0) {
                    break;
                }
                downloadedStringBuilder.append(buffer, 0, readingCount);
            }

            // Create string object using the string builder, and return it.
            downloadedString = downloadedStringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return downloadedString;
    }
}
