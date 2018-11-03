import java.util.Hashtable;
import java.util.LinkedList;

public class LRUCacheReal extends LRUCache {

    public LRUCacheReal() {
        this.size = 50;
    }

    public LRUCacheReal(int size) {
        this.size = size;
    }

    private int dumbSlowFunction(int x) {
        int result = 1;
        final int mod = 10003;
        for (int j = 0; j < 10; j++) {
            for (int i = 2; i <= x % mod; i++) {
                result *= i;
                result %= mod;
            }
        }
        return result;
    }

    protected int doGet(int key) {
        if (hashTable.containsKey(key)) {
            linkedList.remove(new Integer(key));
            linkedList.addLast(key);
            return hashTable.get(key);
        }
        if (hashTable.size() == size) {
            int rkey = linkedList.pollFirst();
            hashTable.remove(rkey);
        }
        int value = dumbSlowFunction(key);
        hashTable.put(key, value);
        linkedList.addLast(key);
        return value;
    }

    public void doClear() {
        hashTable.clear();
        linkedList.clear();
    }

}
