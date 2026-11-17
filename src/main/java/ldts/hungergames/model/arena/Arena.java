package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Arena {
    private final int width;
    private final int height;
    private final Set<Position> walls;
    private final Map<Position, ItemType> items;
    private final Position startPosition;

    Arena(int width, int height, Set<Position> walls, Map<Position, ItemType> items, Position startPosition) {
        this.width = width;
        this.height = height;
        this.walls = new HashSet<>(walls);
        this.items = new HashMap<>(items);
        this.startPosition = startPosition;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Position getStartPosition() { return startPosition; }
    public Set<Position> getWalls() { return Collections.unmodifiableSet(walls); }
    public Map<Position, ItemType> getItems() { return Collections.unmodifiableMap(items); }

    public boolean isInside(Position position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    public boolean isWall(Position position) {
        return walls.contains(position);
    }

    public boolean isWalkable(Position position) {
        return isInside(position) && !isWall(position);
    }

    public Optional<ItemType> getItemAt(Position position) {
        return Optional.ofNullable(items.get(position));
    }

    public Optional<ItemType> removeItem(Position position) {
        return Optional.ofNullable(items.remove(position));
    }
}