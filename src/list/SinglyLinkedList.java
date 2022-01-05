package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SinglyLinkedList implements a singly linked list.
 *
 * @param <E> is the type of elements that the list will hold.
 */
public class SinglyLinkedList<E> implements List<E>, Iterable<E> {
    private int size;
    private Node<E> sentinelHead;
    private Node<E> tail;

    /**
     * Constructs a new SinglyLinkedList.
     */
    private SinglyLinkedList() {
        size = 0;
        sentinelHead = new Node<>(null, null);
        tail = sentinelHead;
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

        tail.next = new Node<>(element, null);
        tail = tail.next;

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

        Node<E> current = sentinelHead;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.next = new Node<>(element, current.next);

        size += 1;
    }

    /**
     * Adds the given element to the beginning of the list.
     *
     * @param element the element to be added.
     */
    public void addFirst(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        sentinelHead.next = new Node<>(element, sentinelHead.next);
        size += 1;
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

        Node<E> newNode = new Node<>(element, null);
        tail.next = newNode;
        tail = newNode;

        size += 1;
    }

    /**
     * Clear the entire list.
     */
    @Override
    public void clear() {
        size = 0;
        sentinelHead = new Node<>(null, null);
        tail = sentinelHead;
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

        return tail.data;
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
        return new SinglyLinkedListIterator();
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

        Node<E> current = sentinelHead;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E data = current.next.data;
        current.next = current.next.next;

        if (index == size - 1) {
            tail = current;
        }

        size -= 1;

        return data;
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

        Node<E> current = sentinelHead;

        for (int i = 0; i < size; i++) {
            if (current.next.data.equals(element)) {
                if (current.next == tail) {
                    tail = current;
                }

                current.next = current.next.next;
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

        return remove(size - 1);
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
            throw new IllegalArgumentException("The element to be set cannot be null!");
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
     * SinglyLinkedListIterator is an iterator for SinglyLinkedList.
     */
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> current;

        private SinglyLinkedListIterator() {
            current = sentinelHead.next;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            E data = current.data;
            current = current.next;

            return data;
        }
    }

    /**
     * Node represents a node in a singly linked list.
     *
     * @param <E> the type of data that the node will hold.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
