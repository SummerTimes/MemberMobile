package cn.com.bailian.bailianmobile.libs.recyclerview.ui.kotlin

/**
 * 作者：杨松
 * 日期：2018/7/23 19:05
 */
abstract class KotlinFloor<T> {


    var data: T? = null

    abstract fun viewType(): Int

    private lateinit var holder: KotlinHolder

    fun onBindHolder(holder: KotlinHolder) {
        onMyBind(holder)
    }

    abstract fun onMyBind(holder: KotlinHolder)

}