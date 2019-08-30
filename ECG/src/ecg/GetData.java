
package ecg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GetData {
    
    
    public  ArrayList<String> readCSVToArrayList(String csvpath) {

        ArrayList<String> dataAL = new ArrayList<String>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(csvpath));
           
            String line = null;//

            int line_num = 0;
            while ((line = reader.readLine()) != null) {

                String item[] = line.split(",");
                dataAL.add(item[0]);

                //System.out.println(dataAL.get(line_num));
                line_num++;
            }
            //System.out.println(dataAL.size());
            //System.out.print(ticketStr.toString());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataAL;
    }
    public  double [] AddToArray(ArrayList<String> data_list){
        int nsamp = data_list.size() - 2;
        double [] ecg = new double [nsamp];
        for (int i = 2; i < nsamp; i++) {
           ecg[i - 2] = Double .parseDouble(data_list.get(i));
         //  System.out.println( ecg[i - 2]);
           // ecg[i - 2] = Float.parseFloat(data_list.get(i));
        }
        return ecg;
    }

    
}
