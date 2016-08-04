package com.eaglesakura.android;

import com.eaglesakura.junit.SupportAssertion;
import com.eaglesakura.util.LogUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import android.app.Application;
import android.content.Context;

import java.io.File;

@RunWith(RobolectricTestRunner.class)
// @Config(constants = BuildConfig.class, packageName = BuildConfig.APPLICATION_ID, sdk = 21)
public class AndroidSupportTestCase<AppClass extends Application> extends SupportAssertion {

    protected final String LOG_TAG = getClass().getSimpleName();

    /**
     * app context
     */
    protected AppClass mApplication;

    private void initializeLogger() {
        ShadowLog.stream = System.out;
        LogUtil.setLogger(new LogUtil.RobolectricLogger());
    }

    public Context getContext() {
        return mApplication;
    }

    public AppClass getApplication() {
        return mApplication;
    }

    @Before
    public void onSetup() {
        mApplication = (AppClass) RuntimeEnvironment.application;
        initializeLogger();
    }

    @After
    public void onShutdown() {

    }

    /**
     * /src/test/assets配下のリソースを取得する
     */
    public File getTestAsset(String path) {
        return new File("src/test/assets/" + path).getAbsoluteFile();
    }

}
