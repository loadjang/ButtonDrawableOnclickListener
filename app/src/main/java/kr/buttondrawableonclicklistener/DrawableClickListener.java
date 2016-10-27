package kr.buttondrawableonclicklistener;

/**
 * Created by an-yeong-gug on 2016. 10. 25..
 */

public interface DrawableClickListener {
    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT,OTHER };
    public void onClick(DrawablePosition target);

}
