package Exercise3;

public class Ex3Runnable {
    public static void main(String[] args) {
        LinkedQueue<Integer> Q1 = new LinkedQueue<>();
        LinkedQueue<Integer> Q2 = new LinkedQueue<>();

        Q1.enqueue(1);
        Q1.enqueue(2);
        Q1.enqueue(3);
        Q1.enqueue(4);
        Q1.enqueue(5);

        displayConcatenate(Q2, Q1);
        displayConcatenate(Q2, Q1);


        Q1.enqueue(11);
        Q1.enqueue(12);
        Q1.enqueue(13);
        Q1.enqueue(14);
        Q1.enqueue(15);

        displayConcatenate(Q1, Q2);



    }

    private static void displayConcatenate(LinkedQueue<Integer> q1, LinkedQueue<Integer> q2) {
        System.out.println("Q1: "+ q1.printQueue());
        System.out.println("Q2: "+ q2.printQueue());

        System.out.println("After concatenate:");
        q1.concatenate(q2);
        System.out.println("Q1: "+ q1.printQueue());
        System.out.println("Q2: "+ q2.printQueue());
        System.out.println("-------------------");
    }
}
