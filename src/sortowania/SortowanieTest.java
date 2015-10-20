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
        
    }
    
    private static void testujSortowanie(String typSortowania, String wybranyPrzypadek, int arrayLength, int numberRepeatsOfSortingArrays, int incrementNextArrayLength,
                                         boolean printsArrays, boolean printTimesInCSV) {
        for(int n = 0; n < numberRepeatsOfSortingArrays; n++) {
        
            double[] tablicaInsert = new double[arrayLength];
            
            if (wybranyPrzypadek.equalsIgnoreCase("random")) {
                Random rnd = new Random();
                for (int i = 0; i < arrayLength; i++) {
                    tablicaInsert[i] = rnd.nextDouble();
                }
            } else if(wybranyPrzypadek.equalsIgnoreCase("best")) {
                if (typSortowania.equalsIgnoreCase("insert")) {
                    for (int i = 0; i < arrayLength; i++) {
                        tablicaInsert[i] = i*1.32;
                    }
                } else if (typSortowania.equalsIgnoreCase("merge")) {
                    //TODO add best merge case
                    throw new UnsupportedOperationException("Not supported yet.");
                } else {
                    System.out.println("Podano zły typ sortowania, dostępne to merge i insert!");
                    return;
                }
            } else if(wybranyPrzypadek.equalsIgnoreCase("worst")) {
                if (typSortowania.equalsIgnoreCase("insert")) {
                    for (int i = 0; i < arrayLength; i++) {
                        tablicaInsert[i] = arrayLength - i;
                    }
                } else if (typSortowania.equalsIgnoreCase("merge")) {
                    //TODO add worst merge case
                    throw new UnsupportedOperationException("Not supported yet.");
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
                for (double elem : tablicaInsert) {
                    System.out.println("    " + elem);
                }
            }
            
            long estimatedTime;
            if (typSortowania.equalsIgnoreCase("insert")) {
                InsertSort insertSort = new InsertSort();
                System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
                long startTime = System.nanoTime();
                tablicaInsert = insertSort.sort(tablicaInsert);
                estimatedTime = System.nanoTime() - startTime;
                
            } else if( typSortowania.equalsIgnoreCase("merge")) {
                InsertSort insertSort = new InsertSort();
                System.gc(); //uruchomienie garbage collectora, by oczyścić pamięć przed
                long startTime = System.nanoTime();
                tablicaInsert = insertSort.sort(tablicaInsert);
                estimatedTime = System.nanoTime() - startTime;
            } else {
                System.out.println("Błąd przy rozpoznawaniu nazwy algorytmu sortowania!");
                return;
            }
            
            if (printsArrays) {
                System.out.println("Tablica po: ");
                for (double elem : tablicaInsert) {
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
    
}
