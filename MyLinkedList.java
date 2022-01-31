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
			throw new IndexOutOfBoundsException("Index cannot be less than 0.");
		}
		if(index >= this.size()){
			throw new IndexOutOfBoundsException(
				"Index cannot be equal to or greater than size of list.");
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
			throw new NullPointerException("data cannot be null");
		}
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("Index cannot exceed size or be less than 0");
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
			throw new NullPointerException("data cannot be null");
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
			throw new NullPointerException("data cannot be null");
		}
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index cannot exceed size or be less than 0");
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
			throw new IndexOutOfBoundsException("Index cannot exceed size or be less than 0");
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
			throw new IndexOutOfBoundsException("Index cannot exceed size or be less than 0");
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

		public MyListIterator() {
			left = head;
			right = head.getNext();
			idx = 0;
			forward = true;
			canRemoveOrSet = false;
		}

        public boolean hasNext() {
			if(right.getElement() == null){
				return false;
			}
			return true;
        }

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

	}
}