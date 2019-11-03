package io.github.tanghuibo.classcanner.core.util;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import javassist.NotFoundException;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午2:22
 */
public class ClassScanUtilsTest {

    final static String CLASS_PATH = "target/classes";

    @Test
    public void scanTest() throws NotFoundException {
        List<ClassInfo> list = ClassScanUtils.scan(CLASS_PATH);

        ClassInfo classInfo = list.stream().filter(item -> ClassScanUtils.class.getName().endsWith(item.getType()))
                .findFirst().orElse(null);
        System.out.println(JSON.toJSONString(list));
        assert classInfo != null;

    }
}
