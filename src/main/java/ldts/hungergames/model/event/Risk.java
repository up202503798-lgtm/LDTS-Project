package ldts.hungergames.model.event;

public enum Risk {
    SAFE(100), RISKY(50), DEADLY(0);

    private final int percent;

    Risk(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}