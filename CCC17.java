import java.util.Arrays;

public class CCC17 {
    // PROBLEM 1
    int S1(String swifts, String semaph) {
        int[] swiftScores = stringToInt(swifts.trim().split(" "));
        int[] semaphScores = stringToInt(semaph.trim().split(" "));
        int N = swiftScores.length;
        int swiftsT = 0, semT = 0, k = 0;
        for (int i = 0; i < N; i++) {
            swiftsT += swiftScores[i];
            semT += semaphScores[i];
            if (swiftsT == semT) {
                k = i + 1;
            }
        }

        return k;
    }

    int[] stringToInt(String[] arr) {
        int[] ret = new int[arr.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(arr[i]);
        }
        return ret;
    }
    // PROBLEM 1 end

    // PROBLEM 2
    String S2(String data) {
        int[] tideData = stringToInt(data.trim().split(" "));
        Arrays.sort(tideData);
        int[] lowTide = new int[tideData.length / 2];
        int[] highTide = new int[tideData.length / 2];

        for (int i = 0, i2 = tideData.length / 2; i < lowTide.length && i2 < tideData.length; i++, i2++) {
            lowTide[i] = tideData[i];
            highTide[i] = tideData[i2];
        }

        int[] result = new int[tideData.length];
        // low gets lower and high gets higher
        for (int i = 0, i2 = 1, i3 = lowTide.length - 1, i4 = 0; i < result.length && i2 < result.length && i3 >= 0
                && i4 < highTide.length; i += 2, i2 += 2, i3--, i4++) {
            result[i] = lowTide[i3];
            result[i2] = highTide[i4];
        }

        return intToString(result);
    }

    String intToString(int[] arr) {
        StringBuilder ret = new StringBuilder("");
        for (int num : arr) {
            ret.append(num + " ");
        }

        return ret.toString().trim();
    }

    // PROBLEM 2 end
    public static void main(String[] args) {
        CCC17 problems = new CCC17();
        System.out.println("1) " + problems.S1("1 2 3 4", "1 3 2 4"));

        System.out.println("2) " + problems.S2("10 50 40 7 3 110 90 2"));
    }
}
