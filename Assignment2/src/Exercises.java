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
 * Code for end-of-chapter exercises on asymptotics.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Exercises {

  /** Returns the sum of the integers in given array. */
  public static int example1(int[] arr) {
    int n = arr.length, total = 0;                                // 4 ops
    for (int j=0; j < n; j++)       // loop from 0 to n-1         // 2n ops
      total += arr[j];                                            // 2n ops
    return total;                                                 // 1 op            
  }

  //example1 has 4 + 2n + 2n + 1 = 4n + 5 ops = O(n)

  /** Returns the sum of the integers with even index in given array. */
  public static int example2(int[] arr) {
    int n = arr.length, total = 0;                                 // 4 ops    
    for (int j=0; j < n; j += 2)    // note the increment of 2    //n/2*2 = n ops
      total += arr[j];                                            //n/2*2 = n ops                
    return total;                                                //1 op                                                        
  }

  //example2 has 4 + n + n + 1= 2n + 5 ops = O(n)

  /** Returns the sum of the prefix sums of given array. */
  public static int example3(int[] arr) {
    int n = arr.length, total = 0;                                // 4 ops  
    for (int j=0; j < n; j++)       // loop from 0 to n-1        // 2n ops
      for (int k=0; k <= j; k++)    // loop from 0 to j         // 2*(1+ 2+ 3+...+n) = 2*n(n+1)/2 = n(n+1) ops  
        total += arr[j];                                        // 2*n(n+1)/2 = n(n+1) ops
    return total;                                              // 1 op
  }

  //example3 has 4 + 2n + n(n+1) + n(n+1) + 1 = 2n^2 + 4n + 5 ops = O(n^2)

  /** Returns the sum of the prefix sums of given array. */
  public static int example4(int[] arr) {
    int n = arr.length, prefix = 0, total = 0;                 // 3*2 = 6 ops 
    for (int j=0; j < n; j++) {     // loop from 0 to n-1     // 2n ops
      prefix += arr[j];                                       // 2n ops
      total += prefix;                                       // n ops     
    }
    return total;                                           // 1 op
  }

  //example4 has 6 + 2n + 2n + n + 1 = 5n + 7 ops = O(n)

  

  /** Returns the number of times second array stores sum of prefix sums from first. */
  public static int example5(int[] first, int[] second) { // assume equal-length arrays
    int n = first.length, count = 0;                              // 4 ops
    for (int i=0; i < n; i++) {     // loop from 0 to n-1       // 2n ops
      int total = 0;                                            // 2n ops                    
      for (int j=0; j < n; j++)     // loop from 0 to n-1       //2n^2 ops
        for (int k=0; k <= j; k++)  // loop from 0 to j         //2* n*(1+2+3+...+n) = 2*n^2(n+1)/2 = n^3+n^2 ops
          total += first[k];                                    //2* n*(1+2+3+...+n) = n^3+n^2 ops
      if (second[i] == total) count++;                          // 2n to 3n ops  (1 for array index, 1 for comparison, 1 for increment if true)
    }                                                            
    return count;                                             // 1 op         
  }

}

  //example5 has 4 + 2n + 2n + 2n^2 + (n^3 + n^2)*2 + (2n to 3n) +1  
  // worse case = 2n^3 + 4n^2 + 3n + 5 ops = O(n^3)