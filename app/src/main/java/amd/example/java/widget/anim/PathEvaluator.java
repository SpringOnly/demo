package amd.example.java.widget.anim;

import android.animation.TypeEvaluator;

/**
 * @author Created by on LvJP 2022/5/31
 */
public class PathEvaluator implements TypeEvaluator<PathPoint> {

    /**
     * @param fraction   :执行的百分比
     * @param startValue : 起点
     * @param endValue   : 终点
     * @return
     */
    @Override
    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        float x, y;
        float oneMiunsT = 1 - fraction;
        //三阶贝塞尔曲线
        if (endValue.mOperation == PathPoint.THIRD_CURVE) {
            x = startValue.mX * oneMiunsT * oneMiunsT * oneMiunsT + 3 * endValue.mContorl0X * fraction * oneMiunsT * oneMiunsT + 3 * endValue.mContorl1X * fraction * fraction * oneMiunsT + endValue.mX * fraction * fraction * fraction;
            y = startValue.mY * oneMiunsT * oneMiunsT * oneMiunsT + 3 * endValue.mContorl0Y * fraction * oneMiunsT * oneMiunsT + 3 * endValue.mContorl1Y * fraction * fraction * oneMiunsT + endValue.mY * fraction * fraction * fraction;
            //二阶贝塞尔曲线
        } else if (endValue.mOperation == PathPoint.SECOND_CURVE) {
            x = oneMiunsT * oneMiunsT * startValue.mX + 2 * fraction * oneMiunsT * endValue.mContorl0X + fraction * fraction * endValue.mX;
            y = oneMiunsT * oneMiunsT * startValue.mY + 2 * fraction * oneMiunsT * endValue.mContorl0Y + fraction * fraction * endValue.mY;
            //直线
        } else if (endValue.mOperation == PathPoint.LINE) {
            //x起始点+t*起始点和终点的距离
            x = startValue.mX + fraction * (endValue.mX - startValue.mX);
            y = startValue.mY + fraction * (endValue.mY - startValue.mY);
        } else { //圆心移动位置
            x = endValue.mX;
            y = endValue.mY;
        }
        return PathPoint.moveTo(x, y);
    }
}