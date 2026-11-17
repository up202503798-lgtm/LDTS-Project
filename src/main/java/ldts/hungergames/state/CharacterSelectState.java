package ldts.hungergames.state;

import ldts.hungergames.controller.CharacterSelectionController;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.selection.CharacterSelectionModel;
import ldts.hungergames.viewer.CharacterSelectionViewer;

public class CharacterSelectState implements State {
    private final CharacterSelectionModel model;
    private final CharacterSelectionViewer viewer;
    private final CharacterSelectionController controller;

    public CharacterSelectState() {
        this(new CharacterSelectionModel(new CharacterFactory().createAll(new Position(0, 0))));
    }

    public CharacterSelectState(CharacterSelectionModel model) {
        this.model = model;
        this.viewer = new CharacterSelectionViewer();
        this.controller = new CharacterSelectionController(model);
    }

    @Override
    public void update(Action action, StateMachine machine) {
        controller.update(action, machine);
    }

    @Override
    public void draw(GUI gui) {
        viewer.draw(gui, model);
    }
}