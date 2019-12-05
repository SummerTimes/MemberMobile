package com.kk.app.mobile.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kk.app.mobile.R

/**
 * 测试Fragment
 *
 * @author kk
 */
class TestFragment : Fragment() {
    private var mParam: String? = null
    private var mView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam = arguments!!.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.app_test_fragment, container, false)
        initView()
        return mView
    }

    private fun initView() {
        val textView = mView!!.findViewById<TextView>(R.id.tv_info)
        textView.text = mParam
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        private const val ARG_PARAM = "param"
        fun newInstance(param: String?): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            args.putString(ARG_PARAM, param)
            fragment.arguments = args
            return fragment
        }
    }
}