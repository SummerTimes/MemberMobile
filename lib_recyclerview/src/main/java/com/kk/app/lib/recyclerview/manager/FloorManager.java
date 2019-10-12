package com.kk.app.lib.recyclerview.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kk.app.lib.recyclerview.Floor;
import com.kk.app.lib.recyclerview.FloorViewHolderMaker;
import com.kk.app.lib.recyclerview.BaseFloorHolder;
import com.kk.app.lib.recyclerview.kln.KotlinFloor;
import com.kk.app.lib.recyclerview.kln.KotlinHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：杨松
 * 日期：2018/1/22 15:18
 * 该类负责楼层的组装
 */

public class FloorManager {

    private final RecyclerView.Adapter mAdapter;
    private List<IFloorCombine> mFloorCombines = new ArrayList<>();

    private final List<Object> mTotalFloors = new ArrayList<>();


    public FloorManager() {
        this.mAdapter = new BaseAdapter();
    }


    public List<Object> getTotalFloors() {
        return mTotalFloors;
    }

    @Deprecated
    public FloorManager addAll(ICombinesProvider iCombinesProvider, int key, AbstractPresenter abstractPresenter) {
        if (mFloorCombines.size() == 0 && iCombinesProvider != null) {
            List<IFloorCombine> combines = iCombinesProvider.createCombines(key);
            for (IFloorCombine combine : combines) {
                if (combine instanceof AbstractFloorCombine) {
                    ((AbstractFloorCombine) combine).bindPresenter(abstractPresenter);
                }
            }
            mFloorCombines.addAll(combines);
        }
        return this;
    }


    public FloorManager addAll(List<IFloorCombine> combines, int key, AbstractPresenter abstractPresenter) {
        if (combines != null && mFloorCombines.size() == 0) {
            for (IFloorCombine combine : combines) {
                if (combine instanceof AbstractFloorCombine) {
                    ((AbstractFloorCombine) combine).bindPresenter(abstractPresenter);
                }
            }
            mFloorCombines.addAll(combines);
        }
        return this;
    }


    public @NonNull
    RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void onRequestInflateUi(IUI iui) {
        for (IFloorCombine floorCombine : mFloorCombines) {
            floorCombine.onRequestInflateRecyclerView(iui);
        }
    }


    public synchronized void notifyCombineChange(final IFloorCombine iCombine) {

        try {
            int num = mFloorCombines.indexOf(iCombine);
            if (num == -1) {
                mAdapter.notifyDataSetChanged();
                return;
            }
            final int indexStart = calculateIndex(num);
            if (indexStart == -1) {
                mAdapter.notifyDataSetChanged();
                return;
            }
            List<Object> floors = iCombine.lastFloors();
            mAdapter.notifyItemRangeChanged(indexStart, indexStart + floors.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void notifyDataChanged(final IFloorCombine iCombine) {
        notifyDataChanged(iCombine, null);
    }


    public synchronized void notifyDataChanged(final IFloorCombine iCombine, final IUI iui) {//todo
        try {
            final List<Object> data = iCombine.getFloors();
            int num = mFloorCombines.indexOf(iCombine);
            if (num == -1) {
                return;
            }
            final int indexStart = calculateIndex(num);
            if (indexStart == -1) {
                return;
            }

            List<Object> floors = iCombine.lastFloors();
            if (floors != null && floors.size() > 0) {
                mTotalFloors.removeAll(floors);
                floors.clear();
            }
            mTotalFloors.addAll(indexStart, data);
            if (floors == null) {
                floors = new SerializableList<>();
            }
            floors.addAll(data);
            iCombine.setAlreadyInsert(true);

            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPositionByTag(String tag) {
        for (int i = 0, num = mTotalFloors.size(); i < num; i++) {
            Object floor = mTotalFloors.get(i);
            if (floor instanceof Floor && TextUtils.equals(tag, ((Floor) floor).TAG)) {
                return i;
            }
        }
        return -1;
    }

    public int getPositionByHeadTag(String tag) {
        for (int i = 0, num = mTotalFloors.size(); i < num; i++) {
            Object floor = mTotalFloors.get(i);
            if (floor instanceof Floor && TextUtils.equals(tag, ((Floor) floor).TAG)) {
                return i;
            }
        }
        return -1;
    }


    public int getPositionByType(int type) {
        for (int i = 0, num = mTotalFloors.size(); i < num; i++) {


            Object floor = mTotalFloors.get(i);
            if (floor instanceof Floor && type == ((Floor) floor).getViewType()) {
                return i;
            }
        }
        return -1;
    }

    public int getPositionByTypeAndIndex(int type, int index) {
        int k = 0;
        for (int i = 0, num = mTotalFloors.size(); i < num; i++) {
            Object floor = mTotalFloors.get(i);
            if (floor instanceof Floor && type == ((Floor) floor).getViewType()) {
                k++;
                if (index == k) {
                    return i;
                }
            }

        }

        return -1;
    }


    private int calculateIndex(int num) {
        int index = 0;
        for (int i = 0; i < num; i++) {
            IFloorCombine combine = mFloorCombines.get(i);
            String name = combine.getClass().getName() + i;
            List<Object> floors = combine.lastFloors();
            if (floors != null && floors.size() > 0) {
                index += combine.lastFloors().size();
            }
        }
        return index;
    }


    public void onSaveInstanceState(Bundle outState) {
    }

    public void clearCombines() {
        mFloorCombines.clear();
    }


    private class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder floorViewHolder = FloorViewHolderMaker.createFloorViewHolder(parent, viewType);
            if (floorViewHolder == null) {
                floorViewHolder = new KotlinHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
            }
            return floorViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            try {
                Object floor = mTotalFloors.get(position);
                if (floor instanceof Floor) {
                    ((BaseFloorHolder) holder).bind(floor);
                } else if (floor instanceof KotlinFloor) {
                    ((KotlinFloor) floor).onBindHolder(((KotlinHolder) holder));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public int getItemViewType(int position) {
            Object o = mTotalFloors.get(position);
            if (o instanceof KotlinFloor) {
                return ((KotlinFloor) o).viewType();
            } else if (o instanceof Floor) {
                return ((Floor) o).getViewType();
            }
            return -1;
        }

        @Override
        public int getItemCount() {
            return mTotalFloors.size();
        }
    }


}
