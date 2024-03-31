import java.util.Random;

public class Ex1 {
    public static void main(String[] args) {

        int numKeys = 100000;
        Integer[] keys = genRandomKeys(numKeys);
        double loadFactors;

        //chainHashMap
        for (int i = 1; i < 10; i++) {
            loadFactors = i / 10.0;
            ChainHashMap<Integer, Integer> chainHashMap = new ChainHashMap<>(loadFactors);
            long insertionTime = InsertionTime(chainHashMap, keys);
            System.out.printf("Insertion time for ChainHashMap with load factor %.1f: %d ms\n", loadFactors, insertionTime);
        }

        System.out.println("-------------------------------------------------");

        //probeHashMap
        for (int i = 1; i < 10; i++) {
            loadFactors = i / 10.0;
            ProbeHashMap<Integer, Integer> probeHashMap = new ProbeHashMap<>(loadFactors);
            long insertionTime = InsertionTime(probeHashMap, keys);
            System.out.printf("Insertion time for ProbeHashMap with load factor %.1f: %d ms\n", loadFactors, insertionTime);
        }

    }

    public static Integer[] genRandomKeys(int numKeys) {

        Random random = new Random();
        Integer[] keys = new Integer[numKeys];
        for (int i = 0; i < numKeys; i++) {
            keys[i] = random.nextInt();
        }
        return keys;
    }

    public static long InsertionTime(AbstractHashMap<Integer, Integer> map, Integer[] keys) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < keys.length; i++) {
            map.put(i, i);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


}