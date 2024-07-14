package com.sdk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class DeviceInfoModule extends ReactContextBaseJavaModule {

    public DeviceInfoModule(ReactApplicationContext reactContext) {
        super(reactContext); // required by React Native
    }
    @ReactMethod
    public void getOSVersion(final Callback callback) {
        callback.invoke(null, Build.VERSION.RELEASE);
    }
    @Override
    // getName is required to define the name of the module represented in JavaScript
    public String getName() {
        return "GetDeviceInfo";
    }

    @ReactMethod
    public void getDeviceID(final Callback callback) {
        getDeviceIDHandler(callback);
    }

    @ReactMethod
    public void getDeviceIPAddress(final Callback callback) {
        getDeviceIPAddressHandler(callback);
    }

    @ReactMethod
    public void getAAID(final Callback callback) {
        getAAIDHandler(callback);
    }

    @ReactMethod
    public void getDeviceBrand(final Callback callback) {
        callback.invoke(null, Build.BRAND);
    }

    @ReactMethod
    public void getDeviceModel(final Callback callback) {
        callback.invoke(null, Build.MODEL);
    }

    @ReactMethod
    public void getScreenSize(final Callback callback) {
        WindowManager windowManager = (WindowManager) getReactApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            float density = displayMetrics.density;
            callback.invoke(null, width, height, density);
        } else {
            callback.invoke("Unable to retrieve screen size", null);
        }
    }

    private void getDeviceIDHandler(final Callback callback) {
        AsyncTask<Void, Void, Void> myAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                Context context = getReactApplicationContext();
                String android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
                callback.invoke(null, android_id);
                return null;
            }
        };
        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void getDeviceIPAddressHandler(final Callback callback) {
        AsyncTask<Void, Void, String> myAsyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(final Void... params) {
                try {
                    List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                    for (NetworkInterface intf : interfaces) {
                        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                        for (InetAddress addr : addrs) {
                            if (!addr.isLoopbackAddress()) {
                                String sAddr = addr.getHostAddress();
                                boolean isIPv4 = sAddr.indexOf(':') < 0;

                                if (isIPv4) {
                                    return sAddr;
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String ipAddress) {
                if (ipAddress != null) {
                    callback.invoke(null, ipAddress);
                } else {
                    callback.invoke("Unable to retrieve IP address", null);
                }
            }
        };
        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void getAAIDHandler(final Callback callback) {
        AsyncTask<Void, Void, Void> myAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                try {
                    Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getReactApplicationContext());
                    String aaid = adInfo.getId();
                    callback.invoke(null, aaid);
                } catch (Exception e) {
                    callback.invoke(e.toString(), null);
                }
                return null;
            }
        };
        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
