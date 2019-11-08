package io.github.tanghuibo.classinfo.canner.core.util;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classinfo.canner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.ArgumentInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.ClassInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.MethodInfo;
import io.github.tanghuibo.classinfo.canner.core.util.testdata.TestAnnotationClass;
import io.github.tanghuibo.classinfo.canner.core.util.testdata.TestMethodAnnotation;
import io.github.tanghuibo.classinfo.canner.core.util.testdata.TestParamAnnotation;
import io.github.tanghuibo.classinfo.canner.core.util.testdata.TestTypeAnnotation;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午2:22
 */
public class ClassScanUtilsTest {

    final static String CLASS_PATH = "target" + File.separator + "classes";

    final static String TEST_CLASS_PATH = "target" + File.separator + "test-classes";

    @Test
    public void scanTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils(new ClassInfoUtils());

        List<ClassInfo> list = classScanUtils.scan(CLASS_PATH);
        System.out.println(JSON.toJSONString(list));

    }

    @Test
    public void filterTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils(new ClassInfoUtils());
        classScanUtils.addFilter(item -> ArgumentInfo.class.getCanonicalName().equals(item.getType()));
        List<ClassInfo> list = classScanUtils.scan(CLASS_PATH);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void scanListTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils(new ClassInfoUtils());
        List<ClassInfo> list = classScanUtils.scan(Arrays.asList(CLASS_PATH));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void annotationTest() {
        ClassScanUtils classScanUtils = new ClassScanUtils(new ClassInfoUtils());
        List<ClassInfo> list = classScanUtils.scan(Arrays.asList(TEST_CLASS_PATH));

        ClassInfo classInfo = list.stream().filter(item ->
                TestAnnotationClass.class.getCanonicalName().equals(item.getType())).findFirst().orElse(null);

        assert classInfo != null;
        AnnotationInfo typeAnnotationInfo
                = classInfo.getAnnotationInfoList().stream().filter(item -> TestTypeAnnotation.class.getCanonicalName().equals(item.getType()))
                .findFirst().orElse(null);

        assert "testClass".equals(typeAnnotationInfo.getParamMap().get("value"));

        List<MethodInfo> methodInfoList = classInfo.getMethodInfoList();
        assert  methodInfoList != null;
        MethodInfo targetMethodInfo
                = methodInfoList.stream().filter(methodInfo -> "test".equals(methodInfo.getName())).findFirst().orElse(null);

        AnnotationInfo methodAnnotationInfo
                = targetMethodInfo.getAnnotationInfoList().stream().filter(item -> TestMethodAnnotation.class.getCanonicalName().equals(item.getType()))
                .findFirst().orElse(null);
        assert "testMethod".equals(methodAnnotationInfo.getParamMap().get("value"));

        ArgumentInfo argumentInfo1 = targetMethodInfo.getArgumentInfoList().get(1);
        AnnotationInfo paramAnnotationInfo1
                = argumentInfo1.getAnnotationInfoList().stream().filter(item -> TestParamAnnotation.class.getCanonicalName().equals(item.getType()))
                .findFirst().orElse(null);
        assert "arg1".equals(paramAnnotationInfo1.getParamMap().get("value"));
        ArgumentInfo argumentInfo3 = targetMethodInfo.getArgumentInfoList().get(3);
        AnnotationInfo paramAnnotationInfo3
                = argumentInfo3.getAnnotationInfoList().stream().filter(item -> TestParamAnnotation.class.getCanonicalName().equals(item.getType()))
                .findFirst().orElse(null);
        assert "arg3".equals(paramAnnotationInfo3.getParamMap().get("value"));
        System.out.println(JSON.toJSONString(list));
    }
}
