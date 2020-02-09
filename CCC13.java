import java.io.*;
import java.util.*;

public class CCC13 {
    Pair[] possibleMatches = { new Pair(1, 2), new Pair(2, 3), new Pair(3, 4), new Pair(1, 3), new Pair(1, 4),
            new Pair(2, 4) };

    int S1(int year) {
        boolean blnFlag = true;
        year = year + 1;
        for (int i = year;; i++) {
            blnFlag = true;
            String strYear = i + "";
            for (int j = 0; j < strYear.length(); j++) {
                if (strYear.substring(j + 1, strYear.length()).contains(strYear.substring(j, j + 1))) {
                    blnFlag = false;
                }
            }

            if (blnFlag) {
                return i;
            }
        }
    }

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
            } else {
                trainQ.add(trains[i]);
            }
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

    int S3(int[][] results) {
        int[] scores = new int[4];
        Pair[] remainingMatches = new Pair[6 - results.length];
        int possibilities = (int) Math.pow(3, remainingMatches.length);
        for (int i = 0; i < remainingMatches.length; i++) {
            Pair temp = new Pair(results[i][0], results[i][1]);
            if (results[i][2] > results[i][3]) {
                scores[results[i][0] - 1] += 3;
            } else if (results[i][2] < results[i][3]) {
                scores[results[i][1] - 1] += 3;
            } else if (results[i][2] == results[i][3]) {
                scores[results[i][0] - 1] += 1;
                scores[results[i][1] - 1] += 1;
            }
            for (int j = 0; j < possibleMatches.length; j++) {
                if (!temp.same(possibleMatches[j].a, possibleMatches[j].b)) {
                    remainingMatches[i] = possibleMatches[j];
                }
            }
        }
        LinkedList<String> outcomes = getOutcomes((int) Math.pow(3, remainingMatches.length), remainingMatches.length);
        for (int i = 0; i < (int) Math.pow(3, remainingMatches.length); i++) {
            int[] tempScores = Arrays.copyOf(scores, 4);
            for (int j = 0; j < remainingMatches.length; j++) {

            }
        }
        return 0;
    }

    LinkedList<String> getOutcomes(int tree, int set) {
        int each = (tree * set) / 3;
        LinkedList<String> ret = new LinkedList<>();
        String[] outcomes = new String[set];
        Arrays.fill(outcomes, "W");
        for (int i = 0; i < tree; i++) {
            StringBuilder scen = new StringBuilder();
            for (int j = 0; j < set; j++) {
                scen.append(outcomes[j]);
            }
            ret.add(scen.toString());

        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CCC13 problems = new CCC13();
        System.out.println(problems.S1(Integer.parseInt(reader.readLine().trim())));
        int W = Integer.parseInt(reader.readLine().trim());
        int N = Integer.parseInt(reader.readLine().trim());
        int[] trains = new int[N];
        for (int i = 0; i < N; i++) {
            trains[i] = Integer.parseInt(reader.readLine().trim());
        }
        System.out.println(problems.S2(W, trains));

        int T = Integer.parseInt(reader.readLine().trim());
        int G = Integer.parseInt(reader.readLine().trim());
        int[][] results = new int[G][4];
        for (int i = 0; i < G; i++) {
            String[] line = reader.readLine().trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                results[i][j] = Integer.parseInt(line[j]);
            }
        }
    }
}

class Pair {
    int a, b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    boolean same(int A, int B) {
        return (a == A && b == B) || (a == B && b == A);
    }
}