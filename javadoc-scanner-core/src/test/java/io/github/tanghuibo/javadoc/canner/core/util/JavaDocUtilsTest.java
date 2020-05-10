package io.github.tanghuibo.javadoc.canner.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.tanghuibo.classcanner.javadoc.core.bean.JavaDocInfo;
import io.github.tanghuibo.classcanner.javadoc.core.util.JavaDocUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-7上午12:24
 */
public class JavaDocUtilsTest {

    private static final String BASE_CLASS = ".." + File.separator;

    private final String SOURCE_PATH = BASE_CLASS
            + "javadoc-scanner-core"+ File.separator
            + "src"+ File.separator
            + "main" + File.separator
            + "java" + File.separator;



    @Test
    public void scan() throws FileNotFoundException {
        List<JavaDocInfo> javaDocInfoList = JavaDocUtils.scan(SOURCE_PATH);
        Assert.assertNotNull(javaDocInfoList);
        Assert.assertNotEquals(javaDocInfoList.size(), 0);
        OutputStream outputStream = new FileOutputStream(BASE_CLASS + "javaDoc.json");
        PrintStream printStream = new PrintStream(outputStream);
        printStream.print(JSON.toJSONString(javaDocInfoList, SerializerFeature.PrettyFormat));
        System.out.println(JSON.toJSONString(javaDocInfoList));
    }
}