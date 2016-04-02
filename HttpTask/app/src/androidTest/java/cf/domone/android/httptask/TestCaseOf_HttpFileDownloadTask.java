package cf.domone.android.httptask;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.concurrent.CountDownLatch;

import cf.domone.android.httptask.asynctask.HttpFileDownloadTask;

/**
 * Test case class to test HttpFileDownloadTask class.
 */
public class TestCaseOf_HttpFileDownloadTask extends AndroidTestCase {

    private Context context;

    /** Synchronization object for a test method to wait for the download task to be completed */
    private CountDownLatch signal;

    private String testSourceAddress;
    private String downloadPath;
    private String downloadFileName;

    public void setUp() throws Exception {
        this.context = getContext();

        this.testSourceAddress = "http://netrance.cafe24.com/test/cf/domone/android/httptask/asynctask/helloworld.txt";
        this.downloadPath = this.context.getFilesDir() + "/download_test";
        this.downloadFileName = "helloworld.txt";
    }

    public void tearDown() throws Exception {
        File fileDownloaded = new File(this.downloadPath, this.downloadFileName);
        fileDownloaded.delete();
    }

    public void test_doInBackground() throws IOException {
        this.signal = new CountDownLatch(1);

        SampleHttpFileDownloadTask task = new SampleHttpFileDownloadTask(
                testSourceAddress,
                downloadPath,
                downloadFileName
        );
        task.execute();

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("InterruptedException occurs.");
            return;
        }

        try (
                BufferedReader br = new BufferedReader(new FileReader(task.downloadedFile));
        ) {
            String actualResult = br.readLine();
            assertEquals("Hello, world.", actualResult);
        }
        catch (IOException e) {
            fail();
        }

        //assertEquals("Hello, world.", task.downloadedString);
    }

    public class SampleHttpFileDownloadTask extends HttpFileDownloadTask {
        public File downloadedFile;

        public String sourceHttpAddress, path, fileName;

        public SampleHttpFileDownloadTask(String sourceHttpAddress, String path, String fileName) throws IOException {
            super(sourceHttpAddress, path, fileName);
            this.sourceHttpAddress = sourceHttpAddress;
            this.path = path;
            this.fileName = fileName;
        }

        @Override
        protected File doInBackground(Void... params) {
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(File downloadedFile) {
            this.downloadedFile = downloadedFile;
            signal.countDown();
        }
    }

}
