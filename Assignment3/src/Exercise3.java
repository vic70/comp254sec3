import java.util.Scanner;
import java.io.File;
public class Exercise3 {
    public static void find(File path, String filename){
        if(path.isDirectory()){
            for (String childname: path.list()){
                //System.out.println(childname);
                File child = new File(path, childname);
                if(child.isDirectory()){
                    find(child, filename);
                } else if (childname.equals(filename)) {
                    System.out.println(child.getAbsolutePath());
                }

            }
        }
    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        String pathString;
        String filename;

        System.out.print("Enter the path to search with: ");
        pathString = sc.nextLine();
        System.out.print("Enter the target filename:");
        filename = sc.nextLine();
//        pathString = "C:\\CentennialCode\\COMP254DataStructure\\Assignment3\\Ex3Test";
//        filename="test.txt";

        find(new File(pathString), filename);
    }
}
