package cf.domone.android.httptask.asynctask;

import org.json.JSONObject;

import java.io.IOException;

import cf.domone.android.httptask.asynctask.strategy.JsonObjectDownloading;

/**
 * This class is an AsyncTask to download a JSON object.
 */
public class HttpJsonDownloadTask extends HttpDownloadTask<JSONObject> {

    /** It is also prohibited to call this default constructor. */
    protected HttpJsonDownloadTask() {
        super();
    }

    /**
     * Call this constructor to create the object.
     * @param targetAddress The HTTP address to download
     */
    public HttpJsonDownloadTask(String targetAddress) throws IOException {
        super(targetAddress, new JsonObjectDownloading());
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        return super.doInBackground(params);
    }
}
