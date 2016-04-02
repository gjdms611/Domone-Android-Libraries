package cf.domone.android.httptask.asynctask;

import java.io.IOException;

import cf.domone.android.httptask.asynctask.strategy.StringDownloading;

/**
 * This class is an AsyncTask to download a string.
 */
public class HttpStringDownloadTask extends HttpDownloadTask<String> {

    /** It is also prohibited to call this default constructor. */
    protected HttpStringDownloadTask() {
        super();
    }

    /**
     * Call this constructor to create the object.
     * @param targetAddress The HTTP address to download
     */
    public HttpStringDownloadTask(String targetAddress) throws IOException {
        super(targetAddress, new StringDownloading());
    }

    /**
     * Downloads the string through the HTTP connection.
     * @param params Not used
     * @return The downloaded string
     */
    @Override
    protected String doInBackground(Void... params) {
        return super.doInBackground(params);
    }

}
