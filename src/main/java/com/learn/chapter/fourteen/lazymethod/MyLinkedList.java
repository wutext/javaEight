package com.learn.chapter.fourteen.lazymethod;

public class MyLinkedList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
