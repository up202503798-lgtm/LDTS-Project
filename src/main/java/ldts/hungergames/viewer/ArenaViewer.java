package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.arena.ArenaType;

import java.util.List;
import java.util.Map;

public class ArenaViewer {
    private final ArenaType type;

    public ArenaViewer() {
        this(ArenaType.INITIAL);
    }

    public ArenaViewer(ArenaType type) {
        this.type = type;
    }

    public void draw(GUI gui, Arena arena, Character player) {
        draw(gui, arena, player, List.of());
    }

    public void draw(GUI gui, Arena arena, Character player, List<Character> competitors) {
        gui.clear();
        drawWalls(gui, arena);
        drawItems(gui, arena);
        drawCompetitors(gui, competitors);
        Position position = player.getPosition();
        gui.drawChar(position.x(), position.y(), '@', GuiColor.CYAN);
        drawStatus(gui, player);
    }

    private void drawWalls(GUI gui, Arena arena) {
        boolean forest = type == ArenaType.FOREST;
        char symbol = forest ? 'T' : '#';
        GuiColor color = forest ? GuiColor.GREEN : GuiColor.WHITE;
        for (Position wall : arena.getWalls()) {
            gui.drawChar(wall.x(), wall.y(), symbol, color);
        }
    }

    private void drawItems(GUI gui, Arena arena) {
        for (Map.Entry<Position, ItemType> item : arena.getItems().entrySet()) {
            Position position = item.getKey();
            switch (item.getValue()) {
                case SUPPLY -> gui.drawChar(position.x(), position.y(), '*', GuiColor.YELLOW);
                case MEDKIT -> gui.drawChar(position.x(), position.y(), '+', GuiColor.GREEN);
            }
        }
    }

    private void drawCompetitors(GUI gui, List<Character> competitors) {
        for (Character competitor : competitors) {
            if (competitor.isAlive()) {
                Position position = competitor.getPosition();
                gui.drawChar(position.x(), position.y(), competitor.getName().charAt(0), GuiColor.RED);
            }
        }
    }

    private void drawStatus(GUI gui, Character player) {
        gui.drawText(2, 0, player.getName() + " | Vida: " + player.getHealth() + "/" + player.getMaxHealth()
                + " | Suprimentos: " + player.getSupplies(), GuiColor.WHITE);
    }
}