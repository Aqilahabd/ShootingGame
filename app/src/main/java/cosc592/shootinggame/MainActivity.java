package cosc592.shootinggame;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Timer;

public class MainActivity extends Activity {

    private GameView gameView;
    private GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameView =new GameView(this);
        setContentView(gameView);

        Timer timer = new Timer();
        MoveTimerTask task=new MoveTimerTask( gameView);
        timer.schedule(task, 1000, 20);

        TouchHandler temp=new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);
        gestureDetector.setOnDoubleTapListener(temp);


    }
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        return true;
    }
    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onDoubleTap(MotionEvent event) {
            gameView.shot();
            return true;
        }
    }
}
