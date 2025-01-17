/**
 * Name: Tyler May, Reese Whitlock
 * ID: A16792035, A17074829
 * Email: tjmay@ucsd.edu, rwhitlock@ucsd.edu
 * Sources used: Coding done jointly, Week 4 Quiz
 * This file ontains the generic class MyLinkedList, which was modified by us.
 * It also contains the given protected class Node.
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** 
 * This class contains all the methods needed to modify and interpret linked
 * lists. Important instance variables include int size, Node head,
 * and Node tail.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	static final String INVALID_INDEX = "Invalid index used.";
	static final String NULL_DATA = "Data cannot be null.";

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		this.size = 0;
		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Gets size of list
	 * @return size of list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Gets the element at a given index
	 * @param index - index to get element from
	 * @return returns element at given index
	 */
	@Override
	public E get(int index) {
		if(index < 0){
			throw new IndexOutOfBoundsException(INVALID_INDEX);
		}
		if(index >= this.size()){
			throw new IndexOutOfBoundsException(
				INVALID_INDEX);
		}
		Node curNode = this.head;
		for(int i = -1; i < index; i++){
			curNode = curNode.getNext();
		}
		return (E) curNode.getElement();
	}

	/**
	 * Adds node at given index
	 * @param index - index to add node
	 * @param data - data for the new node
	 */
	@Override
	public void add(int index, E data) {
		if(data == null){
			throw new NullPointerException(NULL_DATA);
		}
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException(INVALID_INDEX);
		}

		Node curNode = this.head;
		for(int i = 0; i < index; i++){
			curNode = curNode.getNext();
		}
		Node newNode = new Node(data);

		//Code adapted from Week 4 Discussion quiz Q11.1
		curNode.next.prev = newNode;
		newNode.next = curNode.getNext();
		newNode.prev = curNode;
		curNode.next = newNode;

		this.size++;
	}

	/**
	 * Adds node to the end of the list
	 * @param data - data for the new node
	 * @return true if successful, false if otherwise
	 */
	public boolean add(E data) {
		if(data == null){
			throw new NullPointerException(NULL_DATA);
		}
		Node curNode = this.tail;
		curNode = curNode.getPrev();
		Node newNode = new Node(data);

		//Code adapted from Week 4 Discussion quiz Q11.1 (again)
		curNode.next.prev = newNode;
		newNode.next = curNode.getNext();
		newNode.prev = curNode;
		curNode.next = newNode;

		this.size++;

		return true;
	}
	/**
	 * Sets a node to a different value
	 * @param index - index of changed element
	 * @param data - value to change data to
	 */
	public E set(int index, E data) {
		if(data == null){
			throw new NullPointerException(NULL_DATA);
		}
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException(INVALID_INDEX);
		}
		Node curNode = this.head;
		for(int i = -1; i < index; i++){
			curNode = curNode.getNext();
		}
		E prevElement = curNode.getElement();
		curNode.setElement(data);
		return prevElement;
	}
	
	/**
	 * Removes node at specified index
	 * @param index - index to remove node
	 * @return element that was removed
	 */
	public E remove(int index) {
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(INVALID_INDEX);
		}

		Node curNode = this.head;
		for(int i = -1; i < index; i++){
			curNode = curNode.getNext();
		}

		//Code adapted from Week 4 Discussion quiz Q11.1
		curNode.next.prev = curNode.getPrev();
		curNode.prev.next = curNode.getNext();

		this.size--;
		return (E) curNode.getElement();
	}

	/**
	 * Removes all nodes from list
	 */
	public void clear() {
		this.size = 0;
		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Determines if list is empty
	 * @return true if list is empty, return false otherwise
	 */
	public boolean isEmpty() {
		if(this.size == 0){
			return true; 
		} else {
			return false;
		}
	}

	/**
	 * Gets node at specified index
	 * @param index - index to get node
	 * @return node
	 */
	protected Node getNth(int index) {
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException(INVALID_INDEX);
		}
		Node curNode = this.head;
		for(int i = -1; i < index; i++){
			curNode = curNode.getNext();
		}
		return curNode;
	}

	protected class MyListIterator implements ListIterator<E> {	

        // class variables here
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

		//  Implementation of MyListIterator class at index 0
		public MyListIterator() {
			left = head;
			right = head.getNext();
			idx = 0;
			forward = true;
			canRemoveOrSet = false;
		}

		/**
	 	* Determines if the iterator can move forward
	 	* @return true if the iterator can move forward, false if it can't
	 	*/
        public boolean hasNext() {
			if(right.getElement() == null){
				return false;
			}
			return true;
        }

		/**
	 	* Moves the iterator one index to the right
		* @return element of the node iterated over
		*/
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			E returnElement = right.getElement();
			idx++;
			left = left.getNext();
			right = right.getNext();
			forward = true;
			canRemoveOrSet = true;
			return returnElement;
		}

		/**
	 	* Moves the iterator one index to the right
		* @return true if the iterator can move backwards, false if it can't
		*/
		public boolean hasPrevious() {
			if(left.getElement() == null){
				return false;
			}
			return true;
		}

		/**
	 	* Moves the iterator one index to the left
		* @return element of the node iterated over
		*/
		public E previous() {
			if(!hasPrevious()){
				throw new NoSuchElementException();
			}
			E returnElement = left.getElement();
			idx--;
			left = left.getPrev();
			right = right.getPrev();
			forward = false;
			canRemoveOrSet = true;
			return returnElement;
		}

		/**
		* @return the current index
		*/
		public int nextIndex() {
			return idx;
		}

		/**
		* @return the current index - 1
		*/
		public int previousIndex() {
			return idx -1;
		}

		/**
		* Adds node at the index of the iterator, moves the iterator forward
		* @param element is the data for the new node
	 	*/
		public void add(E element){

			Node newNode = new Node(element);

			right.setPrev(newNode);
			newNode.setNext(right);
			newNode.setPrev(left);
			left.setNext(newNode);

			idx++;
			left = newNode;
			canRemoveOrSet = false;
		}

		/**
		* Changes the data of left node if going forward, right if going
		* backwards
		* @param element is the data for changed node
	 	*/
		public void set(E element) {
			if(element == null){
				throw new NullPointerException();
			}

			if(!canRemoveOrSet){
				throw new IllegalStateException();
			}

			if(forward){
				left.setElement(element);
			} else {
				right.setElement(element);
			}
		}

		/**
		* removes the left node if going forward, removes the right node if
		* going backwards
	 	*/
		public void remove() {
			if(!canRemoveOrSet) {
				throw new IllegalStateException();
			}

			if(forward) {
				Node newLeft = left.getPrev();
				right.setPrev(left.getPrev());
				left.getPrev().setNext(right);
				left = newLeft;
				idx--;
			} else {
				Node newRight = left.getPrev();
				right.getNext().setPrev(left);
				left.setNext(right.getNext());
				right = newRight;
			}

			size--;
			canRemoveOrSet = false;
		}
	}
}