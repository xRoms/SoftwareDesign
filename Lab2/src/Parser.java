import java.time.Instant;

public class Parser {
    private final long SECONDSINHOUR = 60*60;
    private final long REQUIREDDEPTH = 3;
    private final int DIRTYUNIXLENGTH = 10;

    public int[] getPostCount(String response, int hours) {
        int lastDateFound = 0;
        int depth = 0;
        int currentDateFound;
        int[] cntPosts = new int[hours];
        for (int i = 0; i < hours; i++) {
            cntPosts[i] = 0;
        }
        String jsonSubstring = "\"date\":";
        long currentTime = Instant.now().getEpochSecond();
        long startTime = currentTime - hours * SECONDSINHOUR;
        while ((currentDateFound = response.indexOf(jsonSubstring, lastDateFound)) != -1) {
            for (int i = lastDateFound; i < currentDateFound; i++) {
                if (response.charAt(i) == '{') {
                    depth++;
                }
                if (response.charAt(i) == '}') {
                    depth--;
                }
            }
            String postTimeString = response.substring(currentDateFound + jsonSubstring.length(), currentDateFound + jsonSubstring.length() + DIRTYUNIXLENGTH);
            long postTime = Long.parseLong(postTimeString.trim());
            lastDateFound = currentDateFound + jsonSubstring.length();
            if ((postTime < startTime) || (depth != REQUIREDDEPTH)) {
                continue;
            }
            int hour = (int)((currentTime - postTime) / SECONDSINHOUR);
            cntPosts[hour] += 1;
        }
        return cntPosts;
    }
}
