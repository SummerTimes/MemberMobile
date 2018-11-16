package com.klcw.app.member.web.register;

import android.support.annotation.NonNull;

import com.klcw.app.member.web.NavigationFunction;

import java.util.List;

import bl.web.function.register.IFunction;
import bl.web.function.register.IFunctionRegisterManager;

/**
 * 作者：杨松
 * 日期：2017/10/9 14:24
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
