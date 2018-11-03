public class DiagramCreator {
    public int[] create(String hashtag, int hours) {

        Loader loader = new Loader();
        Parser parser = new Parser();

        String vkResponse = loader.getResponse(hashtag, hours);
        return parser.getPostCount(vkResponse, hours);
    }
}
