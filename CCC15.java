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
    // PROBLEM 2 end

    public static void main(String[] args) {
        CCC15 problems = new CCC15();
        System.out.println("1) " + problems.S1(new int[] { 1, 3, 5, 4, 0, 0, 7, 0, 0, 6 }));
    }
}