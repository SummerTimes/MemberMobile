package com.kk.app.lib.recyclerview.combine;

import android.annotation.SuppressLint;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataLoader;
import com.kk.app.lib.recyclerview.floormanager.AbstractFloorCombine;
import com.kk.app.lib.recyclerview.floormanager.ILoadMoreUI;
import com.kk.app.lib.recyclerview.floormanager.IUI;
import com.kk.app.lib.recyclerview.loader.RxPreLoaderHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 作者：杨松
 * 日期：2018/6/28 17:15
 * <p>
 * 用来刷新促销角标的Combine抽象
 */

public abstract class PromotionCombine<MainFloorData, PromotionData> extends AbstractFloorCombine implements ILoadMore {


    int page = 1;

    public PromotionCombine(int key) {
        super(key);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onUIReady(final IUI ui, boolean alreadyInsert) {
        if (alreadyInsert) {
            return;
        }
        Observable<MainFloorData> objectObservable = RxPreLoaderHelper.mainFloorListen(getKey(), mainFloorDataLoaderKey(), this);
        objectObservable.flatMap(new Function<MainFloorData, ObservableSource<PromotionData>>() {
            @Override
            public ObservableSource<PromotionData> apply(MainFloorData mainFloorData) throws Exception {
                page = 1;
                refreshMainFloor(mainFloorData);
                info2Insert(ui);
                return promotionLoad(mainFloorData);
            }
        }).subscribe(new Consumer<PromotionData>() {
            @Override
            public void accept(PromotionData promotionData) throws Exception {

                refreshCornerMark(promotionData);
            }
        });
    }

    /**
     * 刷新促销角标回调
     *
     * @param promotionData
     */
    protected abstract void refreshCornerMark(PromotionData promotionData);

    private ObservableSource<PromotionData> promotionLoad(MainFloorData mainFloorData) {
        GroupedDataLoader<PromotionData> groupedDataLoader = createPromotionDataLoader(mainFloorData);
        int promotionKey = PreLoader.preLoad(groupedDataLoader);
        return RxPreLoaderHelper.listenOnce(promotionKey, groupedDataLoader.keyInGroup());
    }

    protected abstract GroupedDataLoader<PromotionData> createPromotionDataLoader(MainFloorData mainFloorData);

    /**
     * 刷新主楼层回调
     *
     * @param mainFloorData
     */
    protected abstract void refreshMainFloor(MainFloorData mainFloorData);

    protected abstract String mainFloorDataLoaderKey();

    /**
     * loadMore回调
     *
     * @param mainFloorData
     */
    protected abstract void onLoadMoreDataBack(MainFloorData mainFloorData);


    protected abstract GroupedDataLoader<MainFloorData> loadMoreDataLoader(int page);


    protected abstract boolean hasMore(int currentPage);

    @SuppressLint("CheckResult")
    @Override
    public void onLoadMore(final IUI iui) {
        if (!hasMore(page)) {
            stopLoadMore(iui);
            return;
        }

        GroupedDataLoader<MainFloorData> mainFloorDataGroupedDataLoader = loadMoreDataLoader(page);
        if (mainFloorDataGroupedDataLoader == null) {
            return;
        }
        int preLoad = PreLoader.preLoad(mainFloorDataGroupedDataLoader);
        Observable<MainFloorData> loadMoreMain = RxPreLoaderHelper.listenOnce(preLoad, mainFloorDataGroupedDataLoader.keyInGroup());


        loadMoreMain.flatMap(new Function<MainFloorData, ObservableSource<PromotionData>>() {
            @Override
            public ObservableSource<PromotionData> apply(MainFloorData mainFloorData) throws Exception {
                if (hasMore(page)) {
                    page++;
                }
                onLoadMoreDataBack(mainFloorData);
                info2Insert(iui);
                stopLoadMore(iui);
                return promotionLoad(mainFloorData);
            }
        }).subscribe(new Consumer<PromotionData>() {
            @Override
            public void accept(PromotionData promotionData) throws Exception {
                refreshCornerMark(promotionData);
            }
        });

    }

    protected void stopLoadMore(IUI iui) {
        if (iui instanceof ILoadMoreUI) {
            ((ILoadMoreUI) iui).stopLoadMore(true);
        }
    }


}
