package Exercise3;

import java.util.NoSuchElementException;

public class LinkedQueue<T> {

    private class Node<T> {
        private T item;
        private Node<T> next;
    }
    private Node<T> first, last;

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(T item){
        Node<T> oldNode = last;
        last = new Node<T>();
        last.item = item;
        last.next = null;

        if (isEmpty()){
            first = last;
        } else {
            oldNode.next = last;
        }
    }

    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is Empty");
        }

        T item = first.item;

        first = first.next;

        if(isEmpty()){
            last = null;
        }

        return item;
    }

    public int size () {
        int count = 0;
        Node<T> current = first;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public String printQueue(){
        StringBuilder str = new StringBuilder();
        if(isEmpty()){
            str.append("Empty Queue");
        } else {
            Node<T> current = first;
            while (current != null)
            {
                str.append(current.item.toString()).append(" ");
                current = current.next;
            }

        }

        return str.toString();
    }

    public void concatenate (LinkedQueue<T> Q2){
        if(Q2.isEmpty()){
            return;
        }


        if(isEmpty()){
            this.first = Q2.first;
        } else {
            this.last.next = Q2.first;
        }
        this.last=Q2.last;

        Q2.first = null;
        Q2.last = null;
    }


}





