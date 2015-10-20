package sortowania;


import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DRAPIEWP
 */
public class MergeSort implements SortingAlgorithm {
  private int ileElem;
  private double[] tablica;
  private double[] tmp;
  private final static int SORTINGARRAYLENGTH = 10;

  public double[] sort(double[] unsortedVector) {
    this.tablica= unsortedVector;
    ileElem = unsortedVector.length;
    this.tmp = new double[unsortedVector.length];
    mergesort(0, ileElem - 1);
    
    return tablica;
  }

  private void mergesort(int min, int max) {
    if (min < max) {
      int midd = min + (max - min) / 2;
      mergesort(min, midd);
      mergesort(midd + 1, max);
      merge(min, midd, max);
    }
  }

  private void merge(int min, int middle, int max) {

    for (int i = min; i <= max; i++) {
      tmp[i] = tablica[i];
    }

    int i = min;
    int j = middle + 1;
    int k = min;
    
    while (i <= middle && j <= max) {
      if (tmp[i] <= tmp[j]) {
        tablica[k] = tmp[i];
        i++;
      } else {
        tablica[k] = tmp[j];
        j++;
      }
      k++;
    }
    while (i <= middle) {
      tablica[k] = tmp[i];
      k++;
      i++;
    }

  }
    
}
