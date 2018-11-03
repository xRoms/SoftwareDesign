
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserTest {
/*
    @Test
    public void testParser() {
        String mockString = mock(String.class);
        long currentTime = Instant.now().getEpochSecond();
        for (int i = 0; i < 3; i++) {
            when(mockString.charAt(anyInt())).thenReturn('{');
        }
        when(mockString.charAt(anyInt())).thenReturn(' ');
        for (int i = 0; i < 600; i++) {
            when(mockString.indexOf(anyString(), anyInt())).thenReturn(i);
            when(mockString.substring(anyInt(), anyInt())).thenReturn(Long.toString(currentTime - 60 * i));
        }
        Parser parser = new Parser();
        int[] result = parser.getPostCount(mockString, 10);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }*/

    @Test
    public void testParserA() {

        long currentTime = Instant.now().getEpochSecond();
        String genJson = "{{{";
        for (int i = 0; i < 600; i++) {
            genJson = genJson.concat("\"date\":" + Long.toString(currentTime - 60 * i));
        }
        Parser parser = new Parser();

        int[] result = parser.getPostCount(genJson, 10);
        for (int i : result) {
            Assertions.assertEquals(i, 60);
        }


    }
}
