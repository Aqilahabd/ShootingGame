package cosc592.shootinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.media.MediaPlayer;


public class GameView extends View {

    private double shapeX;
    private double shapeY;
    private double shapeSpeed;

    private double shotX;
    private double shotY;
    private double shotSpeed;
    private double shotAngle;

    private double gunX;
    private double gunY;
    private double scenegun;

    String color="#009900";

    private double radius;
    private double distanceThreshold;

    private boolean shooted;
    private boolean hit;

    private double sceneWidth;
    private double sceneHeight;
    String Scolor;

    public GameView(Context context)
    {
        super(context);


        this.sceneWidth=1820;
        this.sceneHeight=1050;
        this.Scolor=" ";
        initDraw();
    }

    public void onDraw(Canvas canvas)
    {
        double shapeX = getShapeX();
        double shapeY = getShapeY();
        double shotX=getShotX();
        double shotY=getShotY();
        double gunX=getGunX();
        double gunY= getGunY();
        double scenegun=getScenegun();
        double radius = getRadius();
        String color=getColor();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#D6EAF8"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, (float)sceneWidth, (float)sceneHeight, paint);
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float)shapeX, (float)(sceneHeight-shapeY), (float)radius, paint);
        paint.setColor(Color.parseColor("#7B241C"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float)shotX, (float)(scenegun-shotY),(float)radius, paint);

        paint.setColor(Color.parseColor("#7B241C"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawLine(0, (float)scenegun, (float)gunX,(float)(scenegun-gunY), paint);

    }


    public void setColor(String color1)
    {
        Scolor=color1;
    }
    public void update()
    {
        moveShape();
        if(shooted)
            moveShot();

        if(Clear())
            initDraw();

    }
    public void shot()
    {
        shooted=true;
    }
    public double getShapeX()
    {
        return shapeX;
    }
    public double getShapeY()
    {
        return shapeY;
    }
    public double getShotX()
    {
        return shotX;
    }
    public double getShotY()
    {
        return shotY;
    }
    public double getGunX()
    {
        return gunX;
    }
    public double getGunY()
    {
        return gunY;
    }
    public double getRadius()
    {
        return radius;
    }
    public double getScenegun()
    {
        return scenegun;
    }
    public String getColor(){return color;}

    private void moveShape()
    {
        if(!hit)
        {
            shapeY= shapeY-shapeSpeed;
            hit = decideHit();
        }
        else {
            MediaPlayer mPlayer2;

            mPlayer2= MediaPlayer.create(getContext(), R.raw.explosion);
            mPlayer2.start();
            color="#D6EAF8";
            shapeY=shapeY-shapeSpeed;

        }
    }

    private void moveShot()
    {
        shotX = shotX + shotSpeed*Math.cos(shotAngle*Math.PI/180);
        shotY = shotY+ shotSpeed*Math.sin(shotAngle*Math.PI/180);

    }
    private boolean decideHit()
    { int x=50,y=99;


        double distance = Math.sqrt((shapeX-shotX)*(shapeX-shotX)+(shapeY-shotY)*(shapeY-shotY));
        return distance>=distanceThreshold-15 && distance<=distanceThreshold+15  ;

    }

    private boolean Clear()
    {
        return (shapeX<0 || shapeY<0);
    }
    private void initDraw()
    {

        double sceneHeight=1000;
        double gunLength=200;
        double gunAngle = 10* Math.random();
        this.shapeSpeed = 5 + 5 * Math.random();
        this.shapeX = 600 + 400*Math.random();
        this.shapeY = sceneHeight-50;


        this.gunX = gunLength*Math.cos(gunAngle*Math.PI/180);
        this.gunY = gunLength*Math.sin(gunAngle*Math.PI/180);
        this.scenegun=600+450*Math.random();

        this.shotX=gunX;
        this.shotY=gunY;
        this.shotSpeed=20;
        this.shotAngle=gunAngle;

        this.shooted=false;
        this.color="#0E6655";

        this.radius=50;

        this.distanceThreshold=100;


        this.hit=false;
    }
}
