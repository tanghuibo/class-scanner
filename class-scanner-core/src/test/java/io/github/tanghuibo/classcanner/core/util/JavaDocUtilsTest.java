package io.github.tanghuibo.classcanner.core.util;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classcanner.core.bean.JavaDocInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-7上午12:24
 */
public class JavaDocUtilsTest {

    private String SOURCE_PATH = ".."+ File.separator
            + "class-scanner-core"+ File.separator
            + "src"+ File.separator
            + "main" + File.separator
            + "java" + File.separator;

    @Test
    public void scan() {
        List<JavaDocInfo> javaDocInfoList = JavaDocUtils.scan(SOURCE_PATH);
        System.out.println(JSON.toJSONString(javaDocInfoList));
        Assert.assertNotNull(javaDocInfoList);
        Assert.assertNotEquals(javaDocInfoList.size(), 0);
    }
}