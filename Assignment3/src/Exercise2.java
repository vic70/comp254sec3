import java.util.Scanner;
public class Exercise2 {

    public static boolean palindromeChecker(String s){
        int n = s.length();
        if (n==1 || n==0)
            return true;
        else
        {
            if (s.charAt(0) != s.charAt(n-1))
                return false;
            else
                if (n==2)
                    return true;
                else
                    return palindromeChecker(s.substring(1,n-1));

        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string:");
        String s = sc.nextLine();
        System.out.printf("%s is %sa palindrome.",s, palindromeChecker(s)?"":"not ");
    }

}
