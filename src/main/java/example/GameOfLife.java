package example;


public class GameOfLife {

    private static final char DEAD = '.';
    private static final char ALIVE = 'x';


    public static int countAliveNeighbors(char[][] field, int row, int col) {
        int height = field.length;
        int width = field[0].length;

        int alive = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {

                if (dr == 0 && dc == 0) {
                    continue;
                }
                int nr = (row + dr + height) % height;
                int nc = (col + dc + width) % width;
                if (field[nr][nc] == ALIVE) {
                    alive++;
                }
            }
        }

        return alive;
    }


    public static char nextCellState(char[][] field, int row, int col) {
        char current = field[row][col];
        int aliveNeighbors = countAliveNeighbors(field, row, col);

        if (current == ALIVE) {
            if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                return ALIVE;
            } else {
                return DEAD;
            }
        } else {
            if (aliveNeighbors == 3) {
                return ALIVE;
            } else {
                return DEAD;
            }
        }
    }


    public static char[][] nextGeneration(char[][] field) {
        int height = field.length;
        int width = field[0].length;

        char[][] result = new char[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                result[r][c] = nextCellState(field, r, c);
            }
        }

        return result;
    }


    public static char[][] evolve(char[][] initialField, int generations) {
        char[][] current = copyField(initialField);
        for (int i = 0; i < generations; i++) {
            current = nextGeneration(current);
        }
        return current;
    }


    public static char[][] copyField(char[][] field) {
        int height = field.length;
        int width = field[0].length;
        char[][] copy = new char[height][width];
        for (int r = 0; r < height; r++) {
            System.arraycopy(field[r], 0, copy[r], 0, width);
        }
        return copy;
    }
}
