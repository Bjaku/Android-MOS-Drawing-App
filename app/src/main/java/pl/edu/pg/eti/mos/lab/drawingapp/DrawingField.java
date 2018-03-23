package pl.edu.pg.eti.mos.lab.drawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kuba on 3/18/2018.
 */

public class DrawingField extends View {
    private Coordinates cordsOfTouch;           //Actual point of touching the screen
    private List<Coordinates> historyOfCords;   //All points created during touching the screen
    private Paint paintSettings;

    //This constructor is being used when programmatically creating view
    public DrawingField(Context context){
        super(context);
        init();
    }

    //This constructor is being used when creating view through XML
    public DrawingField(Context context, AttributeSet attributeset){
        super(context, attributeset);
        init();
    }

    public DrawingField(Context context, AttributeSet attributeset, int defStyleAttributes){
        super(context, attributeset, defStyleAttributes);
        init();
    }

    public DrawingField(Context context, AttributeSet attributeset, int defStyleAttributes, int defStyleRes){
        super(context, attributeset, defStyleAttributes);
        init();
    }

    private void init(){
        cordsOfTouch = new Coordinates();
        historyOfCords = new ArrayList<>();
        this.setDrawingCacheEnabled(true);
        paintSettings = new Paint();
        paintSettings.setStrokeWidth(5);
        paintSettings.setARGB(255, 0, 255, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        cordsOfTouch = new Coordinates(event.getX(), event.getY());
        historyOfCords.add(cordsOfTouch);   //O(n), because of ArrayList
        Log.d("DrawingField", "onTouchEvent: " + cordsOfTouch.getX() + " " + cordsOfTouch.getY());
        invalidate();
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        int sizeWithoutLastPoint = historyOfCords.size() - 1;
        if(historyOfCords.size() >= 1) {
            canvas.drawCircle((float)historyOfCords.get(0).getX(), (float)historyOfCords.get(0).getY(), 9, paintSettings);
        }
        for (int i = 0; i < sizeWithoutLastPoint; ++i) {    //theta(n), because sizeWithoutLastPoint is a variable
            canvas.drawLine((float)historyOfCords.get(i).getX(), (float)historyOfCords.get(i).getY(),
                                (float)historyOfCords.get(i + 1).getX(), (float)historyOfCords.get(i + 1).getY(), paintSettings);
        }
    }

    public Bitmap getBitmap() {
        Bitmap result = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        draw(canvas);
        canvas.drawBitmap(result, 0, 0, paintSettings);
        return result;
    }
}
