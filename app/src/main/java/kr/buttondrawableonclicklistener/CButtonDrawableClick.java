package kr.buttondrawableonclicklistener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by an-yeong-gug on 2016. 10. 25..
 */

public class CButtonDrawableClick extends AppCompatButton {
    private Drawable drawableRight=null;
    private Drawable drawableLeft=null;
    private Drawable drawableTop=null;
    private Drawable drawableBottom=null;

    int actionX, actionY;

    private DrawableClickListener clickListener;

    public CButtonDrawableClick(Context context) {

        super(context);

    }
    private boolean isAClick(float startX, float endX, float startY, float endY) {
        int CLICK_ACTION_THRESHHOLD=7;

        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        if (differenceX > CLICK_ACTION_THRESHHOLD || differenceY > CLICK_ACTION_THRESHHOLD) {
            return false;
        }
        return true;
    }
    public CButtonDrawableClick(Context context, AttributeSet attrs) {
        super(context, attrs);

        // this Contructure required when you are using this view in xml
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
                                     Drawable right, Drawable bottom) {

        if (left != null) {
            drawableLeft = left;
        }
        if (right != null) {
            drawableRight = right;
        }
        if (top != null) {
            drawableTop = top;
        }
        if (bottom != null) {
            drawableBottom = bottom;
        }

        super.setCompoundDrawables(left, top, right, bottom);


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkBottomBound()) {
                    clicktype=1;


                }

                if (checkTopBound()) {
                    clicktype=0;


                }


                if (checkLeftBound()) {
                    clicktype=2;


                }


                /**If drawble bounds contains the x and y points then move ahead.*/
                if (checkRightBound()) {
                    clicktype=3;


                }


                if (CheckAllDrawable()) {
                    clicktype=4;


                }

            if(clickListener!=null) {

                if (clicktype == 0) {

                    clickListener.onClick(DrawableClickListener.DrawablePosition.TOP);
                } else if (clicktype == 1) {
                    clickListener.onClick(DrawableClickListener.DrawablePosition.BOTTOM);
                } else if (clicktype == 2) {
                    clickListener.onClick(DrawableClickListener.DrawablePosition.LEFT);
                } else if (clicktype == 3) {
                    clickListener.onClick(DrawableClickListener.DrawablePosition.RIGHT);
                } else if (clicktype == 4) {
                    clickListener.onClick(DrawableClickListener.DrawablePosition.OTHER);
                }
            }




            }

        });

    }



    public boolean checkRightBound() {
        Log.e("log","checkRightBound");
        if(drawableRight==null){return false;}
        Rect bounds;
        bounds = null;
        bounds = drawableRight.getBounds();
        bounds.set(bounds.left , bounds.top , bounds.right , bounds.bottom );

        int x, y;
        int extraTapArea = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20, getContext().getResources().getDisplayMetrics());


        /**
         * IF USER CLICKS JUST OUT SIDE THE RECTANGLE OF THE DRAWABLE
         * THAN ADD X AND SUBTRACT THE Y WITH SOME VALUE SO THAT AFTER
         * CALCULATING X AND Y CO-ORDINATE LIES INTO THE DRAWBABLE
         * BOUND. - this process help to increase the tappable area of
         * the rectangle.
         */
        x = (int) (actionX + extraTapArea);
        y = (int) (actionY - extraTapArea);

        /**Since this is right drawable subtract the value of x from the width
         * of view. so that width - tappedarea will result in x co-ordinate in drawable bound.
         */
        x = getWidth() - x;

                 /*x can be negative if user taps at x co-ordinate just near the width.
                 * e.g views width = 300 and user taps 290. Then as per previous calculation
                 * 290 + 13 = 303. So subtract X from getWidth() will result in negative value.
                 * So to avoid this add the value previous added when x goes negative.
                 */

        if (x <= 0) {
            x += extraTapArea;
        }

                 /* If result after calculating for extra tappable area is negative.
                 * assign the original value so that after subtracting
                 * extratapping area value doesn't go into negative value.
                 */

        if (y <= 0)
            y = actionY;

        /**If drawble bounds contains the x and y points then move ahead.*/

        Log.e("log",bounds.left+":"+bounds.right+":"+bounds.top+":"+bounds.bottom+"::::"+actionX+":"+actionY);
        if (bounds.contains(x, y) && clickListener != null) {

            return true;
        } else {

            return false;
        }


    }




    public boolean onclickflag=false;

    public boolean checkBottomBound() {

        if (drawableBottom != null
                && drawableBottom.getBounds().contains(actionX, actionY)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkTopBound() {
        if (drawableTop != null
                && drawableTop.getBounds().contains(actionX, actionY)) {
            return true;

        } else {
            return false;
        }

    }

    public boolean checkLeftBound() {
        if(drawableLeft==null){return false;}
        Rect bounds;
        bounds = null;
        bounds = drawableLeft.getBounds();
        bounds.set(bounds.left + getPaddingLeft(), bounds.top + getPaddingTop(), bounds.right + getPaddingRight(), bounds.bottom + getPaddingBottom());


        int x, y;
        int extraTapArea = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20, getContext().getResources().getDisplayMetrics());

        x = actionX;
        y = actionY;

        if (!bounds.contains(actionX, actionY)) {
            /** Gives the +20 area for tapping. */
            x = (int) (actionX - extraTapArea);
            y = (int) (actionY - extraTapArea);

            if (x <= 0)
                x = actionX;
            if (y <= 0)
                y = actionY;

            /** Creates square from the smallest value */
            if (x < y) {
                y = x;
            }
        }

        if (bounds.contains(x, y) && clickListener != null) {

            return true;

        } else {

            return false;
        }

    }

    public boolean CheckAllDrawable() {


        if (!checkLeftBound() && !checkRightBound() && !checkBottomBound() && !checkTopBound()) {

            return true;
        } else {

            return false;
        }

    }

    private float startX;
    private float startY;

    private int clicktype=10;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        clicktype=10;
        onclickflag=false;

        switch(event.getAction()){



            case MotionEvent.ACTION_DOWN:
                actionX = (int) event.getX();
                actionY = (int) event.getY();


                    break;





        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        drawableRight = null;
        drawableBottom = null;
        drawableLeft = null;
        drawableTop = null;
        super.finalize();
    }


    public void setDrawableClickListener(DrawableClickListener listener) {
        this.clickListener = listener;
    }

}
