package cf.domone.android.httptask.settings;

import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * This class is to set HTTP connection such as request method, connection timeout, etc.
 */
public class ConnectionSetting {

    private HttpURLConnection connection;

    public ConnectionSetting(HttpURLConnection connection) {
        this.connection = connection;
    }

    public ConnectionSetting setAllowUserInteraction(boolean newValue) {
        connection.setAllowUserInteraction(newValue);
        return this;
    }

    public ConnectionSetting setChunkedStreamingMode(int chunkLength) {
        connection.setChunkedStreamingMode(chunkLength);
        return this;
    }

    public ConnectionSetting setConnectionTimeout(int timeoutMillis) {
        connection.setConnectTimeout(timeoutMillis);
        return this;
    }

    public ConnectionSetting setDefaultUseCashes(boolean newValue) {
        connection.setDefaultUseCaches(newValue);
        return this;
    }

    public ConnectionSetting setDoInput(boolean newValue) {
        connection.setDoInput(newValue);
        return this;
    }

    public ConnectionSetting setDoOutput(boolean newValue) {
        connection.setDoOutput(newValue);
        return this;
    }

    public ConnectionSetting setFixedLengthStreamingMode(int contentLength) {
        connection.setFixedLengthStreamingMode(contentLength);
        return this;
    }

    public ConnectionSetting setIfModifiedSince(long newValue) {
        connection.setIfModifiedSince(newValue);
        return this;
    }

    public ConnectionSetting setInstanceFollowRedirects(boolean followRedirects) {
        connection.setInstanceFollowRedirects(followRedirects);
        return this;
    }

    public ConnectionSetting setRequestMethod(String method) throws ProtocolException {
        connection.setRequestMethod(method);
        return this;
    }

    public ConnectionSetting setReadTimeout(int timeoutMillis) {
        connection.setReadTimeout(timeoutMillis);
        return this;
    }

    public ConnectionSetting setRequestProperty(String field, String newValue) {
        connection.setRequestProperty(field, newValue);
        return this;
    }

    public ConnectionSetting setUseCashes(boolean newValue) {
        connection.setUseCaches(newValue);
        return this;
    }
}
