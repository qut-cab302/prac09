package mapset;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestMapSet {
    @Test
    void testConstructor() {
        MapSet<String, Object> mapset = new MapSet<>();
    }
    @Test
    void testIteratorCount() {
        MapSet<String, String> mapset = new MapSet<>();
        mapset.addValue("a", "b");
        mapset.addValue("a", "c");
        mapset.addValue("b", "b");
        mapset.addValue("b", "b"); // Duplicate
        mapset.addValue("c", "b");
        var iterator = mapset.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(4, count);
    }
    @Test
    void testIteratorOneValue() {
        MapSet<String, Object> mapset = new MapSet<>();
        Object testObject = new Object();
        mapset.addValue("a", testObject);

        var iterator = mapset.iterator();
        assertTrue(iterator.hasNext());
        var firstIteration = iterator.next();
        assertEquals(firstIteration, testObject);
        assertFalse(iterator.hasNext());
    }
    @Test
    void testEntrySet() {
        MapSet<String, String> mapset = new MapSet<>();
        mapset.addValue("a", "b");
        mapset.addValue("a", "c");
        mapset.addValue("b", "b");
        mapset.addValue("b", "b"); // Duplicate
        mapset.addValue("c", "b");
        var entrySet = mapset.entrySet();
        assertEquals(3, entrySet.size()); // 3 different keys, a b and c
        int[] correctMatches = new int[]{0, 0, 0, 0};
        entrySet.forEach(entry -> {
            if (entry.getKey().equals("a") && entry.getValue().contains("b")) correctMatches[0]++;
            if (entry.getKey().equals("a") && entry.getValue().contains("c")) correctMatches[1]++;
            if (entry.getKey().equals("b") && entry.getValue().contains("b")) correctMatches[2]++;
            if (entry.getKey().equals("c") && entry.getValue().contains("b")) correctMatches[3]++;
        });
        for (int i = 0; i < 4; i++) {
            if (correctMatches[i] != 1) {
                fail("Duplicate / missing entry in entrySet");
            }
        }
    }
}
