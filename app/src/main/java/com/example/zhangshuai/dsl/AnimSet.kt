package com.example.zhangshuai.dsl

import android.animation.Animator
import android.animation.AnimatorSet

class AnimSet : Anim() {
    override var animator: Animator = AnimatorSet()
    private val animatorSet
        get() = animator as AnimatorSet

    private val anims by lazy {
        mutableListOf<Anim>()
    }

    /**
     * has played reversely
     */
    private var isReverse = false

    fun anim(animCreation: ValueAnim.() -> Unit): Anim = ValueAnim().apply(animCreation).also { it.addListener() }.also { anims.add(it) }

    fun objectAnim(action: ObjectAnim.() -> Unit): Anim = ObjectAnim().apply { action }.also { it.setPropertyValueHolder() }.also { it.addListener() }.also { anims.add(it) }

    /**
     * start the [AnimSet]
     */
    fun start() {
        if (animatorSet.isRunning) return
        anims.takeIf { isReverse }?.forEach { anim -> anim.reverse() }
        if (anims.size == 1) animatorSet.play(anims.first().animator)
        animatorSet.start()
        isReverse = false
    }

    /**
     * reverse the animation
     */
    override fun reverse() {
        if (animatorSet.isRunning) return
        anims.takeIf { !isReverse }?.forEach { anim -> anim.reverse() }
        animatorSet.start()
        isReverse = true
    }

    /**
     * cancel the [AnimatorSet]
     */
    fun cancel() {
        animatorSet.cancel()
    }

    /**
     * if you want to play animations one after another, use [before] to link several [Anim],
     * like the following:
     *
     * animSet {
     *      anim {
     *          value = floatArrayOf(1.0f, 1.4f)
     *          action = { value -> tv.scaleX = (value as Float) }
     *      } before anim {
     *          values = floatArrayOf(0f, -200f)
     *          action = { value -> btn.translationY = (value as Float) }
     *      }
     *      duration = 200L
     * }
     *
     */
    infix fun Anim.before(anim: Anim): Anim {
        animatorSet.play(animator).before(anim.animator).let { this.builder = it }
        return anim
    }

    /**
     * if you want to play animations at the same time, use [with] to link several [Anim],
     * like the following:
     *
     * animSet {
     *      play {
     *          value = floatArrayOf(1.0f, 1.4f)
     *          action = { value -> tv.scaleX = (value as Float) }
     *      } with anim {
     *          values = floatArrayOf(0f, -200f)
     *          action = { value -> btn.translationY = (value as Float) }
     *      }
     *      duration = 200L
     * }
     *
     * if there are both [with] and [before] in one invocation chain, [with] has higher priority,
     * for example: `a before b with c` means b and c will play at the same time and a plays before them
     *
     */
    infix fun Anim.with(anim: Anim): Anim {
        if (builder == null) builder = animatorSet.play(animator).with(anim.animator)
        else builder?.with(anim.animator)
        return anim
    }
}

/**
 * build a set of animation with a much shorter and readable code by DSL
 */
fun animSet(creation: AnimSet.() -> Unit) = AnimSet().apply { creation() }.also { it.addListener() }



