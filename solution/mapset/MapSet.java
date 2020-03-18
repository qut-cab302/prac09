package mapset;

import java.util.*;

public class MapSet<K, V> extends AbstractMap<K, HashSet<V>> implements Iterable<V> {

    public HashMap<K, HashSet<V>> internal = new HashMap<K, HashSet<V>>();

    public static void main(String[] args) {
        MapSet<String, Integer> map = new MapSet<>();
        map.addValue("B", 4);
        map.addValue("A", 0);
        map.addValue("A", 1);
        map.addValue("B", 3);
        map.addValue("A", 2);
        for (Integer value : map) {
            System.out.println(value);
        }
    }

    public class MapListIterator<V> implements Iterator<V>
    {
        int key = 0;
        int value = 0;
        ArrayList<HashSet<V>> keys;
        HashSet<V> currentValues;
        Iterator<V> currentValuesIterator;

        public MapListIterator()
        {
            ArrayList a = new ArrayList(internal.values());
            Collections.sort(a, (HashSet<V> z, HashSet<V> x) -> x.size() - z.size());
            keys = a;
            currentValues = keys.get(key);
            currentValuesIterator = currentValues.iterator();
        }

        @Override
        public boolean hasNext() {
            return key < keys.size() - 1  || currentValuesIterator.hasNext();
        }

        @Override
        public V next() {
            if(!currentValuesIterator.hasNext())
            {
                HashSet<V> newValuesSet = keys.get(++key);
                currentValuesIterator = newValuesSet.iterator();
            }
            return currentValuesIterator.next();
        }
    }

    public Iterator<V> iterator() {
        return new MapListIterator<V>();
    }

    public void addValue(K key, V value) {
        if (!containsKey(key)) {
            internal.put(key, new HashSet<V>());
        }
        get(key).add(value);
    }

    @Override
    public Set<Entry<K, HashSet<V>>> entrySet() {
        return internal.entrySet();
    }
}
