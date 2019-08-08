package com.kk.app.mobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.app.mobile.R;


/**
 * 测试Fragment
 *
 * @author kk
 */
public class TestFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String mParam;
    private View mView;

    public static TestFragment newInstance(String param) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.app_test_fragment, container, false);
        initView();
        return mView;
    }

    private void initView() {
        TextView textView = mView.findViewById(R.id.tv_info);
        textView.setText(mParam);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
