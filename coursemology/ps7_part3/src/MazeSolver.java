import java.util.*;
import java.util.function.Function;

public class MazeSolver implements IMazeSolver {
    private static final int TRUE_WALL = Integer.MAX_VALUE;

    private static final List<Function<Room, Integer>> WALL_FUNCTIONS = Arrays.asList(
        Room::getNorthWall,
        Room::getEastWall,
        Room::getWestWall,
        Room::getSouthWall
    );

    private static final int[][] DELTAS = new int[][] {
        { -1, 0 }, // North
        { 0, 1 },  // East
        { 0, -1 }, // West
        { 1, 0 }   // South
    };

    private Maze maze;
    private int rows, cols;

    public MazeSolver() {}

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

        if (!inBounds(startRow, startCol) || !inBounds(endRow, endCol)) {
            throw new Exception("Invalid coordinates");
        }

        int[][] dist = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        dist[startRow][startCol] = 0;
        pq.add(new int[]{startRow, startCol, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];

            // Skip outdated states
            if (cost > dist[r][c]) continue;

            // If we reached the destination
            if (r == endRow && c == endCol) {
                return cost;
            }

            Room room = maze.getRoom(r, c);

            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];

                if (!inBounds(nr, nc)) continue;

                int wallCost = WALL_FUNCTIONS.get(d).apply(room);

                // Cannot pass TRUE_WALL
                if (wallCost == TRUE_WALL) continue;

                int newCost = cost + wallCost;

                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.add(new int[]{nr, nc, newCost});
                }
            }
        }

        return null; // No path found
    }

    @Override
    public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
        return null;
    }

    @Override
    public Integer bonusSearch(int startRow, int startCol, int endRow,
                              int endCol, int sRow, int sCol) throws Exception {
        return null;
    }
}
