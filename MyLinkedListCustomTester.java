
/**
 * Name: Tyler May, Reese Whitlock
 * ID: A16792035, A17074829
 * Email: tjmay@ucsd.edu, rwhitlock@ucsd.edu
 * Sources used: Coding done jointly, Week 4 Quiz
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * File containing class MyLinkedListCustomTester. Has custom tests that
 * MyLinkedListPublicTester.java does not contain.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * Tests MyLinkedList.java and the methods within. Sets up two MyLinkedLists
 * to do this: empyList and listLen3, with corresponding MyListIterators.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList emptyList, listLen3;
    private MyLinkedList.MyListIterator emptyListIter, listLen3Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        emptyList = new MyLinkedList();
        emptyListIter = emptyList.new MyListIterator();

        listLen3 = new MyLinkedList();
        listLen3.add(1);
        listLen3.add(2);
        listLen3.add(3);
        listLen3Iter = listLen3.new MyListIterator();
    }

    /**
     * Test hasNext() on empty list and on listLen3 as it iterates through the
     * list
     */
    @Test
    public void testHasNext() {
        // Check if iterator hasNext on empty list
        assertEquals(false, emptyListIter.hasNext());

        // Checks hasNext as iterator moves through size 3 list
        assertEquals(true, listLen3Iter.hasNext());
        listLen3Iter.next();
        assertEquals(true, listLen3Iter.hasNext());
        listLen3Iter.next();
        assertEquals(true, listLen3Iter.hasNext());
        listLen3Iter.next();
        assertEquals(false, listLen3Iter.hasNext());
    }

    /**
     * Test next method on empty list and iterating through list
     */
    @Test
    public void testNext() {
        // Checks if exception is thrown when trying to move next in an empty list
        boolean test = false;
        try {
            emptyListIter.next();
        } catch (NoSuchElementException E) {
            test = true;
        }
        assertTrue(test);

        /**
         * Checks to see if next() is returning the correct element as it
         * iterates through ListLen3
         */
        assertEquals(1, listLen3Iter.next());
        assertEquals(2, listLen3Iter.next());
        assertEquals(3, listLen3Iter.next());
        test = false;
        try {
            listLen3Iter.next();
        } catch (NoSuchElementException E) {
            test = true;
        }
        assertTrue(test);
    }

    /**
     * Test hasNext() on empty list and on listLen3 as it iterates through the
     * list
     */
    @Test
    public void testHasPrevious() {
        // Check if iterator hasPrev on empty list
        assertEquals(false, emptyListIter.hasPrevious());

        // Checks hasPrev as iterator moves through size 3 list
        assertEquals(false, listLen3Iter.hasPrevious());
        listLen3Iter.next();
        assertEquals(true, listLen3Iter.hasPrevious());
        listLen3Iter.next();
        assertEquals(true, listLen3Iter.hasPrevious());
        listLen3Iter.next();
        assertEquals(true, listLen3Iter.hasPrevious());
    }

    /**
     * Test previous method on empty list and iterating through list
     */
    @Test
    public void testPrevious() {
        // Checks if exception is thrown when trying to move next in an empty list
        boolean test = false;
        try {
            emptyListIter.previous();
        } catch (NoSuchElementException E) {
            test = true;
        }
        assertTrue(test);

        /**
         * Checks to see if next() is returning the correct element as it
         * iterates through ListLen3
         */
        listLen3Iter.next();
        listLen3Iter.next();
        listLen3Iter.next();
        assertEquals(3, listLen3Iter.previous());
        assertEquals(2, listLen3Iter.previous());
        assertEquals(1, listLen3Iter.previous());
        test = false;
        try {
            listLen3Iter.previous();
        } catch (NoSuchElementException E) {
            test = true;
        }
        assertTrue(test);
    }

    /**
     * Test nextIndex method on an empty list and while iterating through list
     */
    @Test
    public void testNextIndex() {
        assertEquals(0, emptyListIter.nextIndex());

        assertEquals(0, listLen3Iter.nextIndex());
        listLen3Iter.next();
        assertEquals(1, listLen3Iter.nextIndex());
        listLen3Iter.next();
        assertEquals(2, listLen3Iter.nextIndex());
        listLen3Iter.next();
        assertEquals(3, listLen3Iter.nextIndex());               
    }

    /**
     * Test previousIndex method on an empty list and while iterating backwards
     */
    @Test
    public void testPreviousIndex() {
        //empty list
        assertEquals(-1, emptyListIter.previousIndex());

        //calling previousIndex() while iterating backwards
        listLen3Iter.next();
        listLen3Iter.next();
        listLen3Iter.next();
        assertEquals(2, listLen3Iter.previousIndex());
        listLen3Iter.previous();
        assertEquals(1, listLen3Iter.previousIndex());
        listLen3Iter.previous();
        assertEquals(0, listLen3Iter.previousIndex());
        listLen3Iter.previous();
        assertEquals(-1, listLen3Iter.previousIndex());
    }

    /**
     * Test the set method before any next/previous calls
     */
    @Test
    public void testSet() {
        boolean testFail = false;
        listLen3Iter.forward = true;
        try {
            listLen3Iter.set(0);
        } catch(IllegalStateException E) {
            testFail = true;
        }
        assertTrue(testFail);
    }

    /**
     * Test the remove method when the list is empty and forward = true
     */
    @Test
    public void testRemoveTestOne() {
        boolean testFail = false;
        emptyListIter.forward = true;
        try {
            emptyListIter.remove();
        } catch(IllegalStateException E) {
            testFail = true;
        }
        assertTrue(testFail);
    }

    /**
     * Test the remove method when the list is empty and forward = false
     */
    @Test
    public void testRemoveTestTwo() {
        boolean testFail = false;
        emptyListIter.forward = false;
        try {
            emptyListIter.remove();
        } catch(IllegalStateException E) {
            testFail = true;
        }
        assertTrue(testFail);
    }

    /**
     * Test the add method when the list is empty or at the end of the list
     */
    @Test
    public void testAdd() {
        emptyListIter.add(0);
        assertEquals(0, emptyListIter.left.getElement());
        assertEquals(emptyList.tail.getElement(), 
                emptyListIter.right.getElement());

        listLen3Iter.left = listLen3.tail.getPrev();
        listLen3Iter.right = listLen3.tail;
        listLen3Iter.idx = 3;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;

        listLen3Iter.add(4);
        assertEquals(4, listLen3Iter.left.getElement());
        assertEquals(listLen3.tail.getElement(), 
                listLen3Iter.right.getElement());
        assertEquals(4, listLen3Iter.idx);
    }
}