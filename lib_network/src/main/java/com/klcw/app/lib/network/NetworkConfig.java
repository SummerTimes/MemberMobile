package com.klcw.app.lib.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.klcw.app.lib.network.util.DeviceUtil;
import com.klcw.app.lib.network.util.SharedPreferenceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


/**
 * description:网络请求配置
 * date: 2017/8/23 13:49
 *
 * @author mlp00
 */
public class NetworkConfig {
    private static final String TAG = "NetworkConfig";

    private static NetworkConfig sInstance;

    private static Context mContext;
    private static ArrayMap<String, String> openApiConfig;
    private static ArrayMap<String, String> urlConfig;
    private static Token token;
    public static String deviceNum;
    public static String memberToken = "";
    public static String phone = "";

    private static String networkEnvType = "";

    private NetworkConfig() {
        openApiConfig = new ArrayMap<>();
        urlConfig = new ArrayMap<>();
    }

    public static NetworkConfig getInstance() {
        if (sInstance == null) {
            Log.e(TAG, "NetworkConfig should setup before first call");
        }

        return sInstance;
    }

    public static synchronized void setup(Context context, String type) {
        mContext = context;
        networkEnvType = type;
        if (sInstance == null) {
            sInstance = new NetworkConfig();
        }

        getDeviceNum(context);
        sInstance.loadCSV(context, type);
        DynamicKeyManager.initDynamicKey(context);
        getToken();
    }

    private static void getDeviceNum(Context context) {
        deviceNum = SharedPreferenceUtil.getStringValueFromSP(context, NetworkSpKeys.NETWORK_SP_NAME, NetworkSpKeys.NETWORK_DEVICE_NUM);
        if (TextUtils.isEmpty(deviceNum)) {
            deviceNum = DeviceUtil.getDeviceId(context);
            SharedPreferenceUtil.setStringDataIntoSP(context, NetworkSpKeys.NETWORK_SP_NAME, NetworkSpKeys.NETWORK_DEVICE_NUM, deviceNum);
        }
    }

    private void loadCSV(Context context, String type) {
        try {
            InputStreamReader isr = new InputStreamReader(context.getAssets().open("network_config.csv"));
            BufferedReader reader = new BufferedReader(isr);
            String line;
            String configKey[] = new String[0];
            Log.e("xp", "-----type-----" + type);
            while ((line = reader.readLine()) != null) {
                Log.e("xp", "-----line-----" + line);
                String tokens[] = line.split(";", -1);
                if ("configKey".compareToIgnoreCase(tokens[0]) == 0) {
                    configKey = tokens;
                } else if ("configVal".compareToIgnoreCase(tokens[0]) == 0) {
                    if ("openApi".compareToIgnoreCase(tokens[1]) == 0 && type.compareToIgnoreCase(tokens[2]) == 0) {
                        for (int i = 3; i < tokens.length; i++) {
                            openApiConfig.put(configKey[i], tokens[i]);
                        }
                    } else {
                        if (type.compareToIgnoreCase(tokens[2]) == 0 && tokens.length > 3) {
                            urlConfig.put(tokens[1], tokens[3]);
                        }
                    }
                }
            }

            openApiConfig.put("sn", deviceNum);
            Log.e("xp", "-----openApiConfig-----" + openApiConfig.toString());
            Log.e("xp", "-----urlConfig-----" + urlConfig.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getOpenApiConfig() {
        return openApiConfig;
    }

    public static void setToken(Token token) {
        NetworkConfig.token = token;

        if (token != null) {
            String strToken = new Gson().toJson(token);
            SharedPreferenceUtil.setStringDataIntoSP(mContext, NetworkSpKeys.NETWORK_SP_NAME, NetworkSpKeys.NETWORK_TOKEN_INFO, strToken);
        }
    }

    public static Token getToken() {
        if (token == null) {
            String strToken = SharedPreferenceUtil.getStringValueFromSP(mContext, NetworkSpKeys.NETWORK_SP_NAME, NetworkSpKeys.NETWORK_TOKEN_INFO, null);
            if (!TextUtils.isEmpty(strToken)) {
                token = new Gson().fromJson(strToken, Token.class);
            }
        }
        return token;
    }

    public static String getNetworkEnvType() {
        return networkEnvType;
    }

    public static void setUrl(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            urlConfig.put(key, value);
        }
    }

    public static String getUrl(String key) {
        String result = "";
        if (urlConfig != null && urlConfig.containsKey(key)) {
            result = urlConfig.get(key);
        }

        return result;
    }

    public static String getH5Url()  {
        return getUrl(UrlType.H5);
    }

    public static String getCloudH5Url() {
        return getUrl(UrlType.CLOUD_H5);
    }


    public static String getPayUrl() {
        return getUrl(UrlType.PAY);
    }

    public static String getMpUrl() {
        return getUrl(UrlType.MP);
    }

    public static String getMpStoreUrl(String sid) {
        return getMpUrl() + "?sid=" + sid + "&source=2";
    }

    public static String getLotteryUrl() {
        return getUrl(UrlType.LOTTERY);
    }

    public static String getAppMwUrl() {
        return getUrl(UrlType.APP_MW);
    }

    public static String getOpenApiUrl() {
        return getUrl(UrlType.OPEN_API);
    }

    public static String getParkingUrl() {
        return getUrl(UrlType.PARKING);
    }
}
