import java.util.*;

/**
 * Created by cxj4842 on 2018/8/6.
 */
public class ConsistentHashing<T> {
    private TreeMap<Integer, T> hashCircle = new TreeMap();

    /**
     * 每个实际节点的虚拟节点数
     */
    private int virtualNum = 0;

    private LinkedList<T> realNode = new LinkedList();

    public ConsistentHashing(int virtualNum, Collection<T> nodes) {
        this.virtualNum = virtualNum;
        for (T node : nodes) {
            addNode(node);
        }
    }

    private void addNode(T node) {
        for (int i = 0; i < virtualNum; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.toString() + i);
            int key = HashUtils.getHash(sb.toString());
            hashCircle.put(key, node);
        }
    }

    public T getNode(Object key) {
        if (hashCircle.isEmpty() || key == null) {
            return null;
        }
        int hashCode = HashUtils.getHash(String.valueOf(key.hashCode()));
        if (!hashCircle.containsKey(hashCode)) {
            SortedMap<Integer, T> sortedMap = hashCircle.tailMap(hashCode);
            hashCode = sortedMap.isEmpty() ? 0 : sortedMap.firstKey();
        }
        return hashCircle.get(hashCode);
    }

    public void deleNode(T node) {
        for (int i = 0; i < virtualNum; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.toString() + i);
            int key = HashUtils.getHash(sb.toString());
            hashCircle.remove(key, node);
        }
    }

}
