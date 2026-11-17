package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ArenaBuilder {
    private static final int MIN_SIZE = 5;

    private final int width;
    private final int height;
    private final Random random;
    private final Position startPosition;
    private final Set<Position> walls = new HashSet<>();
    private final Map<Position, ItemType> items = new HashMap<>();

    public ArenaBuilder(int width, int height, Random random) {
        if (width < MIN_SIZE || height < MIN_SIZE) {
            throw new IllegalArgumentException("Arena too small");
        }
        this.width = width;
        this.height = height;
        this.random = random;
        this.startPosition = new Position(width / 2, height / 2);
    }

    public ArenaBuilder withBorderWalls() {
        for (int x = 0; x < width; x++) {
            walls.add(new Position(x, 0));
            walls.add(new Position(x, height - 1));
        }
        for (int y = 0; y < height; y++) {
            walls.add(new Position(0, y));
            walls.add(new Position(width - 1, y));
        }
        return this;
    }

    public ArenaBuilder withRandomWalls(int count) {
        for (Position position : pickFreePositions(count)) {
            walls.add(position);
        }
        return this;
    }

    public ArenaBuilder withItems(ItemType type, int count) {
        for (Position position : pickFreePositions(count)) {
            items.put(position, type);
        }
        return this;
    }

    public Arena build() {
        return new Arena(width, height, walls, items, startPosition);
    }

    public static Arena initialArena(int width, int height, Random random) {
        int area = width * height;
        return new ArenaBuilder(width, height, random)
                .withBorderWalls()
                .withRandomWalls(area / 100)
                .withItems(ItemType.SUPPLY, area / 150)
                .withItems(ItemType.MEDKIT, area / 400)
                .build();
    }

    private List<Position> pickFreePositions(int count) {
        List<Position> free = new ArrayList<>();
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                Position position = new Position(x, y);
                if (!walls.contains(position) && !items.containsKey(position) && !position.equals(startPosition)) {
                    free.add(position);
                }
            }
        }
        Collections.shuffle(free, random);
        return free.subList(0, Math.min(count, free.size()));
    }
}