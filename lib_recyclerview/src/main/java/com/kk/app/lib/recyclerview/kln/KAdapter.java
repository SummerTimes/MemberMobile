package com.kk.app.lib.recyclerview.kln;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;



/**
 * 作者：杨松
 * 日期：2018/7/23 19:03
 */
public class KAdapter extends RecyclerView.Adapter<KotlinHolder> {

    private final List<Object> list;

    public KAdapter(List<Object> list) {
        this.list = list;
    }

    @Override
    public KotlinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KotlinHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }


    @Override
    public void onBindViewHolder(KotlinHolder holder, int position) {
        KotlinFloor kotlinFloor = (KotlinFloor) list.get(position);
        kotlinFloor.onBindHolder(holder);
    }


    @Override
    public int getItemViewType(int position) {
        KotlinFloor o = (KotlinFloor) list.get(position);
        return o.viewType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
