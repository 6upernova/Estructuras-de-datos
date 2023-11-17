

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import cache.LRUCache;
import cache.LRUCacheMap;

public class LRUCacheTest {

	private LRUCache<String, Integer> cache;

    @Before
    public void setUp() {
        cache = new LRUCacheMap<>(3);
    }

    @Test
    public void testGet() {
        cache.put("A", 1);
        assertEquals(Integer.valueOf(1), cache.get("A"));
        assertNull(cache.get("B"));
    }

    @Test
    public void testPut() {
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        assertEquals(Integer.valueOf(1), cache.get("A"));
        assertEquals(Integer.valueOf(2), cache.get("B"));
        assertEquals(Integer.valueOf(3), cache.get("C"));
        cache.put("D", 4);
        
        assertNull(cache.get("A"));
    }

    @Test
    public void testRemove() {
        cache.put("A", 1);
        cache.put("B", 2);
        cache.remove("A");
        assertNull(cache.get("A"));
    }

    @Test
    public void testClear() {
        cache.put("A", 1);
        cache.put("B", 2);
        cache.clear();
        assertEquals(0, cache.size());
        assertNull(cache.get("A"));
        assertNull(cache.get("B"));
    }

    @Test
    public void testSize() {
        cache.put("A", 1);
        cache.put("B", 2);
        assertEquals(2, cache.size());
        cache.remove("A");
        assertEquals(1, cache.size());
    }

}


