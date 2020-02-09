import java.io.*;
import java.util.*;

public class Temp {
    Position start;

    // PROBLEM 3
    int[] s3(int rowEnd, int colEnd, char[][] grid, ArrayList<Position> endPositions, ArrayList<Position> cameras) {
        ArrayList<Position> cameraSpaces = getCameraSpaces(grid, cameras);
        int[] answers = new int[endPositions.size()];
        int curr = 0;
        boolean[][] visited;// = new boolean[rowEnd][colEnd];
        for (Position end : endPositions) {
            visited = new boolean[rowEnd][colEnd];
            answers[curr] = bfs(end, grid, visited, cameraSpaces);
            curr++;
        }
        return answers;
    }

    int bfs(Position position, char[][] grid, boolean[][] visited, ArrayList<Position> cameraSpaces) {
        Queue<Position> bfs = new LinkedList<>();
        bfs.add(new Position(position.row, position.col, 0));
        while (!bfs.isEmpty()) {
            Position curr = bfs.poll();
            if (grid[curr.row][curr.col] == 'S') {
                return curr.distance;
            } else if (!cameraPosition(curr.row, curr.col, cameraSpaces)) {
                bfs.addAll(getNeigbors(curr, grid, visited, grid[curr.row][curr.col] == '.', cameraSpaces));
            }
        }

        return -1;
    }

    LinkedList<Position> getNeigbors(Position position, char[][] grid, boolean[][] visited, boolean empty,
            ArrayList<Position> cameraSpaces) {
        LinkedList<Position> ret = new LinkedList<>();
        int add = empty ? 1 : 0;
        if (!visited[position.row + 1][position.col] && grid[position.row + 1][position.col] != 'W'
                && inBounds(position.row + 1, position.col, grid) && grid[position.row + 1][position.col] != 'C'
                && !cameraPosition(position.row + 1, position.col, cameraSpaces)) {
            ret.add(new Position(position.row + 1, position.col, position.distance + add));
            visited[position.row + 1][position.col] = true;
        }

        if (!visited[position.row - 1][position.col] && inBounds(position.row - 1, position.col, grid)
                && grid[position.row - 1][position.col] != 'W' && grid[position.row - 1][position.col] != 'C'
                && !cameraPosition(position.row - 1, position.col, cameraSpaces)) {
            ret.add(new Position(position.row - 1, position.col, position.distance + add));
            visited[position.row - 1][position.col] = true;
        }

        if (!visited[position.row][position.col + 1] && inBounds(position.row, position.col + 1, grid)
                && grid[position.row][position.col + 1] != 'W' && grid[position.row][position.col + 1] != 'C'
                && !cameraPosition(position.row, position.col + 1, cameraSpaces)) {
            ret.add(new Position(position.row, position.col + 1, position.distance + add));
            visited[position.row][position.col + 1] = true;
        }

        if (!visited[position.row][position.col - 1] && inBounds(position.row, position.col - 1, grid)
                && grid[position.row][position.col - 1] != 'W' && grid[position.row][position.col - 1] != 'C'
                && !cameraPosition(position.row, position.col - 1, cameraSpaces)) {
            ret.add(new Position(position.row, position.col - 1, position.distance + add));
            visited[position.row][position.col - 1] = true;
        }

        return ret;
    }

    boolean inBounds(int row, int col, char[][] grid) {
        int rowEnd = grid.length;
        int colEnd = grid[0].length;
        return row >= 0 && col >= 0 && row < rowEnd && col < colEnd;
    }

    ArrayList<Position> getCameraSpaces(char[][] grid, ArrayList<Position> cameras) {
        ArrayList<Position> ret = new ArrayList<>();
        for (Position camera : cameras) {
            int row = camera.row;
            int col = camera.col;
            while (grid[row][col] != 'W') {
                --col;
                if (grid[row][col] == 'W') {
                    break;
                }
                if (grid[row][col] != 'L' && grid[row][col] != 'R' && grid[row][col] != 'U' && grid[row][col] != 'D'
                        && grid[row][col] != 'W') {
                    ret.add(new Position(row, col));
                }
            }
            row = camera.row;
            col = camera.col;
            while (grid[row][col] != 'W') {
                ++col;
                if (grid[row][col] == 'W') {
                    break;
                }
                if (grid[row][col] != 'L' && grid[row][col] != 'R' && grid[row][col] != 'U' && grid[row][col] != 'D'
                        && grid[row][col] != 'W') {
                    ret.add(new Position(row, col));
                }
            }
            row = camera.row;
            col = camera.col;
            while (grid[row][col] != 'W') {
                --row;
                if (grid[row][col] == 'W') {
                    break;
                }
                if (grid[row][col] != 'L' && grid[row][col] != 'R' && grid[row][col] != 'U' && grid[row][col] != 'D'
                        && grid[row][col] != 'W') {
                    ret.add(new Position(row, col));
                }
            }
            row = camera.row;
            col = camera.col;
            while (grid[row][col] != 'W') {
                ++row;
                if (grid[row][col] == 'W') {
                    break;
                }
                if (grid[row][col] != 'L' && grid[row][col] != 'R' && grid[row][col] != 'U' && grid[row][col] != 'D'
                        && grid[row][col] != 'W') {
                    ret.add(new Position(row, col));
                }
            }
        }
        return ret;
    }

    boolean cameraPosition(int r, int c, ArrayList<Position> cameraSpaces) {
        for (Position pos : cameraSpaces) {
            if (pos.same(r, c)) {
                return true;
            }
        }
        return false;
    }
    // PROBLEM 3 end

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Temp problem = new Temp();
        String[] line1 = reader.readLine().trim().split(" ");
        int N = Integer.parseInt(line1[0]), M = Integer.parseInt(line1[1]);
        char[][] grid = new char[N][M];
        ArrayList<Position> endPositions = new ArrayList<>();
        ArrayList<Position> cameras = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = reader.readLine().trim();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == '.') {
                    endPositions.add(new Position(i, j));
                } else if (grid[i][j] == 'C') {
                    cameras.add(new Position(i, j));
                } else if (grid[i][j] == 'S') {
                    problem.start = new Position(i, j);
                }
            }
        }
        reader.close();
        int[] answers = problem.s3(N, M, grid, endPositions, cameras);
        for (int answer : answers) {
            System.out.println(answer);
        }
    }
}

class Position {
    int row, col, distance;

    Position(int r, int c, int d) {
        row = r;
        col = c;
        distance = d;
    }

    Position(int r, int c) {
        row = r;
        col = c;
    }

    boolean same(int r, int c) {
        return r == row && c == col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}