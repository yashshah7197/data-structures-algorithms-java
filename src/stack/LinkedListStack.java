package stack;

import list.SinglyLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedListStack implements a stack backed by a SinglyLinkedList.
 *
 * @param <E> the type of elements that the stack will hold.
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {
    private final SinglyLinkedList<E> linkedList;

    /**
     * Constructs a new LinkedListStack.
     */
    public LinkedListStack() {
        linkedList = new SinglyLinkedList<E>();
    }

    /**
     * Check if the stack is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean empty() {
        return linkedList.isEmpty();
    }

    /**
     * Return an iterator for the stack.
     *
     * @return an iterator for the stack.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListStackIterator();
    }

    /**
     * Return the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack.
     */
    @Override
    public E peek() {
        if (linkedList.isEmpty()) {
            throw new EmptyStackException();
        }

        return linkedList.getFirst();
    }

    /**
     * Remove the element at the top of the stack and return it.
     *
     * @return the element at the top of the stack.
     */
    @Override
    public E pop() {
        if (linkedList.isEmpty()) {
            throw new EmptyStackException();
        }

        return linkedList.removeFirst();
    }

    /**
     * Push an element on to the top of the stack.
     *
     * @param element the element to be pushed on to the top of the stack.
     * @return the element that was pushed on to the top of the stack.
     */
    @Override
    public E push(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the stack!");
        }

        linkedList.addFirst(element);

        return element;
    }

    /**
     * Check if the given element exists within the stack.
     *
     * @param element the element to be searched for in the stack.
     * @return the distance from the top of the stack if the element exists in the stack, -1 otherwise.
     */
    @Override
    public int search(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be searched cannot be null!");
        }

        int index = linkedList.indexOf(element);

        if (index == -1) {
            return -1;
        }

        return index + 1;
    }

    /**
     * Get the current size of the stack.
     *
     * @return the current size of the stack.
     */
    @Override
    public int size() {
        return linkedList.size();
    }

    /**
     * LinkedListStackIterator is an iterator for LinkedListStack.
     */
    private class LinkedListStackIterator implements Iterator<E> {
        private final Iterator<E> linkedListIterator;

        private LinkedListStackIterator() {
            linkedListIterator = linkedList.iterator();
        }

        @Override
        public boolean hasNext() {
            return linkedListIterator.hasNext();
        }

        @Override
        public E next() {
            if (!linkedListIterator.hasNext()) {
                throw new NoSuchElementException();
            }

            return linkedListIterator.next();
        }
    }
}
