import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleTests {

    public static void main(String[] args) throws Exception {
        SimpleTests tests = new SimpleTests();

        tests.testGet();
        tests.testPostJson();
        tests.testPostForm();
        tests.testPut();
        tests.testDelete();

        System.out.println("Все тесты прошли!");
    }

    public void testGet() throws Exception {
        URL url = new URL("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() == 200) {
            System.out.println("GET test passed");
        } else {
            System.out.println("GET test failed");
        }
        conn.disconnect();
    }

    public void testPostJson() throws Exception {
        URL url = new URL("https://postman-echo.com/post");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        String json = "{\"test\":\"value\"}";
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.close();

        if (conn.getResponseCode() == 200) {
            System.out.println("POST JSON test passed");
        } else {
            System.out.println("POST JSON test failed");
        }
        conn.disconnect();
    }

    public void testPostForm() throws Exception {
        URL url = new URL("https://postman-echo.com/post");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        String form = "foo1=bar1&foo2=bar2";
        OutputStream os = conn.getOutputStream();
        os.write(form.getBytes());
        os.close();

        if (conn.getResponseCode() == 200) {
            System.out.println("POST Form test passed");
        } else {
            System.out.println("POST Form test failed");
        }
        conn.disconnect();
    }

    public void testPut() throws Exception {
        URL url = new URL("https://postman-echo.com/put");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);

        String text = "test text";
        OutputStream os = conn.getOutputStream();
        os.write(text.getBytes());
        os.close();

        if (conn.getResponseCode() == 200) {
            System.out.println("PUT test passed");
        } else {
            System.out.println("PUT test failed");
        }
        conn.disconnect();
    }


    public void testDelete() throws Exception {
        URL url = new URL("https://postman-echo.com/delete");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setDoOutput(true);

        String text = "test text";
        OutputStream os = conn.getOutputStream();
        os.write(text.getBytes());
        os.close();

        if (conn.getResponseCode() == 200) {
            System.out.println("DELETE test passed");
        } else {
            System.out.println("DELETE test failed");
        }
        conn.disconnect();
    }
}