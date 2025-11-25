package example;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java -jar lab1-game-of-life.jar <inputFile> <outputFile>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        File inFile = new File(inputFile);
        if (!inFile.exists()) {
            System.err.println("Error: Input file does not exist: " + inputFile);
            return;
        }

        try (
                Scanner scanner = new Scanner(new FileInputStream(inputFile));
                PrintWriter writer = new PrintWriter(new FileOutputStream(outputFile))
        ) {

            int generations = scanner.nextInt();

            int width = scanner.nextInt();
            int height = scanner.nextInt();
            scanner.nextLine();

            char[][] field = new char[height][width];

            for (int r = 0; r < height; r++) {

                if (!scanner.hasNextLine()) {
                    throw new IllegalStateException("Недостатньо рядків у вхідному файлі!");
                }

                String line = scanner.nextLine().replace(" ", "");

                if (line.length() != width) {
                    throw new IllegalStateException(
                            "Некоректна довжина рядка (очікується " + width + "): " + line
                    );
                }

                for (int c = 0; c < width; c++) {
                    char ch = line.charAt(c);
                    if (ch != '.' && ch != 'x') {
                        throw new IllegalStateException("Некоректний символ '" + ch + "' у рядку: " + line);
                    }
                    field[r][c] = ch;
                }
            }

            char[][] result = GameOfLife.evolve(field, generations);

            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    writer.print(result[r][c]);
                    if (c + 1 < width) writer.print(' ');
                }
                writer.println();
            }

        } catch (IOException e) {
            System.err.println("Помилка роботи з файлами: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}
