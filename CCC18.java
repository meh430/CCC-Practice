import java.io.*;
import java.util.*;

public class CCC18 {
    // PROBLEM 1
    double S1(int[] villages) {
        Arrays.sort(villages);
        double[] middlePoints = new double[villages.length - 1];
        for (int i = 0; i < middlePoints.length; i++) {
            middlePoints[i] = (villages[i] + villages[i + 1]) / 2.0;
        }
        double[] dist = new double[middlePoints.length - 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = middlePoints[i + 1] - middlePoints[i];
        }
        double min = Double.MAX_VALUE;
        for (double distance : dist) {
            min = distance < min ? distance : min;
        }

        return Math.floor(min * 10.0) / 10.0;
    }
    // PROBLEM 1 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CCC18 problems = new CCC18();
        int N = Integer.parseInt(reader.readLine().trim());
        int[] villages = new int[N];
        for (int i = 0; i < N; i++) {
            villages[i] = Integer.parseInt(reader.readLine().trim());
        }
        System.out.println("1) " + problems.S1(villages));
    }
}