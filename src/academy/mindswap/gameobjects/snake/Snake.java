package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 4;
    private Direction direction;
    private boolean alive = true;
    private LinkedList<Position> snakeBody;


    public Snake(){
        snakeBody = new LinkedList<>();
        for (int i = 0; i <SNAKE_INITIAL_SIZE ; i++) {
            snakeBody.add(new Position(new int[]{Field.getWidth()/2-i,Field.getHeight()/2}));
        }
        this.direction = Direction.RIGHT;
    }

    public void increaseSize() {
        int headRow = getHead().getCol();
        int headCol = getHead().getRow();

        int directionRow = direction.getPosition()[0];
        int directionCol = direction.getPosition()[1];

        snakeBody.addFirst(new Position(new int[]{headRow+directionRow, headCol+directionCol}));

    }

    public void move(Direction direction) {
        this.direction = direction;

        int headRow = getHead().getCol();
        int headCol = getHead().getRow();

        int directionRow = direction.getPosition()[0];
        int directionCol = direction.getPosition()[1];

        snakeBody.addFirst(new Position(new int[]{headRow+directionRow, headCol+directionCol}));
        snakeBody.remove(getTail());
    }

    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {

        return snakeBody.getFirst();
    }

    public Position getTail() {

        return snakeBody.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return snakeBody;
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }
}

