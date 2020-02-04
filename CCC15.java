import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CCC15 {
    // PROBLEM 1
    int S1(int[] sequence) {
        Stack<Integer> number = new Stack<>();
        for (int num : sequence) {
            if (num == 0) {
                number.pop();
            } else {
                number.push(num);
            }
        }

        int sum = 0;
        while (!number.isEmpty()) {
            sum += number.pop();
        }

        return sum;
    }
    // PROBLEM 1 end

    // PROBLEM 2
    int S2(int jerseyStock, char[] availSizes, S2_Helper[] requests) {
        S2_Helper[] stock = new S2_Helper[jerseyStock];
        for (int i = 0; i < jerseyStock; i++) {
            stock[i] = new S2_Helper(availSizes[i], i + 1);
        }
        int possible = 0;
        for (S2_Helper request : requests) {
            for (S2_Helper store : stock) {
                if (store.size == 0) {
                    continue;
                } else if (request.satisfied(store)) {
                    possible++;
                    store.size = 0;
                }
            }
        }

        return possible;
    }
    // PROBLEM 2 end

    // PROBLEM 3
    int S3(int gates, int[] planes) {
        boolean[] gateState = new boolean[gates];
        int count = 0;
        for (int i = 0; i < planes.length; i++) {
            int planeNum = planes[i];
            if (!gateState[planeNum - 1]) {
                gateState[planeNum - 1] = true;
                count++;
            }
        }

        return count;
    }
    // PROBLEM 3 end

    // PROBLEM 4
    // thickness, num islands
    int S4(int K, int N, ArrayList<SeaRoute> routes) {
        return -1;
    }
    // PROBLEM 4 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CCC15 problems = new CCC15();
        System.out.println("1) " + problems.S1(new int[] { 1, 3, 5, 4, 0, 0, 7, 0, 0, 6 }));
        S2_Helper[] playerReq = { new S2_Helper('L', 3), new S2_Helper('S', 3), new S2_Helper('L', 1) };
        System.out.println("2) " + problems.S2(4, new char[] { 'M', 'S', 'S', 'L' }, playerReq));
        System.out.println("3) " + problems.S3(4, new int[] { 2, 2, 3, 3, 4, 4 }));

        String[] S4_line1_data = reader.readLine().trim().split(" ");
        int K = Integer.parseInt(S4_line1_data[0]);
        int N = Integer.parseInt(S4_line1_data[1]);
        int M = Integer.parseInt(S4_line1_data[2]);
        ArrayList<SeaRoute> routes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] route_data = reader.readLine().trim().split(" ");
            routes.add(new SeaRoute(Integer.parseInt(route_data[0]), Integer.parseInt(route_data[1]),
                    Integer.parseInt(route_data[2]), Integer.parseInt(route_data[3])));
        }

        System.out.println("4) " + problems.S4(K, N, routes));
    }
}

class S2_Helper {
    // 1-small
    // 2-medium
    // 3-large
    int size, jerseyNum;

    S2_Helper(char chrSize, int num) {
        this.jerseyNum = num;
        if (chrSize == 'S') {
            size = 1;
        } else if (chrSize == 'M') {
            size = 2;
        } else if (chrSize == 'L') {
            size = 3;
        }
    }

    boolean satisfied(S2_Helper other) {
        return (this.size <= other.size && this.jerseyNum == other.jerseyNum);
    }
}

class SeaRoute {
    int islandA, islandB, time, rocks;

    SeaRoute(int a, int b, int t, int r) {
        islandA = a;
        islandB = b;
        time = t;
        rocks = r;
    }
}