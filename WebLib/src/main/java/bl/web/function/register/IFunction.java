package bl.web.function.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.bailian.weblib.bljsbridge.BridgeWebView;

/**
 * 作者：杨松
 * 日期：2017/5/23 09:58
 */

public interface IFunction {

    void registerFunction(BridgeWebView webView, Context context);

    void onActivityAttach(Activity activity);

    void onResume(Activity activity);

    void onDestroy(Activity activity);

    void onReceive(String msg);

    void onPause(Activity activity);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

}
