package com.example.stanislavk.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LasVegas on 06.07.2017.
 */

public class BoxDrawingView extends View {

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);
        for (Box box : mBoxen) {
            float left = Math.min(box.getmOrigin().x, box.getmCurrent().x);
            float right = Math.max(box.getmOrigin().x, box.getmCurrent().x); //здесь была ошибка
            float top = Math.min(box.getmOrigin().y, box.getmCurrent().y);
            float bottom = Math.max(box.getmOrigin().y, box.getmCurrent().y);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //Пользователь прикоснулся к экрану
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE: //Пользователь перемещает палец по экрану
                if (mCurrentBox != null) {
                    mCurrentBox.setmCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP: //Пользователь отводит палец от экрана
                mCurrentBox = null; // здесб была ошибка
                break;
            case MotionEvent.ACTION_CANCEL: //Родительское представление перехватило событие касания
                mCurrentBox = null;// здесб была ошибка
                break;
        }
        return true;
    }
}
