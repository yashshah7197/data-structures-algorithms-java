package stack;

public interface Stack<E> {
    boolean empty();

    E peek();

    E pop();

    E push(E element);

    int search(E element);

    int size();
}
