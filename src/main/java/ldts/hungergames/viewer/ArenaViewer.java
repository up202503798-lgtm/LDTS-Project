package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.Position;

import java.util.Map;

public class ArenaViewer {
    public void draw(GUI gui, Arena arena, Character player) {
        gui.clear();
        drawWalls(gui, arena);
        drawItems(gui, arena);
        Position position = player.getPosition();
        gui.drawChar(position.x(), position.y(), '@', GuiColor.CYAN);
        drawStatus(gui, player);
    }

    private void drawWalls(GUI gui, Arena arena) {
        for (Position wall : arena.getWalls()) {
            gui.drawChar(wall.x(), wall.y(), '#', GuiColor.WHITE);
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

    private void drawStatus(GUI gui, Character player) {
        gui.drawText(2, 0, player.getName() + " | Vida: " + player.getHealth() + "/" + player.getMaxHealth()
                + " | Suprimentos: " + player.getSupplies(), GuiColor.WHITE);
    }
}