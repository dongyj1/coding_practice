package impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by dyj on 4/10/18.
 */
class PriorityQueueImpl <E> implements MyPriorityQueue {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private transient Object[] queue;
    private int size = 0;
    private final Comparator<? super E> comparator;
    private transient int modCount = 0;

    public PriorityQueueImpl(Comparator<? super E> comparator) {
        this.comparator = comparator;

    }

    private void siftUp(int k, Object o) {
        if (comparator != null) {
            siftUpUsingComparator(k, o);
        } else {
            siftUpComparable(k, o);
        }
    }

    private void siftUpUsingComparator(int k, Object o) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare((E)o, (E)e) >= 0) {
                break;
            }
            // sift parent down
            queue[k] = e;
            k = parent;
        }
        queue[k] = (E) o;
    }


    private void siftUpComparable(int k, Object o) {
        Comparable<? super E> key = (Comparable<? super E>) o;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E)e) >= 0) {
                break;
            }
            // sift parent down
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    private void grow(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        int oldCapacity = queue.length;
        // double the capacity
        int newCapacity = oldCapacity + oldCapacity;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void siftDown(int k, Object o) {
        if (comparator != null) {
            siftDownUsingComparator(k, o);
        } else {
            siftDownComparable(k, o);
        }
    }

    private void siftDownUsingComparator(int k, Object o) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E)queue[right]) > 0) {
                c = queue[child = right];
            }
            if (comparator.compare((E) o, (E)c) <= 0) {
                break;
            }
            queue[k] = c;
            k = child;
        }
        queue[k] = o;
    }

    private void siftDownComparable(int k, Object o) {
        Comparable<? super E> key = (Comparable<? super E>) o;
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E)queue[right]) > 0) {
                c = queue[child = right];
            }
            if (key.compareTo((E)c) <= 0) {
                break;
            }
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i, queue[i]);
        }
    }

    @Override
    public boolean add(Object o) {
        return offer(o);
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < size; i++) {
            queue[i] = null;
        }
        size = 0;
    }

    @Override
    public Comparator comparator() {
        return comparator;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        if (o == null) {
            return false;
        }
        modCount++;
        // check size
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        size = i + 1;
        if (i == 0) {
            queue[i] = o;
        } else {
            siftUp(i, o);
        }
        return true;
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }


    @Override
    public int size() {
        return 0;
    }
}
