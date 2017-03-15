package com.example.muratbuldu.getirhackathon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    Paint paint = new Paint();
    ArrayList<Rectangle> rectangleArrayList;
    ArrayList<Circle> circleArrayList;

    public DrawView(Context context, ArrayList<Rectangle> rectangleArrayList_, ArrayList<Circle> circleArrayList_) {
        super(context);
        rectangleArrayList = rectangleArrayList_;
        circleArrayList = circleArrayList_;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for ( Rectangle rectangle: rectangleArrayList){
            String hexColor = "#" + Integer.toHexString(Color.parseColor("#"+rectangle.color)).substring(2);
            paint.setColor(Color.parseColor(hexColor));
            canvas.drawRect(rectangle.xPosition,
                            rectangle.yPosition,
                            rectangle.xPosition+rectangle.width,
                            rectangle.yPosition+rectangle.height, paint);
        }
        for ( Circle circle: circleArrayList){
            String hexColor = "#" + Integer.toHexString(Color.parseColor("#"+circle.color)).substring(2);
            paint.setColor(Color.parseColor(hexColor));
            canvas.drawCircle(circle.xPosition,
                    circle.yPosition,
                    circle.radius,
                    paint);
        }

    }

    public void setRectangleArrayList(ArrayList<Rectangle> rectangleArrayList_){
        rectangleArrayList = rectangleArrayList_;
    }

    public void setCircleArrayList(ArrayList<Circle> circleArrayList_){
        circleArrayList = circleArrayList_;
    }
}
