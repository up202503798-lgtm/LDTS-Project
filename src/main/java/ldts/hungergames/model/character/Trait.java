package ldts.hungergames.model.character;

public enum Trait {
    STRONG("Forte", "Mais vida máxima."),
    FAST("Rápido", "Move-se mais depressa na arena."),
    STEALTHY("Furtivo", "É menos notado pelos concorrentes."),
    STRATEGIST("Estratega", "Começa com mais suprimentos."),
    HUNTER("Caçador", "Encontra mais suprimentos ao procurar recursos."),
    HEALER("Curandeiro", "Recupera vida aos poucos.");

    private final String label;
    private final String description;

    Trait(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() { return label; }
    public String getDescription() { return description; }
}