package com.example.zhangshuai.activity

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.dsl.animSet
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_dsl_test2.*

class DslTest2Activity:AppCompatActivity(){

    private val objectAnim by lazy {
        animSet {
            objectAnim {
                target = tv
                translationX = floatArrayOf(0f, 200f)
                scaleX = floatArrayOf(1f, 1.5f)
                alpha = floatArrayOf(1f, 0.5f)
            }
            duration = 300L
            interpolator = AccelerateInterpolator()
        }
    }

    private val togetherAnim by lazy {
        animSet {
            anim {
                values = floatArrayOf(1.0f, 1.4f)
                action = { value -> tv.scaleX = (value as Float) }
            } with anim {
                values = floatArrayOf(0f, -200f)
                action = { value -> tv.translationY = (value as Float) }
            } with anim {
                values = floatArrayOf(1.0f, 0.5f)
                action = { value -> tv.alpha = (value as Float) }
            }
            duration = 200L
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private val sequenceAnim by lazy {
        animSet {
            anim {
                values = floatArrayOf(1.0f, 1.5f)
                action = { value -> tv.scaleX = (value as Float) }
                onStart = { Toast.makeText(this@DslTest2Activity, "end", Toast.LENGTH_SHORT).show() }
            } before anim {
                values = floatArrayOf(1.5f, 1.0f)
                action = { value -> tv.scaleX = (value as Float) }
            } before anim {
                values = floatArrayOf(1.0f, 1.5f)
                action = { value -> tv.scaleX = (value as Float) }
            }
            duration = 400L
            interpolator = AccelerateInterpolator()
        }
    }

    private val complexAnim by lazy {
        animSet {
            anim {
                values = floatArrayOf(0f, -100f)
                delay = 150L
                action = { value -> tv.translationX = (value as Float) }
            } before anim {
                values = floatArrayOf(1.0f, 2f)
                action = { value -> tv.scaleY = (value as Float) }
            } with objectAnim {
                target = tv
                scaleX = floatArrayOf(1f, 1.5f)
                rotationX = floatArrayOf(0f,360f)
                alpha = floatArrayOf(1f, 0.5f,1f)
            }
            duration = 500L
            delay = 100L
            interpolator = LinearInterpolator()
            onStart = { Toast.makeText(this@DslTest2Activity, "start", Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dsl_test2)

        /**
         * case:ObjectAnim and reverse it
         */
        btnObject.setOnClickListener { objectAnim.start() }
        btnObjectReverse.setOnClickListener { objectAnim.reverse() }

        /**
         * case:play animations together by DSL and reverse it
         */
        btnFree.setOnClickListener { togetherAnim.start() }
        btnFreeReverse.setOnClickListener { togetherAnim.reverse() }

        /**
         * case:play animations Sequence by DSL and reverse it
         */
        btnSequence.setOnClickListener { sequenceAnim.start() }
        btnSequenceReverse.setOnClickListener { sequenceAnim.reverse() }

        /**
         * case:complex animations
         */
        btnComplex.setOnClickListener { complexAnim.start() }
        btnComplexReverse.setOnClickListener { complexAnim.reverse() }

    }
}
