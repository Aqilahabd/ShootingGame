package cosc592.shootinggame;

import java.util.TimerTask;

public class MoveTimerTask extends TimerTask {

    private GameView gameView;

    public MoveTimerTask(GameView gameView)
    {

        this.gameView=gameView;
    }
    public void run()
    {
        gameView.update();
        gameView.postInvalidate();
    }
}

