import java.io.*;
import java.util.*;

public class CCC14 {
    // PROBLEM 1
    void S1(int size, int[] removeInterval) {

        ArrayList<Integer> invites = fillList(size);
        for (int removal : removeInterval) {
            System.out.println("REMOVAL TYPE: " + removal);
            for (int i = removal - 1; i < invites.size(); i += removal) {
                System.out.println("removed index: " + i + ", removed element: " + invites.get(i));
                invites.remove(i);
                i--;
            }
        }
        System.out.println("1) " + invites);
    }

    ArrayList<Integer> fillList(int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            ret.add(i - 1, i);
        }

        return ret;
    }
    // PROBLEM 1 end

    // PROBLEM 2
    boolean S2(String line1, String line2) {
        String[] names1 = line1.trim().split(" ");
        String[] names2 = line2.trim().split(" ");
        List<String> list1 = Arrays.asList(names1);
        List<String> list2 = Arrays.asList(names2);
        ArrayList<S2_Helper> partners = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            partners.add(i, new S2_Helper(list1.get(i), list2.get(i)));
        }

        for (int i = 0; i < partners.size(); i++) {
            for (int j = i + 1; j < partners.size(); j++) {
                S2_Helper pair1 = partners.get(i);
                S2_Helper pair2 = partners.get(j);
                if (pair1.name1.equals(pair2.name1) && !pair1.name2.equals(pair2.name2)) {
                    return false;
                    // System.out.println("bad");
                } else if (pair1.name1.equals(pair2.name2) && !pair1.name2.equals(pair2.name1)) {
                    return false;
                    // System.out.println("bad");
                }
            }
        }

        return true;
    }
    // PROBLEM 2 end

    // PROBLEM 3
    void S3_Driver(ArrayList<ArrayList<Integer>> carTests) {
        for (ArrayList<Integer> cars : carTests) {
            if (this.S3(cars)) {
                System.out.println("Y");
            } else {
                System.out.println("N");
            }
        }
    }

    boolean S3(ArrayList<Integer> cars) {
        int N = cars.size();
        Stack<Integer> mountain = new Stack<Integer>();
        Stack<Integer> branch = new Stack<Integer>();
        ArrayList<Integer> lake = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            mountain.push(cars.get(i));
        }
        int place = 1;
        while (true) {
            if (mountain.isEmpty()) {
                while (true) {
                    if (branch.isEmpty()) {
                        break;
                    }
                    lake.add(branch.pop());
                }
                break;
            }

            if (mountain.peek() == place) {
                lake.add(mountain.pop());
                place++;
            } else if (mountain.peek() != place) {
                if (branch.isEmpty() || branch.peek() != place) {
                    branch.push(mountain.pop());
                } else if (branch.peek() == place) {
                    lake.add(branch.pop());
                    place++;
                }
            }

            System.out.println("Mountain: " + mountain);
            System.out.println("Branch: " + branch);
            System.out.println("Lake: " + lake);
        }

        System.out.println(lake);

        for (int i = 0; i < lake.size(); i++) {
            if (lake.get(i) != i + 1) {
                return false;
            }
        }
        return lake.size() == N;
    }
    // PROBLEM 3 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CCC14 problems = new CCC14();
        problems.S1(10, new int[] { 2, 3 });
        System.out.println(
                problems.S2("Rich Graeme Michelle Sandy Vlado Ron Jacob", "Ron Vlado Sandy Michelle Rich Graeme Jacob")
                        ? "2) good"
                        : "2) bad");
        int testNums = Integer.parseInt(reader.readLine());
        ArrayList<ArrayList<Integer>> carTests = new ArrayList<>();
        for (int i = 0; i < testNums; i++) {
            int carNum = Integer.parseInt(reader.readLine());
            ArrayList<Integer> cars = new ArrayList<>();
            for (int j = 0; j < carNum; j++) {
                cars.add(Integer.parseInt(reader.readLine()));
            }
            carTests.add(cars);
        }
        problems.S3_Driver(carTests);
    }
}

class S2_Helper {
    String name1, name2;

    S2_Helper(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }
}
