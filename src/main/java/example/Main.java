package example;


import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        OutputStream out = System.out;

        if (args.length >= 2) {
            in = new FileInputStream(args[0]);
            out = new FileOutputStream(args[1]);
        }

        try (Scanner scanner = new Scanner(in);
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(out))) {

            int generations = scanner.nextInt();
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            scanner.nextLine();

            char[][] field = new char[height][width];

            for (int r = 0; r < height; r++) {
                if (!scanner.hasNextLine()) {
                    throw new IllegalStateException("Недостатньо рядків для поля");
                }
                String line = scanner.nextLine();

                String normalized = line.replace(" ", "");
                if (normalized.length() < width) {
                    throw new IllegalStateException("Занадто короткий рядок поля: " + line);
                }
                for (int c = 0; c < width; c++) {
                    char ch = normalized.charAt(c);
                    if (ch != '.' && ch != 'x') {
                        throw new IllegalStateException("Некоректний символ у полі: '" + ch + "'");
                    }
                    field[r][c] = ch;
                }
            }

            char[][] result = GameOfLife.evolve(field, generations);

            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    writer.print(result[r][c]);
                    if (c + 1 < width) {
                        writer.print(' ');
                    }
                }
                writer.println();
            }
        }
    }
}