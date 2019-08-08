package com.kk.app.lib.recyclerview.kln

import com.kk.app.lib.recyclerview.R
import kotlinx.android.synthetic.main.floor_title_text.*

/**
 * 作者：杨松
 * 日期：2018/7/23 19:31
 */
class TestFloor : KotlinFloor<String>() {

    override fun onMyBind(holder: KotlinHolder) {
        holder.tv.text = data
    }

    override fun viewType(): Int {
        return R.layout.floor_title_text
    }

}