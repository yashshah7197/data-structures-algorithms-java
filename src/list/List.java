package list;

import java.util.Iterator;

public interface List<E> {
    boolean add(E element);

    void add(int index, E element);

    void clear();

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    Iterator<E> iterator();

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    int size();
}
