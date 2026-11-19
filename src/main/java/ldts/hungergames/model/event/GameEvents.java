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

    public static GameEvent forestAlliance() {
        return new GameEvent(
                "A aliança",
                "Passa o primeiro dia e está tudo a correr bem, até que chega outro concorrente "
                        + "e te encontra. Ele propõe uma aliança.",
                List.of(
                        new Option("Fazer a aliança", Risk.RISKY,
                                "O teu aliado traiu-te enquanto dormias. Não podes confiar em ninguém."),
                        new Option("Não fazer a aliança, mas também não ficar inimigos", Risk.SAFE,
                                "Recusaste sem criar inimigos e o concorrente foi-se embora."),
                        new Option("Não confiar nele e ameaçá-lo", Risk.DEADLY,
                                "O concorrente fugiu, juntou-se a outros e voltou para se vingar.")));
    }

    public static GameEvent forestResources() {
        return new GameEvent(
                "A procura de recursos",
                "Passou mais algum tempo e os suprimentos estão a acabar. "
                        + "É preciso procurar comida e água.",
                List.of(
                        new Option("Procurar recursos perto do esconderijo", Risk.SAFE,
                                "Encontraste alguns suprimentos, mas em pouca quantidade."),
                        new Option("Ir mais longe na floresta à procura de recursos", Risk.RISKY,
                                "Foste longe demais e encontraste outro concorrente."),
                        new Option("Não procurar recursos e continuar escondido", Risk.DEADLY,
                                "Ficaste sem suprimentos e perdeste as forças. Não sobreviveste.")));
    }
}