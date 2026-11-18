package ldts.hungergames.model.event;

import java.util.List;

public final class GameEvents {
    private GameEvents() {
    }

    public static GameEvent firstDecision() {
        return new GameEvent(
                "O início dos Jogos",
                "O gongo soa e os outros concorrentes correm em todas as direções. O que fazes?",
                List.of(
                        new Option("Fugir e esconder-se", Risk.SAFE,
                                "Fugiste para longe da chacina e escondeste-te na floresta."),
                        new Option("Ficar a observar o que os outros fazem", Risk.RISKY,
                                "Ficaste na arena inicial a observar, mas o perigo está por perto."),
                        new Option("Ir para o meio, onde estão as armas", Risk.DEADLY,
                                "Foste para onde a chacina acontece. A arena não perdoa.")));
    }
}