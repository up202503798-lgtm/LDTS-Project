package ldts.hungergames.model.selection;

import ldts.hungergames.model.character.Character;

import java.util.List;

public class CharacterSelectionModel {
    private final List<Character> characters;
    private int selectedIndex;

    public CharacterSelectionModel(List<Character> characters) {
        if (characters.isEmpty()) {
            throw new IllegalArgumentException("At least one character is required");
        }
        this.characters = List.copyOf(characters);
        this.selectedIndex = 0;
    }

    public List<Character> getCharacters() { return characters; }
    public int getSelectedIndex() { return selectedIndex; }
    public Character getSelected() { return characters.get(selectedIndex); }

    public void next() {
        selectedIndex = (selectedIndex + 1) % characters.size();
    }

    public void previous() {
        selectedIndex = (selectedIndex - 1 + characters.size()) % characters.size();
    }
}