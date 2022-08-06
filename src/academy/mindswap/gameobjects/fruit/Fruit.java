package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.Random;


public class Fruit {

    Position position;

    public Fruit() {
        this.position = new Position(new int[]{randomNumber(Field.getWidth()), randomNumber(Field.getHeight())});
    }

    public Position getPosition() {
        return position;
    }


    private int randomNumber(int value){
        return new Random().nextInt(1, value-1);
    }
}
