package impl.deadlock;

import java.util.Comparator;

/**
 * Created by dyj on 4/10/18.
 */
public interface MyPriorityQueue <E> {
    boolean add(E e);

    void clear();

    Comparator<? super E> comparator();

    boolean contains(Object o);

    boolean offer(E e);

    E peek();

    E poll();

    boolean remove(E e);

    int size();
}
