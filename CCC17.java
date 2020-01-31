public class CCC17 {
    // PROBLEM 1
    int S1(String swifts, String semaph) {
        int[] swiftScores = stringToInt(swifts.trim().split(" "));
        int[] semaphScores = stringToInt(semaph.trim().split(" "));
        int N = swiftScores.length;
        for (int i = N - 1; i >= 0; i--) {
            if (addAll(swiftScores, i) == addAll(semaphScores, i)) {
                return i + 1;
            }
        }

        return 0;
    }

    int addAll(int[] arr, int end) {
        int sum = 0;
        for (int i = 0; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    int[] stringToInt(String[] arr) {
        int[] ret = new int[arr.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(arr[i]);
        }
        return ret;
    }

    // PROBLEM 1 end
    public static void main(String[] args) {
        CCC17 problems = new CCC17();
        System.out.println(problems.S1("1 2 3 4", "1 3 2 4"));
    }
}
