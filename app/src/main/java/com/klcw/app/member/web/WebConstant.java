package com.klcw.app.member.web;

/**
 * Created by kk on 2017/3/8.
 */

public interface WebConstant {

    interface API {
        /**
         * {resourceId: ''}
         */
        String BL_APP_SITE_QUERY_ADD_EPLOY_API_MANAGER = "BLAPPSiteQueryAdDeployAPIManager";
        /**
         * {channelld: ''}
         */
        String BL_PROMOTI_ONQUERY_FLASHCATE_GORY_API_MANAGER = "BLPromotionQueryFlashCategoryAPIManager";

        /**
         * {channelid: '',type: '', pageNum: '', pageSize: '', parameter: '', flashCategories: ''}
         */
        String BL_PROMOTION_QUERY_FLASH_LIST_API_MANAGER = "BLPromotionQueryFlashListAPIManager";
        /**
         * {channelid: '', flashId: ''}
         */
        String BL_PROMOTION_QUERY_FLASH_GOODS_API_MANAGER = "BLPromotionQueryFlashGoodsAPIManager";
        /**
         * {actCode: '', sorCol: '',sorTye:'',pageSize:'',pageNo:'',isava:'',brandSid:''}
         */
        String BL_QUERY_BRANDD_ETAI_LSEARCHACTIVITY_API_MANAGER = "BLQueryBrandDetailSearchActivityAPIManager";
        /**
         * {channelSid: '', c: '', searchInfo:{sorCol:'', sorTye:'',pageModel:{pageNo:'',pageSize:''}}}
         */
        String BL_SEARCH_BYKEY_WORD_API_MANAGER = "BLSearchByKeyWordAPIManager";

        /**
         * {fkRankid: ''}
         */
        String BL_QUERY_PRODUCT_LIST_API_MANAGER = "BLQueryProductListAPIManager";

        /**
         * {rankType: ''}
         */
        String BL_QUERY_RANK_LIST_API_MANAGER = "BLQueryRankListAPIManager";
        /**
         * 加入到家购物车
         */
        String BL_DJ_ADDCART_API_MANAGER = "BLDJAddCartAPIManager";
        /**
         * 加入购物车
         */
        String BL_CART_ADD_CART_APIMANAGER = "BLCartAddCartAPIManager";

        /**
         * 退出登录
         */
        String LOGOUT = "BLLogoutAPIManager";


    }


    interface METHOD {


        /**
         * {goodsid:'',goodsName:'',goodsPrice:'',goodsImageUrl:'',isGiftGoods:Boolean}
         * <p>
         * h5跳native商品详情页
         */
        String BLGoodsDetail = "BLGoodsDetail" + "#" + "BLGoodsDetailViewController";
        /**
         * {searchkey:''}
         * <p>
         * h5跳native搜索列表页
         */
        String SEARCH_LIST = "BLCategory" + "#" + "BLCategoryViewController";

        String IMAGE_RES_JUMP = "BLAdvertResource" + "#" + "BLAdvertResourceController";

        String CHECK_VERSION = "NativeEnv" + "#" + "checkVersion";

        String DOWN_LOAD_NATIVEJS = "NativeEnv" + "#" + "downloadNativeJS";

        String SHARE = "BLShare" + "#" + "H5BLShareParams";

        String BURIED_POINT = "NativeEnv" + "#" + "fetchUserInfo";

        String FETCH_LOGININFO = "NativeEnv" + "#" + "fetchLoginInfo";

        String READ_CARD_CRYPTO = "RedCardCrypto" + "#" + "DecypherWithCypherText";

        String NEW_WEB = "BLDefaultWebView" + "#" + "defaultWebViewController";
        /**
         * 跳转登录
         */
        String BL_LOGIN_JUMP = "BLLogin" + "#" + "PresentLoginViewController";
        /**
         * 跳到其他页面
         */
        String BL_NAVIGATE_OTHER_PAGE = "BLPageManager" + "#" + "NavigateWithStringParams";

        /**
         * 电子卡
         */
        String CHANGE_STATE = "BLElectronCard" + "#" + "exchangeState";
        String BL_Contact = "Contact#selectItem";//调用原生通讯录
        String BL_PaymentPage = "BLChargeAndPayment#chargeAndPaymentViewController";//充值
        String BL_PaymentRecordAction = "BLChargeAndPayment#setType";//充值记录
        String BL_Cashier = "BLCashier#cashierNavigationController";//收银台
        String BL_Scan = "BLBarScanner#presentH5BLBarScanner";//扫一扫
        String BL_Server_Url = "ExposeJsApi#getServiceCfg";//获取服务器的url信息
        String BL_PromotionList = "SearchList#IBLActivityList";//充值

        String SET_TITLE = "title" + "#" + "title";
        String BL_COMMENT = "BLMyComment#setPopDownInfo";
        String BL_GOHOME = "BLPageManager#pagemanagerNavigateToHome";

        /**
         * 谈对话框
         */
        String SHOW_ALERT_DIALOG = "AlertController" + "#" + "showAlert";


        /**
         * 回到主页
         */
        String GO_HOME = "BLPageManager" + "#" + "pagemanagerNavigateToHome";


        /**
         * 返回上一个页面
         */
        String BACK = "BLPageManager" + "#" + "pagemanagerBack";

        /**
         * 跳转到订单详情
         */
        String ORDER_DETAIL = "Order" + "#" + "OrderDetailViewController";

        /**
         * 跳转到购物车
         */
        String SHOPPING_CART = "BLShoppingCart" + "#" + "BLBLShoppingCartViewController";


    }

}
