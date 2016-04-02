package cf.domone.android.httptask.asynctask;

import java.io.File;
import java.io.IOException;

import cf.domone.android.httptask.asynctask.strategy.FileDownloading;

/**
 * This class is an AsyncTask to download a file.
 */
public class HttpFileDownloadTask extends HttpDownloadTask<File> {
    /** It is also prohibited to call this default constructor. */
    protected HttpFileDownloadTask() {
        super();
    }

    /**
     * Call this constructor to create the object.
     * @param targetAddress The HTTP address to download
     */
    public HttpFileDownloadTask(String targetAddress, String path, String fileName) throws IOException {
        super(targetAddress, new FileDownloading(path, fileName));
    }

    @Override
    protected File doInBackground(Void... params) {
        return super.doInBackground(params);
    }

}
