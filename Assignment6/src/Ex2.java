public class Ex2 {
    public static void main(String[] args) {

        SortedTableMap<Integer, String> map = new SortedTableMap<>();

        map.put(1, "A");
        map.put(2, null);
        map.put(3, "C");

        System.out.println("Get key 2: " + map.get(1)+", contains key 2: "+map.containKey(1));
        System.out.println("Get key 2: " + map.get(2)+", contains key 2: "+map.containKey(2));
        System.out.println("Get key 2: " + map.get(4)+", contains key 2: "+map.containKey(4));
    }
}
