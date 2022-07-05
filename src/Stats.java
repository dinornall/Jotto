import java.util.ArrayList;

public class Stats {

    static public double findMode (ArrayList<Double> stats){
        double mode = 0;
        int maxCount = 0;
        for (int i = 0; i < stats.size(); i++){
            int count = 0;
            for (int j = 0; j < stats.size(); j++){
                if(stats.get(i).equals(stats.get(j))){
                    count++;
                }
            }
            if (count > maxCount){
                maxCount = count;
                mode = stats.get(i);
            }
        }
        return mode;
    }

    static public double findMean (ArrayList<Double> stats){
        double total = 0;
        for (int i = 0; i< stats.size(); i++){
            total += stats.get(i);
        }
        return total/ stats.size();
    }

    static public double findStdDev (ArrayList<Double> stats, double mean){
        ArrayList<Double> deviations = new ArrayList<Double>();
        for (int i = 0; i < stats.size(); i++){
            double dev = Math.pow((Math.abs(stats.get(i) - mean)), 2);
            deviations.add(dev);
        }
        double devMean = Stats.findMean(deviations);
        return Math.sqrt(devMean);
    }

}
