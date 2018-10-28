package com.klcw.app.lib.recyclerview.multirequest;

import java.util.Map;

/**
 * 作者：杨松
 * 日期：2018/6/4 22:41
 */

public class Demo {


    public void test() {

        RequesterHelper helper = new RequesterHelper();

        NetRequester requester1 = createRequest1();

        NetRequester requester2 = new NetRequester<C, D>("2");

        helper.doRequest(requester1)
                .doRequest(requester2)
                .afterDoRequester(new IRequesterCreator<C, D>() {
                    @Override
                    public NetRequester<C, D> createRequest(Map in) {
                        return create3(in);
                    }
                }, "1", "2")
                .afterDoRequester(new IRequesterCreator<C, D>() {
                    @Override
                    public NetRequester<C, D> createRequest(Map in) {
                        return create3(in);
                    }
                }, "3");
    }

    private NetRequester createRequest1() {
        return new NetRequester<A, B>("3").setRequest(new Request<A>());
    }


    public NetRequester<C, D> create3(Map in) {
        in.get("1");
        in.get("2");
        return new NetRequester<C, D>("3").setRequest(new Request<C>());
    }


    public static class A {


    }

    public static class D {


    }


    public static class B {


    }

    public static class C {


    }

}
