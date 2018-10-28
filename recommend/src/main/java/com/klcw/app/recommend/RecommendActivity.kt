package com.klcw.app.recommend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.klcw.app.lib.widget.BLToast

import com.klcw.app.util.StringUtils

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
        StringUtils.print("初始化推荐模");
    }

    fun OnButtonClick(view: View) {
        BLToast.showToast(this,"提示");
        val intent = Intent(this, TestActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent);
    }
}
