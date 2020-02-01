public class DepthFirst {

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
