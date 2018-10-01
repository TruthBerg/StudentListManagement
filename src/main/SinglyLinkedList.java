/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main;

/**
 * A basic singly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SinglyLinkedList<E> implements Cloneable {
	// ---------------- nested Node class ----------------
	/**
	 * Node of a singly linked list, which stores a reference to its element and to
	 * the subsequent node in the list (or null if this is the last node).
	 */
	private static class Node<E> {

		/** The element stored at this node */
		private E element; // reference to the element stored at this node

		/** A reference to the subsequent node in the list */
		private Node<E> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e
		 *            the element to be stored
		 * @param n
		 *            reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// Accessor methods
		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getElement() {
			return element;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return next;
		}

		// Modifier methods
		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n
		 *            the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
	} // ----------- end of nested Node class -----------

	// instance variables of the SinglyLinkedList
	/** The head node of the list */
	private Node<E> head = null; // head node of the list (or null if empty)

	/** The last node of the list */
	private Node<E> tail = null; // last node of the list (or null if empty)

	/** Number of nodes in the list */
	private int size = 0; // number of nodes in the list

	/** Constructs an initially empty list. */
	public SinglyLinkedList() {
	} // constructs an initially empty list

	// access methods
	/**
	 * Returns the number of elements in the linked list.
	 * 
	 * @return number of elements in the linked list
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether the linked list is empty.
	 * 
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns (but does not remove) the first element of the list
	 * 
	 * @return element at the front of the list (or null if empty)
	 */
	public E first() { // returns (but does not remove) the first element
		if (isEmpty())
			return null;
		return head.getElement();
	}

	/**
	 * Returns (but does not remove) the last element of the list.
	 * 
	 * @return element at the end of the list (or null if empty)
	 */
	public E last() { // returns (but does not remove) the last element
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	// update methods
	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param e
	 *            the new element to add
	 */
	public void addFirst(E e) { // adds element e to the front of the list
		head = new Node<>(e, head); // create and link a new node
		if (size == 0)
			tail = head; // special case: new node becomes tail also
		size++;
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param e
	 *            the new element to add
	 */
	public void addLast(E e) { // adds element e to the end of the list
		Node<E> newest = new Node<>(e, null); // node will eventually be the tail
		if (isEmpty())
			head = newest; // special case: previously empty list
		else
			tail.setNext(newest); // new node after existing tail
		tail = newest; // new node becomes the tail
		size++;
	}
	/**
	 * NEW IN HOMEWORK 3:Å
	 * Searches for an element in the list and returns it if it exists.
	 * 
	 * @param e the element to find
	 * @return the element (or null if it does not exist)
	 */
	public E find(E e) {
		if (isEmpty()) 
			return null;
		Node<E> walk = head;
		while (walk != null) {
			if(e.equals(walk.getElement())) { 	// element found
				return walk.getElement();	
			}
			walk = walk.next;		
		}
		return null;	// element not found
	}

	/**
	 * Removes and returns the first element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeFirst() { // removes and returns the first element
		if (isEmpty())
			return null; // nothing to remove
		E answer = head.getElement();
		head = head.getNext(); // will become null if list had only one node
		size--;
		if (size == 0)
			tail = null; // special case as list is now empty
		return answer;
	}

	/**
	 * NEW IN HOMEWORK 3:
	 *  Removes and returns the last element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeLast() {
		if (isEmpty())
			return null; // nothing to remove
		E element = tail.getElement(); // get the value in the tail
		if (head == tail) { // no previous element, so make list empty
			head = null;
			tail = null;
		} else { // find the node previous to the tail to update its pointer
			Node<E> prevToTail = head;
			while (prevToTail.next != tail) {
				prevToTail = prevToTail.next;
			}
			// update its pointer to null and make it the tail
			prevToTail.next = null;
			prevToTail = tail;
		}
		size--;
		return element;
	}

	/**
	 * NEW IN HOMEWORK 3:
	 * Removes and returns a given element (such as a Student object) in the list
	 * 
	 * @param element the element to remove
	 * @return the removed element (or null if empty)
	 */
	public E remove(E element) {

		if (isEmpty())
			return null;

		if (element.equals(head.getElement())) { // Node with matching element is the head
			return removeFirst();
		}

		if (element.equals(tail.getElement())) { // Node with matching element is the tail
			return removeLast();
		}

		E removedElement = null;
		Node<E> currNode = head.next;
		Node<E> prevNode = head;

		while (currNode != tail) {

			if (element.equals(currNode.getElement())) {
				removedElement = currNode.getElement();
				prevNode.next = currNode.next; // Update pointers: prev to next
				size--;
			}

			// Node not found, so keep searching...
			prevNode = currNode; // Update previous and current nodes
			currNode = currNode.next;
		}

		// done searching, so return removed element
		return removedElement;
	}

	/**
	 * NEW IN HOMEWORK 3:
	 * Compare equality of two students by checking the value of their studentIDs
	 * 
	 * @return true if studentIDs are equal, false otherwise
	 */
	@SuppressWarnings({ "unchecked" })
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) o; // use nonparameterized type
		if (size != other.size)
			return false;
		Node<E> walkA = head; // traverse the primary list
		Node<E> walkB = other.head; // traverse the secondary list
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false; // mismatch
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true; // if we reach this, everything matched successfully
	}

	@SuppressWarnings({ "unchecked" })
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		// always use inherited Object.clone() to create the initial copy
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
		if (size > 0) { // we need independent chain of nodes
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext(); // walk through remainder of original list
			Node<E> otherTail = other.head; // remember most recently created node
			while (walk != null) { // make a new node storing same element
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest); // link previous node to this one
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}

	public int hashCode() {
		int h = 0;
		for (Node<E> walk = head; walk != null; walk = walk.getNext()) {
			h ^= walk.getElement().hashCode(); // bitwise exclusive-or with element's code
			h = (h << 5) | (h >>> 27); // 5-bit cyclic shift of composite code
		}
		return h;
	}

	/**
	 * MODIFIED IN HOMEWORK 3:
	 * Produces a string representation of the contents of the list. 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Node<E> walk = head;
		while (walk != null) {
			sb.append(walk.getElement());
			if (walk != tail)
				sb.append("\n");
			walk = walk.getNext();
		}
		sb.append("\n");
		return sb.toString();
	}

}
