package io.github.tanghuibo.classcanner.core.util;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午7:27
 */
public class MavenUtilsTest {

    static final String BASE_CLASS = "../";
    @Test
    public void scanClassPathsTest() {
        List<String> classPaths = MavenUtils.scanClassPaths(BASE_CLASS);
        System.out.println(JSON.toJSONString(classPaths));
        assert classPaths.size() > 0;
    }

    @Test
    public void scan() {
        ClassScanUtils classScanUtils = new ClassScanUtils();
        List<ClassInfo> scan = classScanUtils.scan(MavenUtils.scanClassPaths(BASE_CLASS));
        System.out.println(JSON.toJSONString(scan));
        assert scan.size() > 0;
    }
}
