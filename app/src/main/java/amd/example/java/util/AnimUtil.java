package amd.example.java.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;


import amd.example.demo.R;
import io.reactivex.disposables.Disposable;

public class AnimUtil {

    private AnimUtil() {
    }

    public static void clearAnimator(View view) {
        Object object = view.getTag(R.id.anim_visible_tag);
        if (!(object instanceof Animator)) {
            return;
        }
        Animator animator = ((Animator) object);
        animator.cancel();
        view.setTag(R.id.anim_visible_tag, null);
    }

    public static void setTagAnimator(View view, Animator animator) {
        view.setTag(R.id.anim_visible_tag, animator);
    }

    public static boolean isPlaying(View view) {
        return view.getTag(R.id.anim_visible_tag) != null;
    }


    /**
     * X轴平移动画
     */
    public static Animator translationX(View view, float start, float end,boolean isShowView) {
        clearAnimator(view);

        view.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", start, end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTag(R.id.anim_visible_tag, null);
                if (!isShowView) {
                    view.setVisibility(View.GONE);
                }
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    /**
     * Y轴平移动画
     */
    public static Animator translationY(View view, float start, float end) {
        clearAnimator(view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", start, end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTag(R.id.anim_visible_tag, null);
                view.setVisibility(View.GONE);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }


    /**
     * 渐入动画
     */
    public static Animator in(View view) {
        clearAnimator(view);

        view.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator out(View view) {
        return out(view, View.GONE);
    }

    /**
     * 渐出动画
     */
    public static Animator out(View view, int visibility) {
        clearAnimator(view);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(1f);
                view.setVisibility(visibility);

                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator inLeft(View view, int start) {
        clearAnimator(view);

        view.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", start, 0);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator outLeft(View view, int end) {
        return outLeft(view, end, View.GONE);
    }

    public static Animator outLeft(View view, int end, int visibility) {
        clearAnimator(view);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, -end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(visibility);
                view.setTranslationX(0);

                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator outRight(View view, int end) {
        return outRight(view, end, View.GONE);
    }

    public static Animator outRight(View view, int end, int visibility) {
        clearAnimator(view);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(visibility);
                view.setTranslationX(0);

                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(350);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator inBottom(View view, int start) {
        return inBottom(view, start, 350);
    }

    public static Animator inBottom(View view, int start, long duration) {
        clearAnimator(view);

        view.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", start, 0);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(duration);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static Animator outBottom(View view, int end) {
        return outBottom(view, end, 350);
    }

    public static Animator outBottom(View view, int end, long duration) {
        clearAnimator(view);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                view.setTranslationX(0);

                view.setTag(R.id.anim_visible_tag, null);
            }
        });
        animator.setDuration(duration);
        animator.start();

        setTagAnimator(view, animator);

        return animator;
    }

    public static void cancelRotate3dAnim(View view) {
        Animation animation = view.getAnimation();
        if (animation != null) {
            view.setAnimation(null);
            animation.cancel();
        }

        Object obj = view.getTag(R.id.anim_disposable_tag);
        if (obj instanceof Disposable) {
            Disposable disposable = ((Disposable) obj);
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            view.setTag(R.id.anim_disposable_tag, null);
        }
    }

//    TODO dragon 下面都需要修改
//    @Retention(RetentionPolicy.SOURCE)
//    @IntDef({Rotate3dAnimation.ROLL_BY_X, Rotate3dAnimation.ROLL_BY_Y, Rotate3dAnimation.ROLL_BY_Z})
//    @interface rotate3dSource {
//    }

    /**
     * 循环执行旋转动画
     *
     * @param view     视图
     * @param rollType 类型
     * @param time     间隔时间，毫秒
     * @param count    次数，-1代表无限
     */
//    public static void rotate3d(View view, @rotate3dSource int rollType, int time, int count) {
//        view.setTag(R.id.anim_count_tag, count);
//
//        rotate3d(view, rollType);
//
//        Observable
//                .interval(time, time, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .takeWhile(aLong -> {
//                    if (view.getAnimation() == null) {
//                        return false;
//                    }
//                    Object obj = view.getTag(R.id.anim_count_tag);
//                    if (!(obj instanceof Integer)) {
//                        return false;
//                    }
//                    int c = ((Integer) obj);
//                    if (c == -1) {
//                        return true;
//                    }
//                    if (c > 1) {
//                        view.setTag(R.id.anim_count_tag, --c);
//                        return true;
//                    }
//                    return false;
//                })
//                .doOnSubscribe(disposable -> view.setTag(R.id.anim_disposable_tag, disposable))
//                .doFinally(() -> {
//                    view.setTag(R.id.anim_count_tag, null);
//                    cancelRotate3dAnim(view);
//                })
//                .subscribe(aLong -> {
//                    rotate3d(view, rollType);
//                });
//    }

    /**
     * 执行旋转动画
     *
     * @param view     视图
     * @param rollType 类型
     */
//    public static void rotate3d(View view, @rotate3dSource int rollType) {
//        int centerX = view.getMeasuredWidth() / 2;
//        int centerY = view.getMeasuredHeight() / 2;
//
//        Rotate3dAnimation openAnimation = new Rotate3dAnimation(rollType, 0, 90, centerX, centerY);
//        openAnimation.setDuration(500);
//        openAnimation.setFillAfter(true);
//        openAnimation.setInterpolator(new AccelerateInterpolator());
//        openAnimation.setAnimationListener(new SimpleOnAnimationListener() {
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (view.getAnimation() == null) {
//                    return;
//                }
//
//                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(rollType, 270, 360, centerX, centerY);
//                rotateAnimation.setDuration(500);
//                rotateAnimation.setFillAfter(true);
//                rotateAnimation.setInterpolator(new DecelerateInterpolator());
//                view.startAnimation(rotateAnimation);
//            }
//        });
//        view.startAnimation(openAnimation);
//    }

    /**
     * 构造旋转动画
     *
     * @param repeatCount 运行次数
     * @param duration    运行时间
     */
    public static AnimationSet rotate(int repeatCount, long duration) {
        AnimationSet animations = new AnimationSet(true);
        animations.setInterpolator(new LinearInterpolator());
        RotateAnimation rotate = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(duration);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(repeatCount);
        animations.addAnimation(rotate);
        return animations;
    }
}