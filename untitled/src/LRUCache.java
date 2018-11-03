import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class LRUCache {
    int size;
    Hashtable<Integer, Integer> hashTable = new Hashtable<>();
    LinkedList<Integer> linkedList = new LinkedList<>();


    public final int get(int key) {
        assert (hashTable.size() <= size) && (linkedList.size() == hashTable.size());
        int value = doGet(key);
        assert (hashTable.size() <= size) && (linkedList.size() == hashTable.size()) && (linkedList.getLast() == key) && hashTable.containsKey(key);
        return value;
    }

    public void clear() {
        assert (hashTable.size() <= size) && (linkedList.size() == hashTable.size());
        doClear();
        assert (hashTable.size() == 0) && (linkedList.size() == 0);
    }

    protected abstract int doGet(int key);
    protected abstract void doClear();
}
