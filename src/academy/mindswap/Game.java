package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private boolean gameOn = true;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
        this.fruit = new Fruit();
    }

    public void start() throws InterruptedException {

         generateFruit(); // uncomment when it's time to introduce fruits

        while (gameOn) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawFruit(fruit);
            Field.drawSnake(snake);
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
            }
        }
        snake.move();
    }

    private void checkCollisions() {
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
        }

    }


}
