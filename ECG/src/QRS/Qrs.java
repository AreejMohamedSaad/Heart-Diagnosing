
package QRS;


public class Qrs {
 
    
    
    public static final int M = 5;
    public static final int N = 30;
    public static final int winSize = 256;
    public static final float HP_CONSTANT = (float) 1 / M;

    
    public static double [] detect(double [] ecg) {
        // circular buffer for input ecg signal
        // we need to keep a history of M + 1 samples for HP filter
        double [] ecg_circ_buff = new double [M + 1];
        int ecg_circ_WR_idx = 0;
        int ecg_circ_RD_idx = 0;

        // circular buffer for input ecg signal
        // we need to keep a history of N+1 samples for LP filter
        double [] hp_circ_buff = new double [N + 1];
        int hp_circ_WR_idx = 0;
        int hp_circ_RD_idx = 0;

        // LP filter outputs a single point for every input point
        // This goes straight to adaptive filtering for eval
        float next_eval_pt = 0;

        // output 
        double [] QRS = new double [ecg.length];

        // running sums for HP and LP filters, values shifted in FILO
        float hp_sum = 0;
        float lp_sum = 0;

        // parameters for adaptive thresholding
        double treshold = 0;
        boolean triggered = false;
        int trig_time = 0;
        float win_max = 0;
        int win_idx = 0;

        for (int i = 0; i < ecg.length; i++) {
            ecg_circ_buff[ecg_circ_WR_idx++] = ecg[i];
            ecg_circ_WR_idx %= (M + 1);

            /* High pass filtering */
            if (i < M) {
                // first fill buffer with enough points for HP filter
                hp_sum += ecg_circ_buff[ecg_circ_RD_idx];
                hp_circ_buff[hp_circ_WR_idx] = 0;
            } else {
                hp_sum += ecg_circ_buff[ecg_circ_RD_idx];

                int tmp = ecg_circ_RD_idx - M;
                if (tmp < 0) {
                    tmp += M + 1;
                }
                hp_sum -= ecg_circ_buff[tmp];

                double  y1 = 0;
                double  y2 = 0;

                tmp = (ecg_circ_RD_idx - ((M + 1) / 2));
                if (tmp < 0) {
                    tmp += M + 1;
                }
                y2 = ecg_circ_buff[tmp];

                y1 = HP_CONSTANT * hp_sum;

                hp_circ_buff[hp_circ_WR_idx] = y2 - y1;
            }

            ecg_circ_RD_idx++;
            ecg_circ_RD_idx %= (M + 1);

            hp_circ_WR_idx++;
            hp_circ_WR_idx %= (N + 1);

            /* Low pass filtering */
            // shift in new sample from high pass filter
            lp_sum += hp_circ_buff[hp_circ_RD_idx] * hp_circ_buff[hp_circ_RD_idx];

            if (i < N) {
                // first fill buffer with enough points for LP filter
                next_eval_pt = 0;

            } else {
                // shift out oldest data point
                int tmp = hp_circ_RD_idx - N;
                if (tmp < 0) {
                    tmp += N + 1;
                }
                lp_sum -= hp_circ_buff[tmp] * hp_circ_buff[tmp];

                next_eval_pt = lp_sum;
            }

            hp_circ_RD_idx++;
            hp_circ_RD_idx %= (N + 1);

            /* Adapative thresholding beat detection */
            // set initial threshold				
            if (i < winSize) {
                if (next_eval_pt > treshold) {
                    treshold = next_eval_pt;
                }
            }

            // check if detection hold off period has passed
            if (triggered) {
                trig_time++;

                if (trig_time >= 100) {
                    triggered = false;
                    trig_time = 0;
                }
            }

            // find if we have a new max
            if (next_eval_pt > win_max) {
                win_max = next_eval_pt;
            }

            // find if we are above adaptive threshold
            if (next_eval_pt > treshold && !triggered) {
                QRS[i] = 1;

                triggered = true;
            } else {
                QRS[i] = 0;
            }

            // adjust adaptive threshold using max of signal found 
            // in previous window            
            if (++win_idx > winSize) {
                // weighting factor for determining the contribution of
                // the current peak value to the threshold adjustment
                double gamma = 0.175;

                // forgetting factor - 
                // rate at which we forget old observations
                double alpha = 0.01 + (Math.random() * ((0.1 - 0.01)));

                treshold = alpha * gamma * win_max + (1 - alpha) * treshold;

                // reset current window ind
                win_idx = 0;
                win_max = -10000000;
            }
        }

        return QRS;
    }

    public static double [] r_rIndex(double [] QRS) {
        int num_of_Rwave = 0;
        for (int i = 0; i < QRS.length; i++) {
            if (QRS[i] == 1) {
                num_of_Rwave++;
            }
        }

        double [] R = new double [num_of_Rwave];
        int k = 0;
        for (int i = 0; i < QRS.length; i++) {
            if (QRS[i] == 1) {
                R[k] = i;
                k++;
            }

        }
        return R;
    }
    public static double  rate(double [] index, double [] r_r,double time,int type) {
        // ,int l,double time)
        //regular 
        if(type==0){
        double  rate;
        double flag = -1;
        for (int j = 0; j < r_r.length; j++) {
              System.out.println(r_r[j]+"hi");
            if (r_r[j] != r_r[j + 1]) {
                flag = 0;
                break;
            }
          
        }
        if (flag == -1) {
           System.out.println(" iam regular");

            rate = 1500 / r_r[0];
            return rate;
        } else {
        //irregular
            // System.out.println("i am irreguler");

            int i = 0;
           //(6000*18)/50 =2160 where 6000 is the milliseconds as in each 50 milliseconds there are 18 samples so to calculate the data in each 2160 seonds 
            while (index[i] < time) {
       
                i++;
            }

            //i+1 array
            i++;
             System.out.println("i="+i);
            rate = 10 * i;

            return rate;
        }
        }else{
        
           double  rate;
        double flag = -1;
        ////will make mode function
        for (int j = 0; j < r_r.length-2; j++) {
            if (r_r[j] != r_r[j + 1]) {
                 System.out.println("the iteration"+j);
                System.out.println("inside regular"+r_r[j]+"  "+r_r[j+1]);
               flag = 0;
                break;
            }
            System.out.println("koko"+r_r[j]);
        }
//          rate = 60000 / r_r[0];
//            return rate;}
        if (flag == -1) {
         System.out.println(" iam regular"+r_r[0]);

            rate =60000 / r_r[0];
            return rate;}
     else {
        //irregular
       System.out.println("i am irreguler");


       double th=6000.0/time;
          System.out.println("m ="+th);
        return rate=10*(index.length+1);
        
        }}
      
     }
    public static double [] r_rInterval(double [] index ,double th) {

        double [] R = new double [index.length];

        for (int i = 0; i < index.length - 1; i++) {
            System.out.println("hjhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+th);
            R[i] =  ((index[i + 1] - index[i]) *th);
        }
        return R;
    }
     }
