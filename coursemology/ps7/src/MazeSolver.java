import java.util.*;

public class MazeSolver implements IMazeSolver {
    private static final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
    private static int[][] DELTAS = new int[][] {
        { -1, 0 },
        { 1, 0 },
        { 0, 1 },
        { 0, -1 }
    };

    private Maze maze;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dist;
    private int[][] parentRow;
    private int[][] parentCol;
    private int[] reachableCount;

    public MazeSolver() {}

    @Override
    public void initialize(Maze maze) {
        this.maze = maze;
        this.rows = maze.getRows();
        this.cols = maze.getColumns();

        this.visited = new boolean[rows][cols];
        this.dist = new int[rows][cols];
        this.parentRow = new int[rows][cols];
        this.parentCol = new int[rows][cols];
        this.reachableCount = new int[rows * cols];
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    @Override
    public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {

        if (!inBounds(startRow, startCol) || !inBounds(endRow, endCol)) {
            throw new Exception("Invalid coordinates");
        }

        // reset
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = false;
                dist[i][j] = -1;
                parentRow[i][j] = -1;
                parentCol[i][j] = -1;
                maze.getRoom(i, j).onPath = false;
            }
        }

        Arrays.fill(reachableCount, 0);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});

        visited[startRow][startCol] = true;
        dist[startRow][startCol] = 0;

        // BFS
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            Room room = maze.getRoom(r, c);

            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];

                if ((d == NORTH && room.hasNorthWall()) ||
                    (d == SOUTH && room.hasSouthWall()) ||
                    (d == EAST && room.hasEastWall()) ||
                    (d == WEST && room.hasWestWall())) {
                    continue;
                }

                if (!inBounds(nr, nc) || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                dist[nr][nc] = dist[r][c] + 1;
                parentRow[nr][nc] = r;
                parentCol[nr][nc] = c;

                q.add(new int[]{nr, nc});
            }
        }

        // count distances
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dist[i][j] != -1) {
                    reachableCount[dist[i][j]]++;
                }
            }
        }

        if (dist[endRow][endCol] == -1) {
            return null;
        }

        // reconstruct path
        int r = endRow;
        int c = endCol;

        while (r != -1) {
            maze.getRoom(r, c).onPath = true;

            int pr = parentRow[r][c];
            int pc = parentCol[r][c];

            r = pr;
            c = pc;
        }

        return dist[endRow][endCol];
    }

    @Override
    public Integer numReachable(int k) throws Exception {
        if (k < 0 || k >= reachableCount.length) {
            return 0;
        }
        return reachableCount[k];
    }
}
