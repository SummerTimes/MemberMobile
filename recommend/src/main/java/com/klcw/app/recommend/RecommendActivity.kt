package com.klcw.app.recommend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.klcw.app.lib.widget.BLToast

/**
 * 推荐模块
 */
class RecommendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)
        initView()
    }

    private fun initView() {

    }

    fun OnButtonClick() {
        BLToast.showToast(this, "提示")
        val intent = Intent(this, TestActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
