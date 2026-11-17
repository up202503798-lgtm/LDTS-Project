package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.Trait;
import ldts.hungergames.model.selection.CharacterSelectionModel;

import java.util.List;

public class CharacterSelectionViewer {
    private static final int MARGIN = 2;
    private static final int LIST_TOP = 3;

    public void draw(GUI gui, CharacterSelectionModel model) {
        gui.clear();
        gui.drawText(MARGIN, 1, "ESCOLHE O TEU PERSONAGEM", GuiColor.YELLOW);
        drawList(gui, model);
        drawDetails(gui, model.getSelected(), LIST_TOP + model.getCharacters().size() + 1);
        gui.drawText(MARGIN, gui.getHeight() - 2, "A/D ou setas: mudar | Enter: confirmar", GuiColor.WHITE);
    }

    private void drawList(GUI gui, CharacterSelectionModel model) {
        List<Character> characters = model.getCharacters();
        for (int i = 0; i < characters.size(); i++) {
            boolean selected = i == model.getSelectedIndex();
            String text = (selected ? "> " : "  ") + characters.get(i).getName();
            gui.drawText(MARGIN, LIST_TOP + i, text, selected ? GuiColor.YELLOW : GuiColor.WHITE);
        }
    }

    private void drawDetails(GUI gui, Character character, int top) {
        gui.drawText(MARGIN, top, "Vida: " + character.getMaxHealth(), GuiColor.WHITE);
        gui.drawText(MARGIN, top + 1, "Suprimentos: " + character.getSupplies(), GuiColor.WHITE);
        int line = top + 2;
        for (Trait trait : character.getTraits()) {
            gui.drawText(MARGIN, line++, trait.getLabel() + ": " + trait.getDescription(), GuiColor.WHITE);
        }
    }
}