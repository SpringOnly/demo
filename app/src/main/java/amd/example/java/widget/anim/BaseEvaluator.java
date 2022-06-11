package amd.example.java.widget.anim;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @author Created by on LvJP 2022/6/11
 */
public class BaseEvaluator implements TypeEvaluator<PointF> {

    private final PointF p1;
    private final PointF p2;

    public BaseEvaluator(PointF p1, PointF p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public PointF evaluate(float fraction, PointF p0, PointF p3) { //p0-startValue    p3-EndValue
        PointF pointf = new PointF();

        // 三阶贝塞尔曲线公式 p0*(1-t)^3 + 3p1*t*(1-t)^2 + 3p2*t^2*(1-t) + p3^3
        pointf.x = p0.x * (1 - fraction) * (1 - fraction) * (1 - fraction)
                + 3 * p1.x * fraction * (1 - fraction) * (1 - fraction)
                + 3 * p2.x * fraction * fraction * (1 - fraction)
                + p3.x * fraction * fraction * fraction;
        pointf.y = p0.y * (1 - fraction) * (1 - fraction) * (1 - fraction)
                + 3 * p1.y * fraction * (1 - fraction) * (1 - fraction)
                + 3 * p2.y * fraction * fraction * (1 - fraction)
                + p3.y * fraction * fraction * fraction;
        return pointf;
    }
}
