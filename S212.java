import java.util.*;
public class S212 {
    static LinkedList<helper> pair = new LinkedList<>();
    static HashMap<Character, Integer> romanToNum = new HashMap<>();
    public static void main(String [] args) {
        romanToNum.put('I', 1);
        romanToNum.put('V', 5);
        romanToNum.put('X', 10);
        romanToNum.put('L', 50);
        romanToNum.put('C', 100);
        romanToNum.put('D', 500);
        romanToNum.put('M', 1000);

        convertString("3X2I4X");

        System.out.println(getResult());
    }

    public static int getResult() {
        int sum = 0;
        for(int i = 0; i < pair.size(); i++) {
            if(i == pair.size()-1) {
                sum += (pair.get(i).var1 * pair.get(i).var2);
                break;
            }

            if(pair.get(i).var1 < pair.get(i+1).var1) {
                sum -= (pair.get(i).var1 * pair.get(i).var2);
            } else {
                sum += (pair.get(i).var1 * pair.get(i).var2);
            }
        }

        return sum;
    }

    public static void convertString(String aroma) {
        for(int i = 0; i < aroma.length(); i+=2) {
            int intPart = Integer.parseInt(aroma.charAt(i) + "");
            char chrPart = aroma.charAt(i+1);
            System.out.println("AROMA: " + intPart + " " + chrPart);
            pair.add(new helper(romanToNum.get(chrPart), intPart));
        }
    }

    private static class helper {
        public int var1;
        public int var2;
        helper(int var1, int var2) {
            this.var1 = var1;
            this.var2 = var2;
        }
    }
}
