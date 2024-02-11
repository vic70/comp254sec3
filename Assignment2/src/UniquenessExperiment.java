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

import java.util.Arrays;

/**
 * Demonstration of algorithms for testing element uniqueness.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class UniquenessExperiment {

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique1(int[] data) {
    int n = data.length;
    for (int j=0; j < n-1; j++)
      for (int k=j+1; k < n; k++)
        if (data[j] == data[k])
          return false;                    // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique2(int[] data) {
    int n = data.length;
    int[] temp = Arrays.copyOf(data, n);   // make copy of data
    Arrays.sort(temp);                     // and sort the copy
    for (int j=0; j < n-1; j++)
      if (temp[j] == temp[j+1])            // check neighboring entries
        return false;                      // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }


  public static void main(String[] args) throws Exception{
    
    //Remarks: function will call once to warn up the JVM
        //From the test reults, the first run time is always the largest
        //Trial: 0 Runtime: 1007
        // Trial: 1 Runtime: 594
        // Trial: 2 Runtime: 621
        // Trial: 3 Runtime: 596
        //May due to JIT compiler feature




    int nInit = 100000;   //Assume runtime of nInit is less than 1 second, otherwise throw exception

    int repeatTrials = 5; //number of trials for repeating runtime test for each n

    long targetRunTime = 1000*60; // 1 minute in milliseconds
    int nMin, nMax=-1, ntarget;   //nMax is -1 to indicate that it has not been set yet

    long runtimes[] = new long[repeatTrials];

    long startTime, endTime, avgRuntime;

    nMin = nInit;
    //find the nMin that will run in less than 1 second and nMax that will run in more than 1 second
    int[] data;

    double increaseFactor = 2;
//*******************************************************************************

    // for unique2, out of heap space when nTest >=1100000000
    // Run time of nTest is 1000000000  is 1446ms<1s, therefore bottlenect at heap memory
    /*
     * MaxInt: 1000000000
      nMax: 1000000000 runTime: 1446
     */
    /*
    * (base) PS C:\CentennialCode\COMP254DataStructure\Assignment2> javac -d .\bin\ .\src\UniquenessExperiment.java ; if ($?) { java -Xmx8192m -cp .\bin\ UniquenessExperiment }
    MaxInt: 1100000000
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at java.base/java.lang.Object.clone(Native Method)
            at java.base/java.util.Arrays.copyOf(Arrays.java:3593)
            at UniquenessExperiment.unique2(UniquenessExperiment.java:48)
            at UniquenessExperiment.main(UniquenessExperiment.java:145)
    * 
    */


    //run time of nTest

    int nTest = 1000000000;// Integer.MAX_VALUE;
    System.out.println("MaxInt: " + nTest);

    data = new int[nTest];
    for (int i = 0; i < nTest; i++){
      data[i] = i;
     }

     unique2(data);
    // unique2(data);

    startTime = System.currentTimeMillis();
    unique2(data);
    endTime = System.currentTimeMillis();
    long runTimeTest = endTime - startTime; 
    System.out.println("For unique2, nTest: " + nTest + " runTime: " + runTimeTest);

    System.out.println("Unique2 bottlenect at heap memory, cannot increase nTest further for 8GB heap memory");

    //java -Xmx8192m -cp .\bin\ UniquenessExperiment
    //******************************************************************************

     // for unique1    
    
    while (true) {
      data = new int[nMin];

      //program run time is largest when there is not duplicate
      for (int i = 0; i < nMin; i++){
        data[i] = i;
      }

      
      unique1(data); //warn up the JVM
      avgRuntime = 0;
      for (int i = 0; i < repeatTrials; i++){
        startTime = System.currentTimeMillis();
        unique1(data);
        endTime = System.currentTimeMillis();
        runtimes[i] = endTime - startTime;
        avgRuntime += runtimes[i];
        System.out.println("Trial: "+ i + " Runtime: "+ runtimes[i] );
      }      
      avgRuntime = avgRuntime / repeatTrials; 


      System.out.println("nMin: " + nMin + " runTime: " + avgRuntime);

      
      if (avgRuntime < targetRunTime){
        nMin = (int) (nMin * increaseFactor); 
        nMax = nMin; 
      }
      else{
        if (nMax == -1){
          //throw taylar exception
          throw new Exception("initial n is too large");
        }
        else{
          nMax = nMin;
          nMin = (int) (nMin / increaseFactor);
          break;
        }
        
      }            
    }


    System.out.println("Initial: nMin: " + nMin+ " nMax: " + nMax);
    ntarget = (nMin+nMax)/2;   
    System.out.println(ntarget);
    


    //find the max n that will run in less than targetRunTime
    while (nMax - nMin > 10000){      //there are still more than 10000 n to test with, not 1 due to variation in running time, 10000 is a around 1 second of error
      ntarget = (nMin+nMax)/2;    
      data = new int[ntarget];
      //program run time is largest when there is not duplicate
      for (int i = 0; i < ntarget; i++){
        data[i] = i;
      }


      unique1(data); //warn up the JVM
      avgRuntime = 0;
      for (int i = 0; i < repeatTrials; i++){
        startTime = System.currentTimeMillis();
        unique1(data);
        endTime = System.currentTimeMillis();
        runtimes[i] = endTime - startTime;
        avgRuntime += runtimes[i];
        System.out.println("Trial: "+ i + " Runtime: "+ runtimes[i] );
      }      
      avgRuntime = avgRuntime / repeatTrials; 

      
      System.out.println("nMin: " + nMin+ " nMax: " + nMax + " nTarget: " + ntarget+" averagerunTime: " + avgRuntime);    

      if (avgRuntime < targetRunTime){
        nMin = ntarget;
      } else {
        nMax = ntarget;
      } 

    }

    //System.out.println("final: nMin: " + nMin+ " nMax: " + nMax);
    System.out.printf("For unique1, largest n that run in less than %d second is: %d\n", targetRunTime/1000, nMin);

    /*Output for unique1
     * 
     * Trial: 0 Runtime: 906
Trial: 1 Runtime: 1030
Trial: 2 Runtime: 1233
Trial: 3 Runtime: 1220
Trial: 4 Runtime: 1142
nMin: 100000 runTime: 1106
Trial: 0 Runtime: 4445
Trial: 1 Runtime: 3311
Trial: 2 Runtime: 4440
Trial: 3 Runtime: 4714
Trial: 4 Runtime: 5125
nMin: 200000 runTime: 4407
Trial: 0 Runtime: 26500
Trial: 1 Runtime: 27798
Trial: 2 Runtime: 31763
Trial: 3 Runtime: 38556
Trial: 4 Runtime: 30656
nMin: 400000 runTime: 31054
Trial: 0 Runtime: 116759
Trial: 1 Runtime: 109281
Trial: 2 Runtime: 129339
Trial: 3 Runtime: 106985
Trial: 4 Runtime: 108326
nMin: 800000 runTime: 114138
Initial: nMin: 400000 nMax: 800000
600000
Trial: 0 Runtime: 34599
Trial: 1 Runtime: 35315
Trial: 2 Runtime: 34160
Trial: 3 Runtime: 35075
Trial: 4 Runtime: 34742
nMin: 400000 nMax: 800000 nTarget: 600000 averagerunTime: 34778
Trial: 0 Runtime: 45340
Trial: 1 Runtime: 47803
Trial: 2 Runtime: 46583
Trial: 3 Runtime: 46255
Trial: 4 Runtime: 46706
nMin: 600000 nMax: 800000 nTarget: 700000 averagerunTime: 46537
Trial: 0 Runtime: 104977
Trial: 1 Runtime: 97493
Trial: 2 Runtime: 95856
Trial: 3 Runtime: 97471
Trial: 4 Runtime: 105181
nMin: 700000 nMax: 800000 nTarget: 750000 averagerunTime: 100195
Trial: 0 Runtime: 97140
Trial: 1 Runtime: 97825
Trial: 2 Runtime: 94031
Trial: 3 Runtime: 90680
Trial: 4 Runtime: 92937
nMin: 700000 nMax: 750000 nTarget: 725000 averagerunTime: 94522
Trial: 0 Runtime: 91412
Trial: 1 Runtime: 93674
Trial: 2 Runtime: 87370
Trial: 3 Runtime: 88865
Trial: 4 Runtime: 88502
nMin: 700000 nMax: 725000 nTarget: 712500 averagerunTime: 89964
Trial: 0 Runtime: 85268
Trial: 1 Runtime: 88643
Trial: 2 Runtime: 90032
Trial: 3 Runtime: 87638
Trial: 4 Runtime: 90254
nMin: 700000 nMax: 712500 nTarget: 706250 averagerunTime: 88367
For unique1, largest n that run in less than 60 second is: 700000
     */

   }

}
