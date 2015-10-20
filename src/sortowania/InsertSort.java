/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortowania;

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
    
 
}
