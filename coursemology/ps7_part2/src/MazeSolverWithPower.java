import java.util.*;

public class MazeSolverWithPower implements IMazeSolverWithPower {
    private static final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
    private static int[][] DELTAS = new int[][] {
        { -1, 0 },
        { 1, 0 },
        { 0, 1 },
        { 0, -1 }
    };

    private Maze maze;
    private int rows, cols;
    private int[] reachableCount;

    public MazeSolverWithPower() {}

    @Override
    public void initialize(Maze maze) {
        this.maze = maze;
        this.rows = maze.getRows();
        this.cols = maze.getColumns();
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    @Override
    public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
        return pathSearch(startRow, startCol, endRow, endCol, 0);
    }

    @Override
    public Integer pathSearch(int startRow, int startCol, int endRow,
                              int endCol, int startingPower) throws Exception {

        if (!inBounds(startRow, startCol) || !inBounds(endRow, endCol)) {
            throw new Exception("Invalid coordinates");
        }

        int[][][] dist = new int[rows][cols][startingPower + 1];
        int[][][] parentRow = new int[rows][cols][startingPower + 1];
        int[][][] parentCol = new int[rows][cols][startingPower + 1];
        int[][][] parentPower = new int[rows][cols][startingPower + 1];

        reachableCount = new int[rows * cols];

        // init
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze.getRoom(i, j).onPath = false;
                for (int p = 0; p <= startingPower; p++) {
                    dist[i][j][p] = -1;
                    parentRow[i][j][p] = -1;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol, startingPower});
        dist[startRow][startCol][startingPower] = 0;

        // BFS
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], p = curr[2];

            Room room = maze.getRoom(r, c);

            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];

                if (!inBounds(nr, nc)) continue;

                boolean hasWall =
                    (d == NORTH && room.hasNorthWall()) ||
                    (d == SOUTH && room.hasSouthWall()) ||
                    (d == EAST && room.hasEastWall()) ||
                    (d == WEST && room.hasWestWall());

                // move normally
                if (!hasWall) {
                    if (dist[nr][nc][p] == -1) {
                        dist[nr][nc][p] = dist[r][c][p] + 1;
                        parentRow[nr][nc][p] = r;
                        parentCol[nr][nc][p] = c;
                        parentPower[nr][nc][p] = p;
                        q.add(new int[]{nr, nc, p});
                    }
                }
                // break wall
                else if (p > 0) {
                    if (dist[nr][nc][p - 1] == -1) {
                        dist[nr][nc][p - 1] = dist[r][c][p] + 1;
                        parentRow[nr][nc][p - 1] = r;
                        parentCol[nr][nc][p - 1] = c;
                        parentPower[nr][nc][p - 1] = p;
                        q.add(new int[]{nr, nc, p - 1});
                    }
                }
            }
        }

        // best end
        int bestDist = Integer.MAX_VALUE;
        int bestPower = -1;

        for (int p = 0; p <= startingPower; p++) {
            if (dist[endRow][endCol][p] != -1 &&
                dist[endRow][endCol][p] < bestDist) {
                bestDist = dist[endRow][endCol][p];
                bestPower = p;
            }
        }

        if (bestPower == -1) return null;

        // reconstruct path
        int r = endRow, c = endCol, p = bestPower;

        while (r != -1) {
            maze.getRoom(r, c).onPath = true;
            int pr = parentRow[r][c][p];
            int pc = parentCol[r][c][p];
            int pp = parentPower[r][c][p];
            r = pr;
            c = pc;
            p = pp;
        }

        // compute reachable
        int[][] minDist = new int[rows][cols];
        for (int i = 0; i < rows; i++) Arrays.fill(minDist[i], -1);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int pwr = 0; pwr <= startingPower; pwr++) {
                    if (dist[i][j][pwr] != -1) {
                        if (minDist[i][j] == -1 ||
                            dist[i][j][pwr] < minDist[i][j]) {
                            minDist[i][j] = dist[i][j][pwr];
                        }
                    }
                }
            }
        }

        Arrays.fill(reachableCount, 0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (minDist[i][j] != -1) {
                    reachableCount[minDist[i][j]]++;
                }
            }
        }

        return bestDist;
    }

    @Override
    public Integer numReachable(int k) throws Exception {
        if (k < 0 || k >= reachableCount.length) return 0;
        return reachableCount[k];
    }
}
