package cf.domone.android.httptask;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import cf.domone.android.httptask.asynctask.HttpJsonDownloadTask;

/**
 * Test case class to test HttpJsonDownloadTask class.
 */
public class TestCaseOf_HttpJsonDownloadTask extends TestCase {

    /** Synchronization object for a test method to wait for the download task to be completed */
    private CountDownLatch signal;

    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void test_doInBackground() throws IOException {
        signal = new CountDownLatch(1);
        String testSourceAddress = "http://netrance.cafe24.com/test/cf/domone/android/httptask/asynctask/HelloWorld.aspx";
        SampleJsonStringDownloadTask task = new SampleJsonStringDownloadTask(testSourceAddress);
        task.execute();

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("InterruptedException occurs.");
            return;
        }

        //assertEquals("Hello, world.", task.downloadedString);
        int msgNo = -1;
        String msgContent = null;
        JSONObject downloadedJsonObject = task.downloadedJsonObject;
        try {
            msgNo = downloadedJsonObject.getInt("msg_no");
            msgContent = downloadedJsonObject.getString("msg_content");
        }
        catch (JSONException e) {
            fail("Cannot read data from the downloaded JSONObject object.");
        }

        assertNotNull(msgContent);
        assertEquals(1, msgNo);
        assertEquals("Hello, world.", msgContent);
    }

    public class SampleJsonStringDownloadTask extends HttpJsonDownloadTask {
        public JSONObject downloadedJsonObject;

        public SampleJsonStringDownloadTask(String sourceHttpAddress) throws IOException {
            super(sourceHttpAddress);
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            this.downloadedJsonObject = result;
            signal.countDown();
        }
    }

}
