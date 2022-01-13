package stack;

import list.ArrayList;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayListStack implements a stack backed by an ArrayList.
 *
 * @param <E> the type of elements that the stack will hold.
 */
public class ArrayListStack<E> implements Stack<E>, Iterable<E> {
    private final ArrayList<E> arrayList;

    /**
     * Constructs a new ArrayListStack.
     */
    public ArrayListStack() {
        arrayList = new ArrayList<>();
    }

    /**
     * Check if the stack is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean empty() {
        return arrayList.isEmpty();
    }

    /**
     * Return an iterator for the stack.
     *
     * @return an iterator for the stack.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayListStackIterator();
    }

    /**
     * Return the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack.
     */
    @Override
    public E peek() {
        if (arrayList.isEmpty()) {
            throw new EmptyStackException();
        }

        return arrayList.get(arrayList.size() - 1);
    }

    /**
     * Remove the element at the top of the stack and return it.
     *
     * @return the element at the top of the stack.
     */
    @Override
    public E pop() {
        if (arrayList.isEmpty()) {
            throw new EmptyStackException();
        }

        return arrayList.remove(arrayList.size() - 1);
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

        arrayList.add(element);

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

        int index = arrayList.indexOf(element);

        if (index == -1) {
            return -1;
        }

        return arrayList.size() - index;
    }

    /**
     * Get the current size of the stack.
     *
     * @return the current size of the stack.
     */
    @Override
    public int size() {
        return arrayList.size();
    }

    /**
     * ArrayListStackIterator is an iterator for ArrayListStack.
     */
    private class ArrayListStackIterator implements Iterator<E> {
        private int currentIndex;

        public ArrayListStackIterator() {
            currentIndex = arrayList.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            }

            currentIndex -= 1;

            return arrayList.get(currentIndex + 1);
        }
    }
}
