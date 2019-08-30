/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRS;

/**
 *
 * @author Rania Amr
 */
public class Filter {
 
  public  double [] low_pass(double[] values,int smoothing){
    double value = values[0];
    for (int i=1, len=values.length; i<len; ++i){
    double  currentValue = values[i];
    value += (currentValue - value) / smoothing;
    values[i] = value;
    //System.out.println(values[i]);
    
         }
    return values;
   }   
  
}
