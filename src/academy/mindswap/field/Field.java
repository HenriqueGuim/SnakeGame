package academy.mindswap.field;

import academy.mindswap.Game;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public final class Field {

    private static final String BORDER_STRING = "▒";
    private static final String GAME_OVER_STRING = "▒";
    private static final String SNAKE_BODY_STRING = "/";
    private static final String SNAKE_HEAD_STRING = " ";
    private static final String FRUIT_STRING = "@";
    private static final Terminal.Color GAME_OVER_BACKGROUND_COLOR = null;

    private static int width;
    private static int height;
    private static Screen screen;
    public static ScreenWriter screenWriter;

    private Field() {
    }

    public static void init(int width, int height) {

        screen = TerminalFacade.createScreen();

        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        drawWalls();
        screen.refresh();
    }

    public static void restart(int width, int height) {
        screen.clear();
        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        drawWalls();
        screen.refresh();
    }

    public static void drawSnake(Snake snake) {

        Terminal.Color snakeColor = Terminal.Color.GREEN;

        if (!snake.isAlive()) {
            snakeColor = Terminal.Color.RED;
        }

        Position head = snake.getHead();

        for (Position p : snake.getFullSnake()) {
            if (!p.equals(head)) {
                screen.putString(p.getCol(), p.getRow(), SNAKE_BODY_STRING, snakeColor, Terminal.Color.GREEN);
            } else {
                screen.putString(p.getCol(), p.getRow(), SNAKE_HEAD_STRING, snakeColor, Terminal.Color.YELLOW);
            }
        }
        screen.refresh();
    }

    public static void clearTail(Snake snake) {
        Position tail = snake.getTail();
        screen.putString(tail.getCol(), tail.getRow(), " ", null, null);
    }

    private static void drawWalls() {
        for (int i = 0; i < width; i++) {
            screenWriter.drawString(i, 0, BORDER_STRING);
            screenWriter.drawString(i, height - 1, BORDER_STRING);
        }

        for (int j = 0; j < height; j++) {
            screenWriter.drawString(0, j, BORDER_STRING);
            screenWriter.drawString(width - 1, j, BORDER_STRING);
        }
    }

    public static Key readInput() {
        return screen.readInput();
    }

    public static void drawFruit(Fruit fruit) {
        screen.putString(fruit.getPosition().getCol(), fruit.getPosition().getRow(), FRUIT_STRING, Terminal.Color.RED, Terminal.Color.BLUE);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    public static void drawGameOverPanel(Terminal.Color color) {
        //row 1
        for (int i = 47; i <= 54; i++) {
            screen.putString(i, 0, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 2
        for (int i = 45; i <= 47; i++) {
            screen.putString(i, 1, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 54; i <= 56; i++) {
            screen.putString(i, 1, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 3
        for (int i = 44; i <= 45; i++) {
            screen.putString(i, 2, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }for (int i = 56; i <= 57; i++) {
            screen.putString(i, 2, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 4
        for (int i = 43; i <= 44; i++) {
            screen.putString(i, 3, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 57; i <= 58; i++) {
            screen.putString(i, 3, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 5
        for (int i = 42; i <= 43; i++) {
            screen.putString(i, 4, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 58; i <= 59; i++) {
            screen.putString(i, 4, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 6
        screen.putString(42, 5, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(59, 5, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 7
        screen.putString(59, 6, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(59, 6, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 8
        for (int i = 9; i <= 10; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 14; i <= 15; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(18, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(22, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 24; i <= 27; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 41; i <= 42; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 59; i <= 60; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 65; i <= 66; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(69, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(73, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 75; i <= 78; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 80; i <= 82; i++) {
            screen.putString(i, 7, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 9
        screen.putString(8, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(13, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(16, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 18; i <= 19; i++) {
            screen.putString(i, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 21; i <= 22; i++) {
            screen.putString(i, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(24, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(41, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(60, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(64, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(67, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(69, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(73, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(75, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(80, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(83, 8, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row10

        screen.putString(8, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 10; i <= 11; i++) {
            screen.putString(i, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 13; i <= 16; i++) {
            screen.putString(i, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(18, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(20, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(22, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 24; i <= 26; i++) {
            screen.putString(i, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(41, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(60, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(64, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(67, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(70, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(72, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 75; i <= 77; i++) {
            screen.putString(i, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 80; i <= 82; i++) {
            screen.putString(i, 9, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 11
        screen.putString(10, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(11, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(13, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(16, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(18, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(22, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(24, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(41, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 46; i <= 52; i++) {
            screen.putString(i, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 54; i <= 55; i++) {
            screen.putString(i, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(60, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(64, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(67, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(70, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(72, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(75, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(80, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(82, 10, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 12
        for (int i = 9; i <= 10; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(13, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(16, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(18, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(22, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 24; i <= 27; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 41; i <= 42; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(45, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(48, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(53, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(56, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 59; i <= 60; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 65; i <= 66; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(71, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 75; i <= 78; i++) {
            screen.putString(i, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(80, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(83, 11, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //Row 13
        screen.putString(42, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(44, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(49, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(52, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(57, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(59, 12, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 14
        screen.putString(42, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(45, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(48, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(53, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(56, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(59, 13, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 15
        screen.putString(42, 14, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 46; i <= 47; i++) {
            screen.putString(i, 14, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 50; i <= 51; i++) {
            screen.putString(i, 14, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 54; i <= 55; i++) {
            screen.putString(i, 14, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(59, 14, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 16
        screen.putString(42, 15, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(49, 15, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(52, 15, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(59, 15, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 17
        for (int i = 43; i <= 44; i++) {
            screen.putString(i, 16, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(48, 16, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(53, 16, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 57; i <= 58; i++) {
            screen.putString(i, 16, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 18
        screen.putString(44, 17, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(46, 17, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 49; i <= 52; i++) {
            screen.putString(i, 17, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(55, 17, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(57, 17, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 19
        screen.putString(44, 18, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 46; i <= 47; i++) {
            screen.putString(i, 18, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 54; i <= 55; i++) {
            screen.putString(i, 18, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(57, 18, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 20
        screen.putString(44, 19, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(46, 19, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 48; i <= 53; i++) {
            screen.putString(i, 19, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(55, 19, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(57, 19, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 21
        for (int i = 44; i <= 45; i++) {
            screen.putString(i, 20, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(47, 20, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        screen.putString(54, 20, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 56; i <= 57; i++) {
            screen.putString(i, 20, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 22
        screen.putString(45, 21, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        for (int i = 48; i <= 53; i++) {
            screen.putString(i, 21, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.putString(56, 21, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        //row 23
        for (int i = 45; i <= 47; i++) {
            screen.putString(i, 22, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 54; i <= 56; i++) {
            screen.putString(i, 22, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 24
        for (int i = 47; i <= 49; i++) {
            screen.putString(i, 23, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        for (int i = 52; i <= 54; i++) {
            screen.putString(i, 23, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        //row 25
        for (int i = 49; i <= 52; i++) {
            screen.putString(i, 52, GAME_OVER_STRING, color, GAME_OVER_BACKGROUND_COLOR);
        }
        screen.refresh();
    }
}
