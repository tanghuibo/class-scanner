package io.github.tanghuibo.classcanner.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午7:27
 */
public class MavenUtilsTest {

    private static final String BASE_CLASS = "../";
    @Test
    public void scanClassPathsTest() {
        List<String> classPaths = MavenUtils.getClassPaths(MavenUtils.getAllModules(BASE_CLASS));
        System.out.println(JSON.toJSONString(classPaths));
        assert classPaths.size() > 0;
    }

    @Test
    public void scan() throws FileNotFoundException {
        ClassInfoUtils classInfoUtils = new ClassInfoUtils();
        classInfoUtils.addBeforeFilter(item -> !item.getName().contains("$"));
        classInfoUtils.addBeforeFilter(item -> Modifier.isPublic(item.getModifiers()));
        ClassScanUtils classScanUtils = new ClassScanUtils(classInfoUtils);
        List<ClassInfo> scan = classScanUtils.scan( MavenUtils.getClassPaths(MavenUtils.getAllModules(BASE_CLASS)));
        OutputStream outputStream = new FileOutputStream((BASE_CLASS.endsWith(File.separator) ? BASE_CLASS : BASE_CLASS + File.separator) + "javaDoc.json");
        PrintStream printStream = new PrintStream(outputStream);
        printStream.print(JSON.toJSONString(scan, SerializerFeature.PrettyFormat));
        System.out.println(JSON.toJSONString(scan));
        assert scan.size() > 0;
    }
}
