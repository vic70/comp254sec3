package Exercise1;

public class Ex1Runnable {
    public static void main(String[] args) {
        LinkedPositionalList<Integer> positionalList = new LinkedPositionalList<>();

        Position<Integer> p = positionalList.addLast(1);
        Position<Integer> q = positionalList.addLast(2);
        Position<Integer> r = positionalList.addFirst(3);
        Position<Integer> s = positionalList.addAfter(r, 4);
        Position<Integer> t = positionalList.addBefore(p, 5);

        positionalList.remove(r);

        System.out.println("PositionalList: " + positionalList.toString());
        System.out.println("Content of p: "+ p.getElement());
        System.out.println("Return of method indexOf(p) is:"+ positionalList.indexOf(p)+" (0 base)");

    }

}
