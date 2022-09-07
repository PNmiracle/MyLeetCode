package ch05队列_栈解题精讲.b_队列的经典习题;

import java.util.NoSuchElementException;

/**
 * @Author mapKey
 * @Date 2022-08-29-9:11 AM
 */
// 底层用数组实现队列
class ArrayQueue<E> {
    int size;
    private E[] data;
    private final static int INIT_CAP = 2;
    //[first, last)
    private int first, last;

    public ArrayQueue(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        first = last = 0;
    }

    public ArrayQueue() {
        // 不传参数，默认大小为 INIT_CAP
        this(INIT_CAP);
    }

    /* 增 */
    public void enqueue(E e) {
        if (size == data.length) {
            resize(size * 2);
        }

        data[last] = e;
        last++;
        if (last == data.length) {
            last = 0;
        }

        size++;
    }

    /* 删 */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        E oldVal = data[first];
        data[first] = null;
        first++;
        if (first == data.length) {
            first = 0;
        }

        size--;
        return oldVal;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];

        // first ----- last
        // --- last    first ---

        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }

        first = 0;
        last = size;
        data = temp;
    }

    /* 查 */
    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[first];
    }

    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (last == 0) return data[data.length - 1];
        return data[last - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

class MyCircularQueue {

    ArrayQueue<Integer> q;
    int maxCap;

    public MyCircularQueue(int k) {
        q = new ArrayQueue<>(k);
        maxCap = k;
    }

    public boolean enQueue(int value) {
        if (q.size() == maxCap) {
            return false;
        }
        q.enqueue(value);
        return true;
    }

    public boolean deQueue() {
        if (q.isEmpty()) {
            return false;
        }
        q.dequeue();
        return true;
    }

    public int Front() {
        if (q.isEmpty()) {
            return -1;
        }
        return q.peekFirst();
    }

    public int Rear() {
        if (q.isEmpty()) {
            return -1;
        }
        return q.peekLast();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public boolean isFull() {
        return q.size() == maxCap;
    }
}


class ArrayQueue1<E> {
    private int size;
    private E[] data;
    private final static int INIT_CAP = 2;

    private int first, last;

    public ArrayQueue1(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        first = last = 0;
    }

    public ArrayQueue1() {
        this(INIT_CAP);
    }

    public void enqueue(E e) {
        if (size == data.length) {
            resize(size * 2);
        }

        data[last] = e;
        last++;

        if (last == data.length) {
            last = 0;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        E oldVal = data[first];
        data[first] = null;
        first++;

        if (first == data.length) {
            first = 0;
        }

        size--;
        return oldVal;
    }

    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[first];
    }

    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (last == 0) {
            return data[data.length - 1];
        }

        return data[last - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }

        first = 0;
        last = size;
        data = temp;
    }

    public int size() {
        return size;
    }
}

class MyCircularQueue1 {
    private ArrayQueue1<Integer> q;
    private int maxCap;

    public MyCircularQueue1(int k) {
        q = new ArrayQueue1<>(k);
        maxCap = k;
    }

    public boolean enQueue(int value) {
        if (q.size() == maxCap) {
            return false;
        }
        q.enqueue(value);
        return true;
    }

    public boolean deQueue() {
        if (q.isEmpty()) {
            return false;
        }
        q.dequeue();
        return true;
    }
}