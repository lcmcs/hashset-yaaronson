package edu.touro.cs.mcon364;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;

import static java.util.Spliterators.iterator;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main hs = new Main();


    @Test
    void size() {
        hs.add("e");
        assertEquals(1,hs.size());
    }

    @Test
    void isEmpty() {
        assertTrue(hs.isEmpty());
    }

    @Test
    void clear() {
        assertEquals(0,hs.size());
        assertTrue(hs.isEmpty());
    }

    @Test
    void contains() {
        hs.add("a");
        assertTrue(hs.contains("a"));
        assertFalse(hs.contains("d"));
    }

    @Test
    void remove() {
        hs.add("w");
        assertEquals(1,hs.size());
        hs.remove("w");
        assertEquals(0,hs.size());
    }
    @Test
    void hasNext() {
      hs.add("a");
      Iterator<String> it = hs.iterator();
      assertTrue(it.hasNext());
      it.next();
      assertFalse(it.hasNext());
    }
    @Test
    void Next() {
        hs.add("a");
        Iterator<String> it = hs.iterator();
        assertEquals("a",it.next());
    }

    @Test
    void toArray() {
        hs.add("a");
        assertArrayEquals(new String[]{"a"}, hs.toArray());
    }

    @Test
    void add() {
        hs.add("e");
        hs.add("w");
        assertEquals(2,hs.size());
        hs.add("w");
        assertEquals(2,hs.size());
    }

    @Test
    void testRemove() {
        hs.add("e");
        assertEquals(1,hs.size());
        hs.remove("e");
        assertEquals(0,hs.size());
    }
}