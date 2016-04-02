package cf.domone.android.httptask.asynctask.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Strategy class to download a file from an input stream.
 */
public class FileDownloading extends BufferDownloading<File> {

    /** The path of the file to be downloaded */
    protected String path;

    /** The name of the file to be downloaded */
    protected String fileName;

    /** It is prohibited to call this default constructor. */
    protected FileDownloading() {
        super();
    }

    /**
     * It is also prohibited to call this constructor.
     * @param bufferSize The size of download buffer
     */
    protected FileDownloading(int bufferSize) {
        super(bufferSize);
    }

    /**
     * Use this constructor.
     * @param path The value to set path field
     * @param fileName The value to set fileName field
     */
    public FileDownloading(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * Use this constructor.
     * @param path The value to set path field
     * @param fileName The value to set fileName field
     * @param bufferSize The size of download buffer
     */
    public FileDownloading(String path, String fileName, int bufferSize) {
        this.path = path;
        this.fileName = fileName;
        this.setBufferSize(bufferSize);
    }
    /**
     * This method downloads a file through its buffer from the input stream.
     * The file is deleted if downloading it fails.
     * @param inputStream The input stream to download the target file
     * @return File object for the downloaded file or null
     */
    @Override
    public File download(InputStream inputStream) {
        File pathAsFile = new File(path);
        File downloadedFile = new File(path + File.separator + fileName);

        // Create the path of the file to be downloaded if the path does not exist.
        if (!pathAsFile.exists()) {
            pathAsFile.mkdirs();
        }

        // Create the target file if it does not exist.
        if (!downloadedFile.exists()) {
            try {
                downloadedFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Read the content of the file from the input stream,
        // and write the content into the target file.
        try (
            FileOutputStream fos = new FileOutputStream(downloadedFile);
        ) {
            while (true) {
                byte[] buffer = new byte[bufferSize];
                int readingCount = inputStream.read(buffer);
                if (readingCount < 0) {
                    break;
                }
                fos.write(buffer, 0, readingCount);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return downloadedFile;
    }
}
