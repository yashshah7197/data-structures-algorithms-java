package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CircularLinkedList implements a circular linked list.
 *
 * @param <E> is the type of elements that the list will hold.
 */
public class CircularLinkedList<E> implements List<E>, Iterable<E> {
    private int size;
    private Node<E> sentinelHead;

    /**
     * Constructs a new CircularLinkedList.
     */
    public CircularLinkedList() {
        size = 0;
        sentinelHead = new Node<>(null, null, null);
        sentinelHead.previous = sentinelHead;
        sentinelHead.next = sentinelHead;
    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param element the element to be added.
     * @return true if the element is added successfully
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        Node<E> newNode = new Node<>(element, sentinelHead.previous, sentinelHead);
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size += 1;

        return true;
    }

    /**
     * Adds the given element at the specified index in the list.
     *
     * @param index   the index in the list where the element is to be added.
     * @param element the element to be added.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node<E> newNode = new Node<>(element, current.previous, current);
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size += 1;
    }

    /**
     * Adds the given element to the beginning of the list.
     *
     * @param element the element to be added.
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param element the element to be added.
     */
    public void addLast(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        Node<E> newNode = new Node<>(element, sentinelHead.previous, sentinelHead);
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size += 1;
    }

    /**
     * Clear the entire list.
     */
    @Override
    public void clear() {
        size = 0;
        sentinelHead = new Node<>(null, null, null);
        sentinelHead.previous = sentinelHead;
        sentinelHead.next = sentinelHead;
    }

    /**
     * Checks if the given element exists within the list.
     *
     * @param element the element to be checked.
     * @return true if the element exists within the list, false otherwise.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be checked cannot be null!");
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < size; i++) {
            if (current.data.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Retrieves the element at the specified index from the list.
     *
     * @param index the index from which to retrieve the element.
     * @return the element at the specified index.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Retrieves the first element of the list.
     *
     * @return the first element of the list.
     */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return sentinelHead.next.data;
    }

    /**
     * Retrieves the last element of the list.
     *
     * @return the last element of the list.
     */
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return sentinelHead.previous.data;
    }

    /**
     * Retrieves the index of the given element if it exists in the list.
     *
     * @param element the element whose index is to be retrieved.
     * @return the index of the given element if it exists in the list, -1 otherwise.
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be checked cannot be null!");
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < size; i++) {
            if (current.data.equals(element)) {
                return i;
            }

            current = current.next;
        }

        return -1;
    }

    /**
     * Check if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return an iterator for the list.
     *
     * @return an iterator for the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    /**
     * Removes the element at the specified index from the list.
     *
     * @param index the index in the list at which the element is to be removed.
     * @return the element currently at the specified index before removal.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.previous.next = current.next;
        current.next.previous = current.previous;

        size -= 1;

        return current.data;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to be removed.
     * @return true if the element is removed successfully, false otherwise.
     */
    @Override
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be removed cannot be null!");
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < size; i++) {
            if (current.data.equals(element)) {
                current.previous.next = current.next;
                current.next.previous = current.previous;

                size -= 1;

                return true;
            }
        }

        return false;
    }

    /**
     * Removes the first element from the list.
     *
     * @return the element currently first in the list.
     */
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return remove(0);
    }

    /**
     * Removes the last element from the list.
     *
     * @return the element currently last in the list.
     */
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<E> nodeToRemove = sentinelHead.previous;

        nodeToRemove.previous.next = nodeToRemove.next;
        nodeToRemove.next.previous = nodeToRemove.previous;

        size -= 1;

        return nodeToRemove.data;
    }

    /**
     * Sets the value of the given index in the list to the specified element.
     *
     * @param index   the index at which the element is to be set.
     * @param element the element to be set at the specified index.
     * @return the element currently at the specified index.
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new IllegalArgumentException("The element to be seet cannot be null!");
        }

        Node<E> current = sentinelHead.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E data = current.data;
        current.data = element;

        return data;
    }

    /**
     * Get the current size of the list.
     *
     * @return the current size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * CircularLinkedListIterator is an iterator for CircularLinkedList.
     */
    private class CircularLinkedListIterator implements Iterator<E> {
        private Node<E> current;

        private CircularLinkedListIterator() {
            current = sentinelHead.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinelHead;
        }

        @Override
        public E next() {
            if (current == sentinelHead) {
                throw new NoSuchElementException();
            }

            E data = current.data;
            current = current.next;

            return data;
        }
    }

    /**
     * Node represents a node in a doubly linked list.
     *
     * @param <E> the type of data that the node will hold.
     */
    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        private Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
}
