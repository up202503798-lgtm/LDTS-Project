package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.selection.CharacterSelectionModel;
import ldts.hungergames.state.ArenaState;
import ldts.hungergames.state.StateMachine;

public class CharacterSelectionController {
    private final CharacterSelectionModel model;

    public CharacterSelectionController(CharacterSelectionModel model) {
        this.model = model;
    }

    public void update(Action action, StateMachine machine) {
        if (action == null) {
            return;
        }
        switch (action) {
            case LEFT -> model.previous();
            case RIGHT -> model.next();
            case SELECT -> machine.changeState(new ArenaState()); // J4: passar model.getSelected() à arena
            default -> { }
        }
    }
}