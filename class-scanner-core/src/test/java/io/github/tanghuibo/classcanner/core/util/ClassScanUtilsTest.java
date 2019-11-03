package io.github.tanghuibo.classcanner.core.util;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午2:22
 */
public class ClassScanUtilsTest {

    final static String CLASS_PATH = "target/classes";

    @Test
    public void scanTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils();

        List<ClassInfo> list = classScanUtils.scan(CLASS_PATH);
        System.out.println(JSON.toJSONString(list));

    }

    @Test
    public void filterTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils();
        classScanUtils.addFilter(item -> "io.github.tanghuibo.classcanner.core.bean.ArgumentInfo".equals(item.getType()));
        List<ClassInfo> list = classScanUtils.scan(CLASS_PATH);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void scanListTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils();
        List<ClassInfo> list = classScanUtils.scan(Arrays.asList(CLASS_PATH));
        System.out.println(JSON.toJSONString(list));
    }
}
