package canvas.draw;

import java.util.Scanner;

public class DrawCanvas {

    private char[][] canvas;
    private int width;
    private int height;

    public static void main(String[] args) {
        DrawCanvas drawer = new DrawCanvas();
        drawer.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            switch (command) {
                case "C":
                    int w = Integer.parseInt(tokens[1]);
                    int h = Integer.parseInt(tokens[2]);
                    createCanvas(w, h);
                    break;
                case "L":
                    int x1 = Integer.parseInt(tokens[1]);
                    int y1 = Integer.parseInt(tokens[2]);
                    int x2 = Integer.parseInt(tokens[3]);
                    int y2 = Integer.parseInt(tokens[4]);
                    drawLine(x1, y1, x2, y2);
                    break;
                case "R":
                    int rx1 = Integer.parseInt(tokens[1]);
                    int ry1 = Integer.parseInt(tokens[2]);
                    int rx2 = Integer.parseInt(tokens[3]);
                    int ry2 = Integer.parseInt(tokens[4]);
                    drawRectangle(rx1, ry1, rx2, ry2);
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid command");
            }
            drawCanvas();
        }
    }

    private void createCanvas(int w, int h) {
        width = w + 2; // add 2 for borders
        height = h + 2;
        canvas = new char[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    canvas[row][col] = '-';
                } else {
                    canvas[row][col] = ' ';
                }
            }
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        //check if it is a vertical line
        if (x1 == x2) {
            for (int row = Math.min(y1, y2); row <= Math.max(y1, y2); row++) {
                canvas[row][x1] = 'x';
            }
        } else if (y1 == y2) { //check if it is a horizontal line
            for (int col = Math.min(x1, x2); col <= Math.max(x1, x2); col++) {
                canvas[y1][col] = 'x';
            }
        } else {
            System.out.println("Currently only horizontal or vertical lines are supported.");
        }
    }

    private void drawRectangle(int x1, int y1, int x2, int y2) {
        drawLine(x1, y1, x2, y1);
        drawLine(x1, y1, x1, y2);
        drawLine(x2, y1, x2, y2);
        drawLine(x1, y2, x2, y2);
    }

    private void drawCanvas() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(canvas[row][col]);
            }
            System.out.println();
        }
    }
}
