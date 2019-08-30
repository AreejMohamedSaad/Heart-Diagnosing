
package ecg;
//
//import ECGjar.Class1;
import QRS.Filter;
import QRS.Qrs;
import com.mathworks.toolbox.javabuilder.*;
//import TheMain.matlabJava;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jaaavaaMatlab.jaaavaaMatlab;
import TheJar.TheJar;
import lastJar.lastJar;

public class Result {
    
    
    
    
     public static double [] ReadData(String recordFile){
          GetData getData = new GetData();
            ArrayList<String> data_list = getData.readCSVToArrayList(recordFile);
            //second method
            double[] ecg = getData.AddToArray(data_list);
      return ecg;
      }
      public static double [] qrsArray(double []ecg){
           Qrs qrs = new Qrs();
            double[] q = qrs.detect(ecg);
      return q;
      }
      public static double  recordResult(double []q) {
          
           // System.out.println("recordFile"+recordFile);
          
//           GetData getData = new GetData();
//            ArrayList<String> data_list = getData.readCSVToArrayList(recordFile);
            //second method
           // double[] ecg =ReadData(recordFile);
            ////from filter class
//            Filter filter = new Filter();
//            int smoothing = 3;
//            double[] filter_ecg = filter.low_pass(ecg, smoothing);
    Qrs qrs = new Qrs();
          //  double[] q =  qrsArray(ecg);
            double[] index = qrs.r_rIndex(q);
            //for record
             double th=1/14.44;
            double[] R_R = qrs.r_rInterval(index,th );
            double rate = qrs.rate(index, R_R, 2160, 0);

            System.out.println(rate);
            if (rate >= 60 && rate <= 100) {
                System.out.println("Normal");

            } else {
                System.out.println(" Not Normal");
            }
          return rate;
      
      
      }
      public static double  imgResult(String recordFile) throws IOException, MWException {
             
           double pixel_orginal = 5800;
            double time_orginal = 10000.;
            ImagePixels image = new ImagePixels();
            ImagePixels imagename= new ImagePixels();
           imagename.usingBufferedWritter(recordFile);

                            // lastJar.Class1.main(null);/
//                 Class1 c =new Class1();
//                 c.main(null);
//            
            // jaaavaaMatlab  m = new jaaavaaMatlab();
           //  m.mainprogram();
            
//           TheJar  m = new TheJar();
//             m.mainprogram();
             


              lastJar  m = new lastJar();
             m.mainprogram();
            
            GetData getData = new GetData();
            ArrayList<String> data_list = getData.readCSVToArrayList("data.csv");
            //second method
            double pixel_image = 0;

            try {
                pixel_image = image.getPixel();

            } catch (IOException ex) {
                Logger.getLogger(ECG.class.getName()).log(Level.SEVERE, null, ex);
            }

            int orginalpixel = 5800;

            double[] ecg = getData.AddToArray(data_list);
        ////from filter class
            /// sample per time  th 

            double time = (time_orginal * pixel_image) / pixel_orginal;
            if (time > 10000) {
                time = 10000;
            }
            double th = time / ecg.length;

            Filter filter = new Filter();
            int smoothing = 3;
            double[] filter_ecg = filter.low_pass(ecg, smoothing);
            Qrs qrs = new Qrs();
            double[] q = qrs.detect(ecg);
            for (int i = 0; i < q.length; i++) {
                // System.out.println("q"+q[i]);
            }
             double rate=0;
            double[] index = qrs.r_rIndex(q);
            if (index.length > 1) {
                for (int i = 0; i < index.length; i++) {
                    System.out.println("index[" + i + "] : " + index[i]);
                }
                System.out.println("size of r_R interval ");
                double[] R_R = qrs.r_rInterval(index, th);
                for (int i = 0; i < R_R.length; i++) {
                    System.out.println("R_R[" + i + "] : " + R_R[i]);
                }
                 rate = qrs.rate(index, R_R,time,1);

                System.out.println(rate);
                if (rate >= 60 && rate <= 100) {
                    System.out.println("Normal");

                } else {
                    System.out.println(" Not Normal");
                }
            } else {

                System.out.println(" i can't found ");
            }

          return rate;
      
      
      }
}
