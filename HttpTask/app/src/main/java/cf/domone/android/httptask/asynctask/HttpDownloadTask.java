package cf.domone.android.httptask.asynctask;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cf.domone.android.httptask.asynctask.strategy.Downloading;
import cf.domone.android.httptask.settings.ConnectionSetting;

/**
 * This class is an AsyncTask to download something such as a string or a file.
 * @param <Result> The data type of the downloaded content.
 */
public class HttpDownloadTask<Result> extends AsyncTask<Void, Integer, Result> {

    /** Connection object to download */
    protected HttpURLConnection connection;

    /** Strategy object to download */
    protected Downloading<Result> downloading;

    /** Field to set HTTP connection setting */
    protected ConnectionSetting connectionSetting;

    /** It is prohibited to call this default constructor. */
    protected HttpDownloadTask() {
    }

    /**
     * Call this constructor to create HttpDownloadTask object.
     * @param targetAddress The HTTP address to download
     * @param downloading The strategy object to download
     * @throws IOException
     */
    public HttpDownloadTask(
        String targetAddress,
        Downloading<Result> downloading
    ) throws IOException {
        // This code can throw MalformedURLException.
        URL url = new URL(targetAddress);

        // This code can throw IOException.
        this.connection = (HttpURLConnection)url.openConnection();

        // Set the other fields.
        this.downloading = downloading;
        this.connectionSetting = new ConnectionSetting(this.connection);
    }

    /**
     * Download Result object from the HTTP address.
     * @param params Not used
     * @return The result object downloaded or null
     */
    @Override
    protected Result doInBackground(Void... params) {
        Result result = null;

        try (
            InputStream inputStream = this.connection.getInputStream();
        ){
            // Check if the server connection is successful.
            int responseCode = this.connection.getResponseCode();
            if (200 != responseCode) {
                this.connection.disconnect();
                return null;
            }

            // Download the Result object from the HTTP address.
            result = downloading.download(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            this.connection.disconnect();
            return null;
        }

        this.connection.disconnect();
        return result;
    }

    /** Call this method to set HTTP URL connection */
    public ConnectionSetting getConnectionSetting() {
        return this.connectionSetting;
    }

}
