package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.JavaDocInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-7上午12:24
 */
public class JavaDocUtilsTest {

    private String SOURCE_PATH = "../class-scanner-core/src/main/java";

    @Test
    public void scan() {
        List<JavaDocInfo> javaDocInfoList = JavaDocUtils.scan(SOURCE_PATH);
        Assert.assertNotNull(javaDocInfoList);
        Assert.assertNotEquals(javaDocInfoList.size(), 0);
    }
}