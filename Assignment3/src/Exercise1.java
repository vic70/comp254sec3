public class Exercise1 {

    public static int multiplier (int m, int n){
        if(n== 0){
            return 0;
        }
        else
            return m + multiplier(m, n-1);
    }

    public static void main(String[] args)
    {
        int m = 5;
        int n = 6;
        System.out.printf("%d time %d equal to %d",m,n, multiplier(m,n));
    }

}
