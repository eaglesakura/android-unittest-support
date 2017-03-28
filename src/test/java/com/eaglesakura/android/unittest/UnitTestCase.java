package com.eaglesakura.android.unittest;

import com.eaglesakura.android.AndroidSupportTestCase;
import com.eaglesakura.log.Logger;

import org.junit.Test;

public class UnitTestCase extends AndroidSupportTestCase {

    @Test
    public void テストが実行される() {
        Logger.out(Logger.LEVEL_DEBUG, "UnitTest", "テスト実行");
    }
}
