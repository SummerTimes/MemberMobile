package com.kk.app.mobile.web.register;

import android.support.annotation.NonNull;

import com.kk.app.mobile.web.NavigationFunction;

import java.util.List;

import com.kk.app.web.function.register.IFunction;
import com.kk.app.web.function.register.IFunctionRegisterManager;

/**
 * @author kk
 * @datetime 2019-08-06
 * @desc
 */
public class FunctionRegisterManager implements IFunctionRegisterManager {

    @Override
    public void onBind(@NonNull List<IFunction> functions) {
        addFunction(functions,new NavigationFunction());
    }

    private void addFunction(@NonNull List<IFunction> functions, IFunction iFunction) {
        functions.add(iFunction);
    }
}
