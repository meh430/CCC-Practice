public class DepthFirst {
    boolean checkPrime(int num) {
        for (int i = 2; i <= num / 2; ++i) {
            // condition for nonprime number
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    void sieveOfEratosthenes(int n) {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(containsPath());
    }

    static int[] intPath = { 1, 4, 3, 6, 6, 2, 7, 6, 1, 7, 3, 8, 4, 3, 2, 9 };
    static int[][] grid = { { 1, 4, 7, 1, 9, 3, 2, 8, 3, 1 }, { 5, 3, 0, 2, 7, 3, 9, 5, 3, 6 },
            { 1, 6, 4, 2, 5, 8, 4, 6, 9, 6 }, { 9, 6, 4, 6, 9, 6, 2, 4, 9, 6 }, { 5, 2, 7, 8, 5, 4, 5, 2, 6, 9 },
            { 0, 3, 6, 1, 7, 3, 8, 4, 3, 2 }, };

    static boolean containsPath() {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        System.out.println(visited[0][0]);
        return dfs(0, 0, visited, 0);
    }

    static boolean dfs(int row, int col, boolean[][] visited, int k) {
        if (k == intPath.length) {
            return true;
        }

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        } else if (visited[row][col] || intPath[k] != grid[row][col]) {
            return false;
        }

        visited[row][col] = true;
        if (dfs(row + 1, col, visited, k + 1) || dfs(row - 1, col, visited, k + 1) || dfs(row, col + 1, visited, k + 1)
                || dfs(row, col - 1, visited, k + 1)) {
            return true;
        }
        return false;
    }
}
