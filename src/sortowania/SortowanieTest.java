/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortowania;

import java.util.Random;

/**
 *
 * @author Paweł
 */
public class SortowanieTest {
        
    
    
    public static void main(String[] args) {
        
        int startArrayLength = 100;
        int incrementNextArrayLength = 200;
        int numberRepeatsOfSortingArrays = 10;
        boolean printsArrays = false;
        boolean printTimesInCSV = true;
        
        System.out.println("###########################Sortowanie przez wstawianie##################################\n");
        System.out.println("Sortowanie tablicy randomowej: ");
        testujSortowanie("insert", "random", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        
        
        System.out.println("Sortowanie tablicy optymistycznej (posrotowanej): ");
        testujSortowanie("insert", "best", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        

        System.out.println("Sortowanie tablicy pesimistycznej (posrotowanej w odwrotym kierunku): ");
        testujSortowanie("insert", "worst", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        
        System.out.println("###########################Sortowanie przez scalanie##################################\n");
        System.out.println("Sortowanie tablicy randomowej: ");
        testujSortowanie("merge", "random", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        
        System.out.println("Sortowanie tablicy optymistycznej (posrotowanej): ");
        testujSortowanie("merge", "best", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        

        System.out.println("Sortowanie tablicy pesimistycznej (porozrzucanej według specjalnego algorytmu): ");
        testujSortowanie("merge", "worst", startArrayLength, numberRepeatsOfSortingArrays, incrementNextArrayLength, printsArrays, printTimesInCSV);
        System.out.println();
        
        
    }
    
    private static void testujSortowanie(String typSortowania, String wybranyPrzypadek, int arrayLength, int numberRepeatsOfSortingArrays, int incrementNextArrayLength,
                                         boolean printsArrays, boolean printTimesInCSV) {
        for(int n = 0; n < numberRepeatsOfSortingArrays; n++) {
        
            double[] tablicaWartosci = new double[arrayLength];
            
            if (wybranyPrzypadek.equalsIgnoreCase("random")) {
                Random rnd = new Random();
                for (int i = 0; i < arrayLength; i++) {
                    tablicaWartosci[i] = rnd.nextDouble();
                }
            } else if(wybranyPrzypadek.equalsIgnoreCase("best")) {
               
                for (int i = 0; i < arrayLength; i++) {
                    tablicaWartosci[i] = i*1.32;
                } 
                
            } else if(wybranyPrzypadek.equalsIgnoreCase("worst")) {
                if (typSortowania.equalsIgnoreCase("insert")) {
                    for (int i = 0; i < arrayLength; i++) {
                        tablicaWartosci[i] = arrayLength - i;
                    }
                } else if (typSortowania.equalsIgnoreCase("merge")) {
                    //make sorted array
                    for (int i = 0; i < arrayLength; i++) {
                        tablicaWartosci[i] = i;
                    }
                    makeMergeWorstCaseArr(tablicaWartosci);
                    
                } else {
                    System.out.println("Podano zły typ sortowania, dostępne to merge i insert!");
                    return;
                }
            } else {
                System.out.println("Wybrano zły przypadek sortowania, dostępne to random, best i worst");
                    return;
            }
            
            if (printsArrays) {
                System.out.println("Tablica przed: ");
                for (double elem : tablicaWartosci) {
                    System.out.println("    " + elem);
                }
            }
            
            long estimatedTime;
            if (typSortowania.equalsIgnoreCase("insert")) {
                InsertSort insertSort = new InsertSort();
                System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
                long startTime = System.nanoTime();
                tablicaWartosci = insertSort.sort(tablicaWartosci);
                estimatedTime = System.nanoTime() - startTime;
                
            } else if( typSortowania.equalsIgnoreCase("merge")) {
                InsertSort insertSort = new InsertSort();
                System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
                long startTime = System.nanoTime();
                tablicaWartosci = insertSort.sort(tablicaWartosci);
                estimatedTime = System.nanoTime() - startTime;
            } else {
                System.out.println("Błąd przy rozpoznawaniu nazwy algorytmu sortowania!");
                return;
            }
            
            if (printsArrays) {
                System.out.println("Tablica po: ");
                for (double elem : tablicaWartosci) {
                    System.out.println("    " + elem);
                }
            }
            
            if( printTimesInCSV )
                System.out.println(arrayLength + "; " + estimatedTime);
            else
                System.out.println("Czas sortowania tablicy " + typSortowania + " " + arrayLength + " elementowej to: " + estimatedTime + " nanosekonds");
            
            arrayLength = arrayLength + incrementNextArrayLength;
        }
    }
    

    
    public static void makeMergeWorstCaseArr(double[] arr) { 

            if(arr.length<=1)
                return;

            if(arr.length==2)
            {
                double swap=arr[0];
                arr[0]=arr[1];
                arr[1]=swap;
                return;
            }

        int i,j;
        int m = (arr.length + 1) / 2;
        double left[] = new double[m];
        double right[] = new double[arr.length-m];

        for(i=0,j=0;i<arr.length;i=i+2,j++) //Storing alternate elements in left subarray
            left[j]=arr[i];

        for(i=1,j=0;i<arr.length;i=i+2,j++) //Storing alternate elements in right subarray
            right[j]=arr[i];

        makeMergeWorstCaseArr(left);
        makeMergeWorstCaseArr(right);
        mergeTwoarrIntoOne(arr, left, right);
    }
    
     public static void mergeTwoarrIntoOne(double[] arr, double[] left, double[] right) {
        int i,j;
        for(i=0;i<left.length;i++)
                arr[i]=left[i];
        for(j=0;j<right.length;j++,i++)
                arr[i]=right[j];
    }
}
