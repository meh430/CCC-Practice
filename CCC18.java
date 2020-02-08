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

        return min;// Math.floor(min * 10.0) / 10.0;
    }
    // PROBLEM 1 end

    // PROBLEM 2
    // rotate and check if valid
    void S2(int[][] sunFlower) {
        while (true) {
            if (checkValid(sunFlower)) {
                break;
            }
            rotate(sunFlower);
        }

        System.out.println("3) ");
        for (int i = 0; i < sunFlower.length; i++) {
            if (i != 0)
                System.out.println();

            for (int j = 0; j < sunFlower.length; j++) {
                System.out.print(j == sunFlower.length - 1 ? sunFlower[i][j] : sunFlower[i][j] + " ");
            }
        }
    }

    void rotate(int[][] sunFlower) {
        int N = sunFlower.length;
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(sunFlower[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, j2 = N - 1; j < N && j2 >= 0; j++, j2--) {
                sunFlower[i][j] = temp[j2][i];
            }
        }
    }

    boolean checkValid(int[][] sunFlower) {
        for (int i = 0; i < sunFlower.length - 1; i++) {
            for (int j = 0; j < sunFlower.length - 1; j++) {
                if (sunFlower[i][j] > sunFlower[i][j + 1] || sunFlower[i][j] > sunFlower[i + 1][j]) {
                    return false;
                }
            }
        }

        return true;
    }
    // PROBLEM 2 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CCC18 problems = new CCC18();
        int N = Integer.parseInt(reader.readLine().trim());
        int[] villages = new int[N];
        for (int i = 0; i < N; i++) {
            villages[i] = Integer.parseInt(reader.readLine().trim());
        }
        System.out.print("1) ");
        System.out.printf("%.1f\n", problems.S1(villages));

        N = Integer.parseInt(reader.readLine().trim());
        int[][] sunFlower = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = reader.readLine().trim().split(" ");
            for (int j = 0; j < N; j++) {
                sunFlower[i][j] = Integer.parseInt(line[j]);
            }
        }

        problems.S2(sunFlower);
    }
}