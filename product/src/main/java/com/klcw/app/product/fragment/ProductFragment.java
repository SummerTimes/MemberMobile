package com.klcw.app.product.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billy.cc.core.component.CC;
import com.klcw.app.product.R;
import com.klcw.app.product.constant.ProductConstant;

/**
 * @author kk
 * @datetime: 2019/08/07
 * @desc: 我的
 */
public class ProductFragment extends Fragment {

    private View rootView;

    private String mParam;

    public static ProductFragment newInstance(String param) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ProductConstant.KRY_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ProductConstant.KRY_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.pt_main_fragment, container, false);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        initView();
        return rootView;
    }

    private void initView() {
        rootView.findViewById(R.id.tv_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CC.obtainBuilder("productComponent")
                        .setActionName("productActivity")
                        .build()
                        .callAsync();
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
