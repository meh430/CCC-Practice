import java.io.*;
import java.util.*;

public class Main {
    int S2(int W, int[] trains) {
        int count = 0;

        Queue<Integer> trainQ = new LinkedList<>();
        for (int i = 0; i < trains.length; i++) {
            if (addAll(trainQ) > W) {
                trainQ.poll();
                break;
            }

            if (trainQ.size() >= 4) {
                trainQ.poll();
                count++;
            }

            trainQ.add(trains[i]);
        }

        while (!trainQ.isEmpty()) {
            trainQ.poll();
            count++;
        }

        return count;
    }

    int addAll(Queue<Integer> trainQ) {
        int sum = 0;
        Iterator<Integer> trainI = trainQ.iterator();
        while (trainI.hasNext()) {
            sum += trainI.next();
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Main problem = new Main();
        int W = Integer.parseInt(reader.readLine().trim());
        int N = Integer.parseInt(reader.readLine().trim());
        int[] trains = new int[N];
        for (int i = 0; i < N; i++) {
            trains[i] = Integer.parseInt(reader.readLine().trim());
        }
        reader.close();
        System.out.println(problem.S2(W, trains));
    }
}