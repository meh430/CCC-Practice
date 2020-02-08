import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // PROBLEM 3
    int S3(int gates, int[] planes) {
        boolean[] gateState = new boolean[gates];
        boolean succ = false;
        int count = 0;
        for (int i = 0; i < planes.length; i++) {
            succ = false;
            int planeNum = planes[i];
            for (int j = 1; j <= planeNum; j++) {
                if (!gateState[planeNum - j]) {
                    gateState[planeNum - j] = true;
                    succ = true;
                    count++;
                    break;
                }
            }
            if (!succ) {
                return count;
            }
        }

        return count;
    }
    // PROBLEM 3 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int gates = Integer.parseInt(reader.readLine().trim());
        int P = Integer.parseInt(reader.readLine().trim());
        int[] planes = new int[P];
        for (int i = 0; i < P; i++) {
            planes[i] = Integer.parseInt(reader.readLine().trim());
        }
        System.out.println(new Main().S3(gates, planes));
    }

}