import java.util.ArrayList;
import java.util.List;

public class MarsRover {
private int x;
private int y;
private Direction direction;
private List<Obstacle> obstacles;

public MarsRover() {
    this(0, 0, Direction.NORTH);
}

public MarsRover(int x, int y, Direction direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.obstacles = new ArrayList<MarsRover.Obstacle>();
}

public String move(String commands) {
    for (char c : commands.toCharArray()) {
        if (c == 'R') {
            direction = direction.turnRight();
        } else if (c == 'L') {
            direction = direction.turnLeft();
        } else if (c == 'M') {
            moveForward();
            Obstacle obstacle = checkObstacles();
            if (obstacle != null) {
                return getCurrentPosition() + " " + obstacle.getMessage();
            }
        } else {
            throw new IllegalArgumentException("Invalid command: " + c);
        }
    }
    return getCurrentPosition();
}

private void moveForward() {
    switch (direction) {
        case NORTH:
            y = (y + 1) % 10;
            break;
        case EAST:
            x = (x + 1) % 10;
            break;
        case SOUTH:
            y = (y - 1 + 10) % 10;
            break;
        case WEST:
            x = (x - 1 + 10) % 10;
            break;
    }
}

public void addObstacle(int x, int y) {
    obstacles.add(new Obstacle(x, y));
}

public String obstacle() {
    if (checkObstacles() != null) {
        return checkObstacles().getMessage();
    } else {
        return "";
    }
}

private Obstacle checkObstacles() {
    for (Obstacle obstacle : obstacles) {
        if (obstacle.getX() == x && obstacle.getY() == y) {
            return obstacle;
        }
    }
    return null;
}

public String getCurrentPosition() {
    return x + "," + y + "," + direction.getShortName();
}

private static class Obstacle {
    private final int x;
    private final int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getMessage() {
        return "Obstacle at position (" + x + "," + y + ")";
    }
}

private enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private final String shortName;

    Direction(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public Direction turnRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                throw new IllegalArgumentException("Unknown direction: " + this);
        }
    }

    public Direction turnLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                throw new IllegalArgumentException("Unknown direction: " + this);
        }
    }
}
}