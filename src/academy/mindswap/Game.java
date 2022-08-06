package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;

import static academy.mindswap.field.Field.screenWriter;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private boolean gameOn = true;
    private int delay;
    private int points =0;
    private static int highScore = 0;
    private int startingCol;
    private int startingRow;

    private boolean gameOverColor = true;

    public Game(int cols, int rows, int delay) throws IOException {
        startingCol = cols;
        startingRow = rows;
        Field.init(cols, rows);
        setHighScore();
        Field.screenWriter.drawString(Field.getWidth()/2-14,0,"Points: "+ points + " Highest Score: " + highScore);
        snake = new Snake();
        this.delay = delay;
        this.fruit = new Fruit();

    }



    public void start() throws InterruptedException, IOException {

         generateFruit(); // uncomment when it's time to introduce fruits

        while (gameOn) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawFruit(fruit);
            Field.drawSnake(snake);
        }
        while (true){
            startNewGame();
        }

    }

    private void startNewGame() throws InterruptedException, IOException {
        Terminal.Color color1 = Terminal.Color.BLUE;
        Terminal.Color color2 = Terminal.Color.GREEN;
        if (gameOverColor){
            Field.drawGameOverPanel(color1);
            gameOverColor = false;
        } else {
            Field.drawGameOverPanel(color2);
            gameOverColor = true;
        }



        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case Enter:
                    points =0;
                    Field.restart(startingCol,startingRow);
                    Field.screenWriter.drawString(Field.getWidth()/2-14,0,"Points: "+ points + " Highest Score: " + highScore);
                    snake = new Snake();
                    this.delay = 100;
                    this.fruit = new Fruit();
                    gameOn = true;
                    start();
            }
        }
    }

    public void finishGame() {
        this.gameOn = false;
    }


    private void generateFruit() {
        fruit.getPosition();
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
                case  Escape: Pause();
                    return;
            }
        }
        snake.move();
    }

    private void Pause() {

        while (true){
            Key k = Field.readInput();
        if (k != null) {
            switch (k.getKind()) {
                case Enter:
                    return;

                }
            }
        }
    }

    private void checkCollisions() throws IOException {

        if (snake.getHead().getRow() == 0 || snake.getHead().getRow() == Field.getHeight()-1){
            snake.die();
            finishGame();
        }
        if (snake.getHead().getCol() == 0 || snake.getHead().getCol() == Field.getWidth()-1){
            snake.die();
            finishGame();
        }
        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if(snake.getFullSnake().get(i).equals(snake.getHead())){
                snake.die();
                finishGame();
            }
        }

        if (fruit.getPosition().equals(snake.getHead())){
            snake.increaseSize();
            fruit=new Fruit();
            points++;
            if (points > highScore){
                highScore = points;
            }
            delay-=5;
            Field.screenWriter.drawString(Field.getWidth()/2-14,0,"Points: "+ points + " Highest Score: " + highScore);
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/henriqueguimaraes/Documents/Repositorio/SnakeGame/snake-game-skeleton/resources/HighScore.txt", false));
            writer.write(String.valueOf(highScore));
            writer.flush();
            writer.close();
        }

    }

    private void setHighScore() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/henriqueguimaraes/Documents/Repositorio/SnakeGame/snake-game-skeleton/resources/HighScore.txt"));
        highScore = Integer.parseInt(reader.readLine());

    }
}
