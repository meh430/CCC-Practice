import java.util.*;

public class CCC2020 {
    int J1(int n) {
        if (n == 1 || n == 10 || n == 9) {
            return 1;
        } else if (n == 2 || n == 3 || n == 7 || n == 8) {
            return 2;
        } else if (n == 4 || n == 5 || n == 6) {
            return 3;
        }

        return 0;
    }

    int S1(String swifts, String semaph) {
        int[] swiftScores = stringToInt(swifts.trim().split(" "));
        int[] semaphScores = stringToInt(semaph.trim().split(" "));
        int N = swiftScores.length;
        int swiftsT = 0, semT = 0, day = 0;
        for (int i = 0; i < N; i++) {
            swiftsT += swiftScores[i];
            semT += semaphScores[i];
            if (swiftsT == semT) {
                day = i + 1;
            }
        }

        return day;
    }

    int[] stringToInt(String[] arr) {
        int[] ret = new int[arr.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(arr[i]);
        }
        return ret;
    }

    int S3(int[] Rs, int N) {
        LinkedList<Division> divisions = new LinkedList<>();
        Arrays.sort(Rs);
        int holder = 0;
        for (int i = 0; i < N; i++) {
            if (i + 1 >= N) {
                if (Rs[i] - Rs[i - 1] != 0) {
                    divisions.add(new Division(Rs[i], 1));
                } else {
                    divisions.add(new Division(Rs[i], (i + 1) - holder));
                }
                break;
            }
            if (Rs[i + 1] - Rs[i] != 0) {
                divisions.add(new Division(Rs[i], (i + 1) - holder));
                holder = i + 1;
            }
        }

        Collections.sort(divisions, new Comparator<Division>() {
            @Override
            public int compare(Division o1, Division o2) {
                if (o2.count - o1.count == 0) {
                    return o2.value - o1.value;
                }
                return o2.count - o1.count;
            }
        });
        int sameCount = 0;
        for (int i = 0; i < divisions.size() - 1; i++) {
            if (divisions.get(i).count - divisions.get(i + 1).count != 0) {
                sameCount = i;
                break;
            } else if (i + 1 >= divisions.size() - 1) {
                sameCount = i + 1;
            }
        }

        LinkedList<Division> ret = new LinkedList<>();
        if (sameCount == divisions.size() - 1) {
            Collections.sort(divisions, new Comparator<Division>() {
                public int compare(Division o1, Division o2) {
                    return o2.value - o1.value;
                }
            });

            return Math.abs(divisions.get(divisions.size() - 1).value - divisions.get(0).value);
        } else if (sameCount + 1 >= 2) {
            for (int i = 0; i < sameCount + 1; i++) {
                ret.add(new Division(divisions.get(i).value, divisions.get(i).count));
            }
            Collections.sort(ret, new Comparator<Division>() {
                public int compare(Division o1, Division o2) {
                    return o2.value - o1.value;
                }
            });
            return Math.abs(ret.get(ret.size() - 1).value - ret.get(0).value);
        } else if (sameCount + 1 == 1) {
            int index = 0;
            int highFreq = divisions.get(0).value;
            for (int i = 1; i < divisions.size() - 1; i++) {
                if (divisions.get(i).count - divisions.get(i + 1).count != 0) {
                    index = i;
                    break;
                } else if (i + 1 >= divisions.size() - 1) {
                    index = i;
                }
            }

            for (int i = 1; i < index + 1; i++) {
                ret.add(new Division(divisions.get(i).value, divisions.get(i).count));
            }

            Collections.sort(ret, new Comparator<Division>() {
                public int compare(Division o1, Division o2) {
                    return o1.value - o2.value;
                }
            });

            if (highFreq < ret.get(0).value) {
                return Math.abs(highFreq - ret.get(ret.size() - 1).value);
            } else {
                return Math.abs(highFreq - ret.get(0).value);
            }
        }

        return 0;
    }
}

class Division {
    int value, count;

    Division(int value, int c) {
        this.value = value;
        this.count = c;
    }

    @Override
    public String toString() {
        return "(" + value + ", " + count + ")";
    }
}