/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortowania;

import java.util.Random;

/**
 *
 * @author DRAPIEWP
 */
public class InsertSort implements SortingAlgorithm {
    
    @Override
    public double[] sort(double[] unsortedVector) {
        for(int i = 0; i < unsortedVector.length; i++) {
            double tmp = unsortedVector[i];
            
            int j;
            for(j= i - 1; j >= 0 && tmp < unsortedVector[j]; j-- ) {
                unsortedVector[j+1] = unsortedVector[j];
            }
            unsortedVector[j+1] = tmp;
        }
        
        return unsortedVector;
    }
    
    public static void main(String[] args) {
        
        int startArrayLength = 100;
        int incrementNextArrayLength = 100;
        int numberRepeatsOfSortingArrays = 2;
        boolean printsArrays = false;
        boolean printTimesInCSV = true;
        
        System.out.println("Sortowanie tablicy randomowej: ");
        int arrayLength = startArrayLength;
        for(int n = 0; n < numberRepeatsOfSortingArrays; n++) {
        
            double[] tablicaInsert = new double[arrayLength];
            Random rnd = new Random();

            for (int i = 0; i < arrayLength; i++) {
                tablicaInsert[i] = rnd.nextDouble();
            }
            
            if (printsArrays) {
                System.out.println("Tablica insert przed: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            InsertSort insertSort = new InsertSort();
            
            System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
            long startTime = System.nanoTime();
            tablicaInsert = insertSort.sort(tablicaInsert);
            long estimatedTime = System.nanoTime() - startTime;

            if (printsArrays) {
                System.out.println("Tablica insert po: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            if( printTimesInCSV )
                System.out.println(arrayLength + "; " + estimatedTime);
            else
                System.out.println("Czas sortowania insert tablicy " + arrayLength + " elementowej to: " + estimatedTime + " nanosekonds");
            
            arrayLength = arrayLength + incrementNextArrayLength;
        }
        System.out.println();
        
     
        //najlepszy przypadek (tablica jest już posortowana)
        System.out.println("Sortowanie tablicy optymistycznej (posrotowanej): ");
        arrayLength = startArrayLength;
        for(int n = 0; n < numberRepeatsOfSortingArrays; n++) {
            
            double[] tablicaInsert = new double[arrayLength];

            for (int i = 0; i < arrayLength; i++) {
                tablicaInsert[i] = i*1.32;
            }
            
            if (printsArrays) {
                System.out.println("Tablica insert przed: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            InsertSort insertSort = new InsertSort();
            
            System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
            long startTime = System.nanoTime();
            tablicaInsert = insertSort.sort(tablicaInsert);
            long estimatedTime = System.nanoTime() - startTime;

            if (printsArrays) {
                System.out.println("Tablica insert po: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            if( printTimesInCSV )
                System.out.println(arrayLength + "; " + estimatedTime);
            else
                System.out.println("Czas sortowania insert tablicy " + arrayLength + " elementowej to: " + estimatedTime + " nanosekonds");
            
            arrayLength = arrayLength + incrementNextArrayLength;
        }
        System.out.println();
        
        
        //najgorszy przypadek (tablica jest już posortowana odwrotnie)
        System.out.println("Sortowanie tablicy pesimistycznej (posrotowanej w odwrotym kierunku): ");
        arrayLength = startArrayLength;
        for(int n = 0; n < numberRepeatsOfSortingArrays; n++) {
            
            double[] tablicaInsert = new double[arrayLength];

            for (int i = 0; i < arrayLength; i++) {
                tablicaInsert[i] = arrayLength - i;
            }
            
            if (printsArrays) {
                System.out.println("Tablica insert przed: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            InsertSort insertSort = new InsertSort();
            
            System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
            long startTime = System.nanoTime();
            tablicaInsert = insertSort.sort(tablicaInsert);
            long estimatedTime = System.nanoTime() - startTime;

            if (printsArrays) {
                System.out.println("Tablica insert po: ");
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            if( printTimesInCSV )
                System.out.println(arrayLength + "; " + estimatedTime);
            else
                System.out.println("Czas sortowania insert tablicy " + arrayLength + " elementowej to: " + estimatedTime + " nanosekonds");
            
            arrayLength = arrayLength + incrementNextArrayLength;
        }
        System.out.println();
        
    }
 
}
