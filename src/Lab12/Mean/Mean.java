package Lab12.Mean;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static int size = 100000000;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);



    public static void main(String[] args) {
        initArray(size);
        try {
            for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
                parallelMean(cnt);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    static void parallelMean(int cnt) throws InterruptedException {
        MeanCalc threads[]=new MeanCalc[cnt];
        int interval = array.length/cnt;
        for(int i =0; i< cnt; ++i){
            threads[i] = new MeanCalc(i*interval, (i+1) * interval);
        }

        double t1 = System.nanoTime()/1e6;
        for (MeanCalc mc: threads) {
            mc.start();
        }
        double t2 = System.nanoTime()/1e6;
//        for(MeanCalc mc:threads) {
//            mc.join();
//        }

        double mean= 0;
        for(int i = 0; i< cnt; ++i){
          mean += results.take();
        }

        double t3 = System.nanoTime()/1e6;

        mean /= cnt;

        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }


    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            if(end > array.length)
                end = array.length;
            if(start> array.length)
                start = array.length;
            this.start = start;
            this.end=end;
        }
        public void run(){
            double sum = 0;
            for(int i = start; i<end; ++i){
                sum += array[i];
            }
            mean = sum/(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

}