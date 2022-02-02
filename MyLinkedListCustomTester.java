
/**
 * TODO: Add your file header
 * Name: Tyler May, Reese Whitlock
 * ID: A16792035, A17074829
 * Email: tjmay@ucsd.edu, rwhitlock@ucsd.edu
 * Sources used: Coding done jointly, Week 4 Quiz
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
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
        // Check if iterator has next on empty list
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
     * TODO: test the next method when [...]
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
     * TODO: test the hasPrevious method when [fill in another one here]
     */
    @Test
    public void testHasPrevious() {
        // Check if iterator has next on empty list
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
     * TODO: test the previous method when [...]
     */
    @Test
    public void testPrevious() {

    }

    /**
     * TODO: test the nextIndex method when [...]
     */
    @Test
    public void testNextIndex() {

    }

    /**
     * TODO: test the previousIndex method when [...]
     */
    @Test
    public void testPreviousIndex() {

    }

    /**
     * TODO: test the set method when [...]
     */
    @Test
    public void testSet() {

    }

    /**
     * TODO: test the remove method when [...]
     */
    @Test
    public void testRemoveTestOne() {

    }

    /**
     * TODO: test the remove method when [fill in another one here]
     */
    @Test
    public void testRemoveTestTwo() {

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