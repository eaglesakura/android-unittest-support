package com.eaglesakura.android.unittest;

import com.eaglesakura.android.AndroidSupportTestCase;
import com.eaglesakura.util.LogUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, packageName = BuildConfig.APPLICATION_ID, sdk = 21)
public class UnitTestCase extends AndroidSupportTestCase {

    @Test
    public void テストが実行される() {
        LogUtil.out("UnitTest", "テスト実行");
    }
}
