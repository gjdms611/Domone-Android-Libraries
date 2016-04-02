package cf.domone.android.httptask;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import cf.domone.android.httptask.asynctask.HttpStringDownloadTask;

/**
 * Test case class to test HttpStringDownloadTask class.
 */
public class TestCaseOf_HttpStringDownloadTask extends AndroidTestCase {

    /** Synchronization object for a test method to wait for the download task to be completed */
    private CountDownLatch signal;

    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    /**
     * Test method to test doInBackground method.
     * @throws IOException
     */
    public void test_doInBackground() throws IOException {
        signal = new CountDownLatch(1);
        String testSourceAddress = "http://netrance.cafe24.com/test/cf/domone/android/httptask/asynctask/helloworld.txt";
        SampleHttpStringDownloadTask task = new SampleHttpStringDownloadTask(testSourceAddress);
        task.execute();

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("InterruptedException occurs.");
            return;
        }

        assertEquals("Hello, world.", task.downloadedString);
    }

    public class SampleHttpStringDownloadTask extends HttpStringDownloadTask {
        public String downloadedString;

        public SampleHttpStringDownloadTask(String sourceHttpAddress) throws IOException {
            super(sourceHttpAddress);
        }

        @Override
        protected String doInBackground(Void... params) {
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(String s) {
            this.downloadedString = s;
            signal.countDown();
        }
    }
}
