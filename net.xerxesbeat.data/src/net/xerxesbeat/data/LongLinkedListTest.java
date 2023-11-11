package net.xerxesbeat.data;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class LongLinkedListTest {

    LongLinkedList empty_list = null;
    LongLinkedList full_list = null;
    int[] small_range = IntStream.rangeClosed(1, 3).toArray();
//    long[] large_range = LongStream.rangeClosed(1, 20_000).toArray();
    List<Long> large_range = LongStream.rangeClosed(1, 20_000).boxed().collect(Collectors.toList());

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        empty_list = new LongLinkedList<>();
        full_list = new LongLinkedList<Long>(large_range);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        empty_list = null;
    }

    @Test
    void empty() {
        assertFalse(full_list.empty());
    }

    @org.junit.jupiter.api.Test
    void pop() {
        assertEquals(20_000L, full_list.pop());
        assertEquals(19_999L, full_list.last());
        assertEquals(19_999L, full_list.size());
    }

    /**
     * This test fails really badly if you use more than 89,400,855 long ints.
     * Not sure why, but that's pretty weird.
     */
    @org.junit.jupiter.api.Test
    void last() {
        assertEquals(20_000L, full_list.last());
    }

    @org.junit.jupiter.api.Test
    void push() {
        full_list.push(1234L);
        assertEquals(1234L, full_list.get(0));
    }

    @org.junit.jupiter.api.Test
    void append() {
        full_list.append(20_001L);
        assertEquals(20_001L, full_list.get(20_000L));
        assertEquals(20_001L, full_list.size());
    }

    @org.junit.jupiter.api.Test
    void set() {
        assertEquals(2345L, full_list.get(2344L));
        full_list.set(2344L, 0);
        assertEquals(0, full_list.get(2344L));
    }

    @org.junit.jupiter.api.Test
    void seek() {
        // Is this really needed?
    }

    @org.junit.jupiter.api.Test
    void prev() {
        // Is this really needed?
    }

    @org.junit.jupiter.api.Test
    void next() {
        // Is this really needed?
    }

    @Nested
    class EmptyLongLinkedListTest {
        @org.junit.jupiter.api.BeforeEach
        void setUp() {
            empty_list = new LongLinkedList<>();
        }

        @org.junit.jupiter.api.AfterEach
        void tearDown() {
            empty_list = null;
        }

        @org.junit.jupiter.api.Test
        void empty() {
            assertTrue(empty_list.empty());
        }

        @Test
        void size() {
            assertEquals(0, empty_list.size());
        }

        @Test
        void peek() {
            assertNull(empty_list.peek());
        }

        @Test
        void pop() {
            assertNull(empty_list.pop());
        }

        @Test
        void last() {
            assertNull(empty_list.last());
        }

        @Test
        void get() {
            assertNull(empty_list.get(0));
        }

        @Test
        void push() {
            assertTrue(empty_list.push(1));
        }

        @Test
        void append() {
            assertTrue(empty_list.push(1));
        }

        @Test
        void set() {
            assertFalse(empty_list.set(0, 1));
        }
    }
}