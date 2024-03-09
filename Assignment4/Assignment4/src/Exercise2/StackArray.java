package Exercise2;

import java.util.NoSuchElementException;

public class StackArray<T>{
    private T[] stack;
    private int size;
    private int top;

    public StackArray(){
        stack = (T[]) new Object[1];
        size = 0;
        top = -1;
    }

    public void resize(int capacity) {
        if (top + 1 <= capacity) {
            T[] temp = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, temp, 0, top + 1);
            stack = temp;
        }
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void push(T item){
        if (top == stack.length -1){
            this.resize(2 * size);
        }
        stack[++top] = item;
        size++;
    }


    public T pop(){
        if(this.isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        T popItem = stack[top];
        stack[top--] = null; // Remove reference to the popped item to avoid memory leaks


        size --;

        if(size>0 && stack.length/2 > top) {
            this.resize(stack.length / 2);
        }

        return popItem;
    }

    public T peek(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return stack[top];
    }

    public StringBuilder printStack(){
        if (isEmpty()){
            return new StringBuilder("Empty stack");
        }
        StringBuilder str = new StringBuilder();
        for (int i =0; i<=top; i++){
            str.append(stack[i]).append(" ");
        }
        return str;
    }

    public static <T> void Transfer (StackArray<T> sStack, StackArray<T> tStack){

        if (!tStack.isEmpty()){
            throw new IllegalArgumentException("Output stack must be empty");
        }

        while(!sStack.isEmpty()){
            tStack.push(sStack.pop());
        }
    }

}







