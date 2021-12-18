import java.util.HashMap;
import java.util.Map;

public class TennisGame2 implements TennisGame {
    public int P1point = 0;
    public int P2point = 0;

    public String P1res = "";
    public String P2res = "";
    private String player1Name;
    private String player2Name;

    private Map<Integer, String> nameScoreMap = new HashMap<>();


    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        nameScoreMap.put(0, "Love");
        nameScoreMap.put(1, "Fifteen");
        nameScoreMap.put(2, "Thirty");
        nameScoreMap.put(3, "Forty");
        nameScoreMap.put(4, "Deuce");

    }

    public String getScore() {
        String score = "";
        score = tablero(score);

        score = advantagePlayer(score);

        score = winPlayer(score);
        return score;
    }

    private String winPlayer(String score) {

        if (ganador(P1point, P2point)) {
            score = "Win for "+player1Name;
        }
        if (ganador(P2point, P1point)) {
            score = "Win for "+player2Name;
        }
        return score;
    }

    private boolean ganador(int p1, int p2) {
        return puntajeJugadores(p1, p2) && (p1 - p2) >= 2;
    }

    private boolean puntajeJugadores(int p1, int p2) {
        return p1 >= 4 && p2 >= 0;
    }

    private String advantagePlayer(String score) {
        if (lider(P1point, P2point))
            score = "Advantage "+player1Name;

        if (lider(P2point, P1point))
            score = "Advantage "+player2Name;

        return score;
    }

    private boolean lider(int p1, int p2) {
        return p1 > p2 && p2 >= 3;
    }

    private String tablero(String score) {
        score = empate(score);


        score = p1ComparadoConP2(score);


        score = p2Ganando(score, P2point, P1point);

        if (puntajeMenorACuatro(P1point > P2point, P1point)) {
            resultados();

             score = puntaje();
        }
        score = tableroGanador(score, P2point, P1point);
        return score;
    }

    private String p2Ganando(String score, int p2point, int p1point) {
        if (p2point > 0 && p1point == 0) {

            resultados();
            score = puntaje();
        }
        return score;
    }

    private String empate(String score) {
        if (puntajeMenorACuatro(P1point == P2point, P1point)) {
            score = nameScoreMap.get(P1point);
            score += "-All";
        }
        return score;
    }

    private String tableroGanador(String score, int p2point, int p1point) {
        if (puntajeMenorACuatro(p2point > p1point, p2point)) {

            resultados();

            score = puntaje();
        }
        return score;
    }

    private String p1ComparadoConP2(String score) {
        if (P1point == P2point && P1point >= 3)
            score = nameScoreMap.get(4);

        score = p1Ganando(score, P1point, P2point);
        return score;
    }

    private String p1Ganando(String score, int p1point, int p2point) {
        if (p1point > 0 && p2point == 0) {

            resultados();
            score = puntaje();
        }
        return score;
    }

    private boolean puntajeMenorACuatro(boolean b, int p1point) {
        return b && p1point < 4;
    }

    private String puntaje() {
        String score;
        score = P1res + "-" + P2res;
        return score;
    }

    private void resultados() {
        P1res = nameScoreMap.get(P1point);
        P2res = nameScoreMap.get(P2point);
    }

    public void SetP1Score(int number) {

        for (int i = 0; i < number; i++) {
            P1Score();
        }

    }

    public void SetP2Score(int number) {

        for (int i = 0; i < number; i++) {
            P2Score();
        }

    }

    public void P1Score() {
        P1point++;
    }

    public void P2Score() {
        P2point++;
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            P1Score();
        else
            P2Score();
    }
}