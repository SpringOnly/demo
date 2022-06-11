package amd.example.java.widget.anim;

import android.animation.TypeEvaluator;

/**
 * @author Created by on LvJP 2022/5/27
 * 自定义估值器
 */
public class CustomEvaluator implements TypeEvaluator<MyViewPoint> {

    @Override
    public MyViewPoint evaluate(float fraction, MyViewPoint startValue, MyViewPoint endValue) {
        float x = startValue.getX() + (fraction * (endValue.getX() - startValue.getX()));
        float y = startValue.getY() + (fraction * (endValue.getY() - startValue.getY()));

        return new MyViewPoint(x, y);
    }
}
