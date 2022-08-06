package academy.mindswap.gameobjects.snake;

public enum Direction {
    UP(new int[] {0,-1}),
    DOWN(new int[] {0,1}),
    LEFT(new int[]{-1,0}),
    RIGHT(new int[]{1,0});
    private int[]position;

    Direction(int[] position) {
        this.position= position;
    }

    public int[] getPosition() {
        return position;
    }
}
