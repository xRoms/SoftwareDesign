import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;

public class Loader {
    private final long SECONDSINHOUR = 60*60;

    public String getResponse(String hashtag, int hours) {
        long startTime = Instant.now().getEpochSecond() - hours * SECONDSINHOUR;
        URLConnection connection;
        try {
            String URLString = "https://api.vk.com/method/newsfeed.search?q=" + hashtag + "&start_time=" + startTime + "&access_token=ace4f28bace4f28bace4f28b44ac822b9baace4ace4f28bf739f11c62a3aebc48ea601e&v=5.87";
            connection = new URL(URLString).openConnection();
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        try {
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            char[] buffer = new char[256];
            int rc;

            StringBuilder sb = new StringBuilder();

            while ((rc = reader.read(buffer)) != -1)
                sb.append(buffer, 0, rc);

            reader.close();

            return sb.toString();
        } catch (IOException e) {
            return e.getLocalizedMessage();
        }
    }




}
