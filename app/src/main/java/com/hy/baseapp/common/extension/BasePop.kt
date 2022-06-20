package com.hy.baseapp.common.extension

import android.view.animation.Animation
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.Direction
import razerdp.util.animation.ScaleConfig
import razerdp.util.animation.TranslationConfig

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/27
 *     desc  :
 *
 * </pre>
 */

/**
 * 默认从上面出现的POP动画
 */
fun getUp2BottomAnimation(duration: Int = 300): Animation = AnimationHelper.asAnimation().withTranslation(
    TranslationConfig().from(Direction.TOP).interpolator(FastOutSlowInInterpolator()).duration(300)
).toShow()

fun getUp2BottomDismissAnimation(offSetY:Int): Animation = AnimationHelper.asAnimation().withTranslation(
    TranslationConfig().fromY(offSetY).to(Direction.TOP).interpolator(FastOutSlowInInterpolator()).duration(300)
).toShow()


/**
 * 默认从底部上升POP动画
 */
fun getBottom2UpAnimation(duration: Int = 300): Animation = AnimationHelper.asAnimation().withTranslation(
    TranslationConfig().from(Direction.BOTTOM).interpolator(FastOutSlowInInterpolator()).duration(300)
).toShow()

/**
 * 从上面回到底部pop动画
 * @param offSetY 距离底部的偏移量
 */
fun getBottom2UpDismissAnimation(offSetY:Int): Animation =  AnimationHelper.asAnimation()
    .withTranslation(
        TranslationConfig().fromY(offSetY).to(Direction.BOTTOM).interpolator(
            FastOutSlowInInterpolator()
        ).duration(300)).toShow()

/**
 * 从中间放大的pop动画
 */
fun getScaleFromCenterAnimation(): Animation = AnimationHelper.asAnimation().withScale(ScaleConfig().duration(300)).toShow()
/**
 * 从中间缩小的pop动画
 */
fun getScaleFromCenterDismissAnimation(): Animation = AnimationHelper.asAnimation().withScale(
    ScaleConfig().scale(1f,0f).duration(300)).toShow()
