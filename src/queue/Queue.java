package queue;

public interface Queue<E> {
    boolean add(E element);

    E element();

    boolean isEmpty();

    boolean offer(E element);

    E peek();

    E poll();

    E remove();

    int size();
}
