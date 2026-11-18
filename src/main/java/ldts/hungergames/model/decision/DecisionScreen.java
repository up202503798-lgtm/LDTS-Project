package ldts.hungergames.model.decision;

import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;

public class DecisionScreen {
    private final GameEvent event;
    private int selectedOption = 0;

    public DecisionScreen(GameEvent event) {
        this.event = event;
    }

    public GameEvent getEvent() {
        return event;
    }

    public void nextOption() {
        if (selectedOption < event.options().size() - 1) {
            selectedOption++;
        }
    }

    public void prevOption() {
        if (selectedOption > 0) {
            selectedOption--;
        }
    }

    public boolean isSelected(int index) {
        return index == selectedOption;
    }

    public Option getSelectedOption() {
        return event.options().get(selectedOption);
    }
}