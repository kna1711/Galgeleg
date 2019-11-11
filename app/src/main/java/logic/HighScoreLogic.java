package logic;

public class HighScoreLogic {
    private static HighScoreLogic instance;

    public static HighScoreLogic getInstance() {
        return (instance == null) ? instance = new HighScoreLogic() : instance;
    }
}
