import java.util.ArrayList;

public class Main {



    static public void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        String hashtagToSearchFor = args[0];
        int timeInHours = Integer.parseInt(args[1]);
        DiagramCreator dCreator = new DiagramCreator();
        int[] result = dCreator.create(hashtagToSearchFor, timeInHours);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
