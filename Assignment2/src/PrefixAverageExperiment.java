/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */

class PrefixAverageExperiment {

  /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
  public static double[] prefixAverage1(double[] x) {
    int n = x.length;
    double[] a = new double[n];    // filled with zeros by default
    for (int j=0; j < n; j++) {
      double total = 0;            // begin computing x[0] + ... + x[j]
      for (int i=0; i <= j; i++)
        total += x[i];
      a[j] = total / (j+1);        // record the average
    }
    return a;
  }

  /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
  public static double[] prefixAverage2(double[] x) {
    int n = x.length;
    double[] a = new double[n];    // filled with zeros by default
    double total = 0;              // compute prefix sum as x[0] + x[1] + ...
    for (int j=0; j < n; j++) {
      total += x[j];               // update prefix sum to include x[j]
      a[j] = total / (j+1);        // compute average based on current sum
    }
    return a;
  }


  public static void main(String[] args) {
    int n = 50000;                           // starting value
    int trials2 = 10;
    int trials1 = 5;
    try {
      if (args.length > 0)
        trials2 = trials1 = Integer.parseInt(args[0]);
      if (args.length > 1)
        n = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) { }
    int start = n;  // remember the original starting value

    // let's run version 2 (the quicker one) first
    System.out.printf("Testing prefixAverage2 for %d trials...\n", trials2);
    for (int t=0; t < trials2; t++) {

      //assign a small constant value to each element of the array
      double[] testArray = new double[n];
      for (int j=0; j <n; j++){
        testArray[j] = 0.0000001d;
      }
      
      //testing
      long startTime = System.currentTimeMillis();
      double[] temp = prefixAverage2(testArray);
      long endTime = System.currentTimeMillis();
      long elapsed = endTime - startTime;
      System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
      n *= 2;                                // double the problem size
    }

    System.out.printf("Testing prefixAverage1 for %d trials...\n", trials1);
    n = start;                               // restore n to its start value
    for (int t=0; t < trials1; t++) {

      //assign a small constant value to each element of the array
      double[] testArray = new double[n];
      for (int j=0; j <n; j++){
        testArray[j] = 0.0000001d;
      }
      
      //testing
      long startTime = System.currentTimeMillis();
      double[] temp = prefixAverage1(testArray);
      long endTime = System.currentTimeMillis();
      long elapsed = endTime - startTime;
      System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
      n *= 2;                                // double the problem size
    }
  }

}

/*output:
Testing prefixAverage2 for 10 trials...
n:     50000 took            1 milliseconds
n:    100000 took            1 milliseconds
n:    200000 took            1 milliseconds
n:    400000 took            4 milliseconds
n:    800000 took            8 milliseconds
n:   1600000 took           17 milliseconds
n:   3200000 took           33 milliseconds
n:   6400000 took           43 milliseconds
n:  12800000 took          128 milliseconds
n:  25600000 took          276 milliseconds
Testing prefixAverage1 for 5 trials...
n:     50000 took         2447 milliseconds
n:    100000 took         6465 milliseconds
n:    200000 took        30402 milliseconds
n:    400000 took       105882 milliseconds
n:    800000 took       392048 milliseconds
 */