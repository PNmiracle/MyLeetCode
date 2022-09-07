package ch02_动手实现ArrayList和LinkedList;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @Author mapKey
 * @Date 8/18/2022 8:45 PM
 * @Since version-1.0
 * @Description
 * 双链表实现MyLinkedList
 */
class MyLinkedList_Double<E> implements Iterable<E> {
    // 虚拟头尾节点, 相当于两个占位符
    final private Node<E> head, tail;
    //实际存放的元素个数
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化头尾节点
    public MyLinkedList_Double() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    /***** 增 *****/

    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        // temp <-> tail
        temp.next = x;
        x.prev = temp;

        x.next = tail;
        tail.prev = x;
        // temp <-> x <-> tail
        size++;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        // head <-> temp
        temp.prev = x;
        x.next = temp;

        head.next = x;
        x.prev = head;
        // head <-> x <-> temp
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(element);
            return;
        }

        // 找到 index 对应的 Node
        Node<E> p = getNode(index);
        Node<E> temp = p.prev;
        // temp <-> p

        // 新要插入的 Node
        Node<E> x = new Node<>(element);

        p.prev = x;
        temp.next = x;

        x.prev = temp;
        x.next = p;

        // temp <-> x <-> p

        size++;
    }

    /***** 删 *****/

    public E removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        // 虚拟节点的存在是我们不用考虑空指针的问题
        Node<E> x = head.next;
        Node<E> temp = x.next;
        // head <-> x <-> temp
        head.next = temp;
        temp.prev = head;

        x.prev = null;
        x.next = null;
        // head <-> temp

        size--;
        return x.val;
    }

    public E removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> temp = tail.prev.prev;
        // temp <-> x <-> tail

        tail.prev = temp;
        temp.next = tail;

        x.prev = null;
        x.next = null;
        // temp <-> tail

        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> x = getNode(index);
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        // prev <-> x <-> next
        prev.next = next;
        next.prev = prev;

        x.prev = x.next = null;
        // prev <-> next

        size--;

        return x.val;
    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        return p.val;
    }

    public E getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return head.next.val;
    }

    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return tail.prev.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = val;

        return oldVal;
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p;
    }
    private Node<E> getNode_my(int index) {
        checkElementIndex(index);


        Node<E> p = null;
        if (index < (size >> 1)) {
            p = head.next;
            // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail.prev;
            for (int i = size - 1; i > index; i--){
                p = p.prev;
            }
        }

        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
        System.out.println();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

}

// 单链表实现
class MyLinkedList_Single<E> {

    // 单链表链表节点
    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    // 构造函数初始化头尾节点
    public MyLinkedList_Single() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;

        this.size = 0;
    }

    /***** 增 *****/

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;

        // head -> temp
        x.next = temp;
        head.next = x;
        // head -> x -> temp

        size++;
    }

    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp;
        if (size - 1 >= 0) {
            temp = getNode(size - 1);
        } else {
            temp = head;
        }

        // temp -> tail
        x.next = tail;
        temp.next = x;
        // temp -> x -> tail

        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        // 判断特殊情况
        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> x = new Node<>(element);
        Node<E> p = getNode(index);
        Node<E> temp;
        if (index - 1 >= 0) {
            temp = getNode(index - 1);
        } else {
            temp = head;
        }

        // temp -> p
        x.next = p;
        temp.next = x;
        // temp -> x -> p

        size++;
    }

    /***** 删 *****/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> x = head.next;
        // head -> x -> ...
        head.next = head.next.next;
        x.next = null;
        // head -> ...

        size--;

        return x.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> x = getNode(size - 1);
        Node<E> temp;
        if (size - 2 >= 0) {
            temp = getNode(size - 2);
        } else {
            temp = head;
        }
        // temp -> x -> tail
        temp.next = tail;
        x.next = null;
        // temp -> tail

        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        Node<E> p = getNode(index);
        Node<E> prev;
        if (index - 1 >= 0) {
            prev = getNode(index - 1);
        } else {
            prev = head;
        }

        Node<E> next = p.next;
        // prev -> p -> next
        prev.next = next;
        p.next = null;
        // prev -> next

        size--;
        return p.val;
    }

    /***** 查 *****/

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(size - 1).val;
    }

    public E get(int index) {
        checkElementIndex(index);
        Node<E> p = getNode(index);
        return p.val;
    }

    /***** 改 *****/

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = element;

        return oldVal;
    }

    /***** 其他工具函数 *****/
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // 返回 index 对应的 Node
    // 注意：请保证传入的 index 是合法的
    private Node<E> getNode(int index) {
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }
}