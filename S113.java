public class S113 {
    public static void main(String [] args) {
        int sum = 0;
        int jersey = 90;
        for(int i = 1; i <= jersey - 3; i++) {
            for(int j = i + 1; j <= jersey - 2; j++) {
                for(int k = j + 1; k <= jersey - 1; k++) {
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }
}
