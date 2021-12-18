import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        score = validarPuntaje(score);
        return score;
    }

    private String validarPuntaje(String score) {
        if (jugadorGanando(m_score1, m_score2)) {
            score = getNombrePuntaje();
        } else if (mayorACuatro()) {
            int minusResult = m_score1 - m_score2;
            score = getJugadorGanando(minusResult);
        } else {
            score = marcador (score);
        }
        return score;
    }

    private boolean mayorACuatro() {
        return ganador(m_score1, 4) || ganador(m_score2, 4);
    }

    private String marcador(String score) {
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (jugadorGanando(i, 1)) tempScore = m_score1;
            else {
                score += "-";
                tempScore = m_score2;
            }
            score = puntajeBasico(score, tempScore);
        }
        return score;
    }

    private String puntajeBasico(String score, int tempScore) {
        Map<Integer, String> nameScoreMap = new HashMap<>();
        nameScoreMap.put(0, "Love");
        nameScoreMap.put(1, "Fifteen");
        nameScoreMap.put(2, "Thirty");
        nameScoreMap.put(3, "Forty");

        return score + nameScoreMap.get(tempScore);

    }

    private String getJugadorGanando(int minusResult) {
        String score;
        if (jugadorGanando(minusResult, 1)) score = "Advantage "+player1Name;
        else if (jugadorGanando(minusResult, -1)) score = "Advantage "+player2Name;
        else if (ganador(minusResult, 2)) score = "Win for "+player1Name;
        else score = "Win for "+player2Name;
        return score;
    }

    private boolean ganador(int minusResult, int i) {
        return minusResult >= i;
    }

    private boolean jugadorGanando(int minusResult, int i) {
        return minusResult == i;
    }

    private String getNombrePuntaje() {
        switch (m_score1) {
            case 0:
                return  "Love-All";
            case 1:
                return  "Fifteen-All";
            case 2:
                return  "Thirty-All";
            default:
                return  "Deuce";

        }
    }
}
