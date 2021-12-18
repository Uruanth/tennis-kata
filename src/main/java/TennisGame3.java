
public class TennisGame3 implements TennisGame {

    private int p2;
    private int p1;
    private String p1N;
    private String p2N;
    private String[] p = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }

    public String getScore() {
        if (puntajes()) return tablero();

        return isDeuce();

    }

    private String tablero() {

        return (isWin(p1, p2)) ? p[p1] + "-All" : p[p1] + "-" + p[p2];
    }

    private String isDeuce() {
       String  s = p1 > p2 ? p1N : p2N;
       return (isWin(p1, p2)) ?  "Deuce" : isAdvantageOrWin(s);

    }

    private String isAdvantageOrWin(String s) {
        return isWin((p1 - p2) * (p1 - p2), 1) ? "Advantage " + s : "Win for " + s;
    }

    private boolean isWin(int i, int i2) {
        return i == i2;
    }

    private boolean puntajes() {
        return puntajesMenoresQueCuatro() && !(isWin(p1 + p2, 6));
    }

    private boolean puntajesMenoresQueCuatro() {
        return p1 < 4 && p2 < 4;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(p1N))
            this.p1 += 1;
        else
            this.p2 += 1;

    }

}
