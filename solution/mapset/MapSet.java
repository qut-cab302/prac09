package mapset;

import java.util.*;

public class MapSet<K, V> extends AbstractMap<K, HashSet<V>> implements Iterable<V> {

    public static void main(String[] args) {
        MapSet<String, Integer> map = new MapSet<>();

        map.addValue("B", 4);
        map.addValue("B", 4);

        map.addValue("B", 3);
        map.addValue("A", 2);

        map.addValue("@", 10);

        map.addValue("A", 0);
        map.addValue("A", 1);

        for (Integer value : map) {
            System.out.println(value);
        }
    }

    // internal data structure
    HashMap<K, HashSet<V>> internal = new HashMap<>();

    private void addValue(K key, V value) {
        HashSet<V> hashset = internal.computeIfAbsent(key, k -> new HashSet<>());
        hashset.add(value);
    }

    class InternalIterator implements Iterator<V> {
        Iterator<Iterator<V>> hashsetIterators;
        Iterator<V> iterator;

        public InternalIterator() {
            ArrayList<HashSet<V>> hashsets = new ArrayList<>();

            for (Map.Entry<K, HashSet<V>> entry : entrySet()) {
                hashsets.add(entry.getValue());

            }
            hashsets.sort( (HashSet<V> o1, HashSet<V> o2 ) -> o2.size() - o1.size() );
            ArrayList<Iterator<V>> hashsetIterators = new ArrayList<>();

            for (HashSet<V> hashset : hashsets) {
                hashsetIterators.add(hashset.iterator());
            }

            this.hashsetIterators = hashsetIterators.iterator();
            this.iterator = this.hashsetIterators.next();
        }

        @Override
        public boolean hasNext() {
            return hashsetIterators.hasNext() || iterator.hasNext();
        }

        @Override
        public V next() {
            boolean exhausted = !iterator.hasNext();
            if (exhausted) {
                iterator = hashsetIterators.next();
            }
            return iterator.next();
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new InternalIterator();
    }

    @Override
    public Set<Entry<K, HashSet<V>>> entrySet() {
        return internal.entrySet();
    }
}
