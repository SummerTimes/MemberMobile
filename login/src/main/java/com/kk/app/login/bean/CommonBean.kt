package com.kk.app.login.bean

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class CommonBean {
    /**
     * children : []
     * courseId : 13
     * id : 408
     * name : 鸿洋
     * order : 190000
     * parentChapterId : 407
     * userControlSetTop : false
     * visible : 1
     */
    private val courseId = 0
    private val id = 0
    private val name: String? = null
    private val order = 0
    private val parentChapterId = 0
    private val userControlSetTop = false
    private val visible = 0
    private val children: List<*>? = null
    override fun toString(): String {
        return "CommonBean{" +
                "courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", userControlSetTop=" + userControlSetTop +
                ", visible=" + visible +
                ", children=" + children +
                '}'
    }
}