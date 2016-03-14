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
public class AndroidSupportTestCase<AppClass extends Application> {

    /**
     * app context
     */
    protected AppClass mApplication;

    private void initializeLogger() {
        ShadowLog.stream = System.out;
        LogUtil.setOutput(true);
        LogUtil.setLogger(new LogUtil.Logger() {
            @Override
            public void i(String msg) {
                try {
                    StackTraceElement[] trace = new Exception().getStackTrace();
                    StackTraceElement elem = trace[Math.min(trace.length - 1, 3)];
                    System.out.println("I " + String.format("%s[%d] : %s", elem.getFileName(), elem.getLineNumber(), msg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void d(String msg) {
                try {
                    StackTraceElement[] trace = new Exception().getStackTrace();
                    StackTraceElement elem = trace[Math.min(trace.length - 1, 3)];
                    System.out.println("D " + String.format("%s[%d] : %s", elem.getFileName(), elem.getLineNumber(), msg));
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
    protected File getTestAsset(String path) {
        return new File("src/test/assets/" + path).getAbsoluteFile();
    }

    public static org.hamcrest.Matcher<Boolean> isTrue() {
        return Is.is(true);
    }
}
