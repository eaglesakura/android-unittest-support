package com.eaglesakura.android;

import com.eaglesakura.util.LogUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.hamcrest.core.Is;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import android.app.Application;
import android.content.Context;

import java.io.File;

@RunWith(RobolectricGradleTestRunner.class)
// @Config(constants = BuildConfig.class, packageName = BuildConfig.APPLICATION_ID, sdk = 21)
public class AndroidSupportTestCase<AppClass extends Application> {

    protected final String LOG_TAG = getClass().getSimpleName();

    /**
     * app context
     */
    protected AppClass mApplication;

    private void initializeLogger() {
        ShadowLog.stream = System.out;
        LogUtil.setLogger(
                new LogUtil.Logger() {
                    @Override
                    public void out(int level, String tag, String msg) {

                        switch (level) {
                            case LogUtil.LOGGER_LEVEL_INFO:
                                tag = "I/" + tag;
                                break;
                            case LogUtil.LOGGER_LEVEL_ERROR:
                                tag = "E/" + tag;
                                break;
                            default:
                                tag = "D/" + tag;
                                break;
                        }

                        try {
                            StackTraceElement[] trace = new Exception().getStackTrace();
                            StackTraceElement elem = trace[Math.min(trace.length - 1, 2)];
                            System.out.println(String.format("%s | %s[%d] : %s", tag, elem.getFileName(), elem.getLineNumber(), msg));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    public static org.hamcrest.Matcher<Boolean> isTrue() {
        return Is.is(true);
    }
}
