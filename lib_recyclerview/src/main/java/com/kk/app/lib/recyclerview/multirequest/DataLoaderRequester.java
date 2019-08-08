package com.kk.app.lib.recyclerview.multirequest;

import android.util.Log;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.DataListener;
import com.billy.android.preloader.interfaces.DataLoader;

/**
 * 作者：杨松
 * 日期：2018/6/4 13:59
 */

public class DataLoaderRequester<In, Out> extends NetRequester<In, Out> {


    private DataLoader<Out> dataLoader;

    public DataLoaderRequester(String name) {
        super(name);
    }

    public void setDataLoader(DataLoader<Out> dataLoader) {
        this.dataLoader = dataLoader;
    }


    public DataLoaderRequester<In, Out> doRequest() {
        loadDataLoader();
        return this;
    }

    public DataLoaderRequester(String name, DataLoader<Out> dataLoader) {
        super(name);
        this.dataLoader = dataLoader;
    }

    @Override
    protected void visitNet(Request<In> inRequest, String param) {
        if (this.dataLoader == null) {
            Log.e("DataLoaderRequester", "请绑定好dataLoader再请求");
            return;
        }
        loadDataLoader();
    }

    private void loadDataLoader() {
        final int preLoad = PreLoader.preLoad(this.dataLoader);
        PreLoader.listenData(preLoad, new DataListener<Out>() {
            @Override
            public void onDataArrived(Out out) {
                setResponse(null, out, out != null);
                info();
                PreLoader.removeListener(preLoad, this);
            }
        });
    }
}
