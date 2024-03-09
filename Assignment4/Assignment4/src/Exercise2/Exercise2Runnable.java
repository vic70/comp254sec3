package Exercise2;

public class Exercise2Runnable {
        public static void main(String[] args) {

            Exercise2.StackArray<Integer> s = new Exercise2.StackArray<>();
            Exercise2.StackArray<Integer> t = new Exercise2.StackArray<>();

            s.push(1);
            s.push(2);
            s.push(3);
            s.push(4);
            s.push(5);
            s.push(6);
            s.push(7);
            s.push(8);
            s.push(9);
            s.printStack();
            //System.out.println("Pop:"+s.pop());
            System.out.println("S: "+s.printStack());
            System.out.println("T: "+t.printStack());

            System.out.println("After transfer:");
            Exercise2.StackArray.Transfer(s,t);
            System.out.println("S: "+s.printStack());
            System.out.print("T: "+t.printStack());

        }
}
