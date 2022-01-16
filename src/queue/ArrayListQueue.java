package queue;

public class ArrayListQueue<E> implements Queue<E> {
    @Override
    public boolean add(E element) {
        return false;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean offer(E element) {
        return false;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
