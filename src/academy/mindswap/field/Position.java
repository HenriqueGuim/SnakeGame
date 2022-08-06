package academy.mindswap.field;

import java.util.Arrays;

public class Position {
    private int[] position;

    public Position(int[] position) {
        this.position = position;
    }

    public int getCol() {
        return position[0];
    }

    public int getRow() {
        return position[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return Arrays.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }
}
