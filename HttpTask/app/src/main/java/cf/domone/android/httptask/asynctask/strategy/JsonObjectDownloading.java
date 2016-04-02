package cf.domone.android.httptask.asynctask.strategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Strategy class to download a JSON object from an input stream.
 */
public class JsonObjectDownloading extends BufferDownloading<JSONObject> {

    public JsonObjectDownloading() {
        super();
    }

    /**
     * @param bufferSize The size of download buffer
     */
    public JsonObjectDownloading(int bufferSize) {
        super(bufferSize);
    }

    /**
     * This method downloads a JSON object through its buffer.
     * @param inputStream The input stream to download something such as a string or a file
     * @return The downloaded JSON object or null
     */
    @Override
    public JSONObject download(InputStream inputStream) {
        // Download JSON string from the source address.
        StringDownloading stringDownloading = new StringDownloading();
        String downloadedString = stringDownloading.download(inputStream);
        if (null == downloadedString) {
            return null;
        }

        // Transform the string into a JSON object.
        JSONObject jsonResult;
        try {
            jsonResult = new JSONObject(downloadedString);
        }
        catch (JSONException e) {
            e.printStackTrace();
            jsonResult = null;
        }

        return jsonResult;
    }

}
