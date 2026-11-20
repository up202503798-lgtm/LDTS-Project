package ldts.hungergames.model.forest;

public class ForestProgress {
    private boolean allied;
    private int supplies;

    public boolean isAllied() {
        return allied;
    }

    public int getSupplies() {
        return supplies;
    }

    public void makeAlliance() {
        allied = true;
    }

    public void addSupplies(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Supplies cannot be negative");
        }
        supplies += amount;
    }
}