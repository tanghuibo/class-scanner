package io.github.tanghuibo.classcanner.core;

import com.alibaba.fastjson.JSON;
import io.github.tanghuibo.classcanner.core.util.ClassScanUtils;
import javassist.NotFoundException;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:53
 */
public class Application {

    final static String CLASS_PATH = "class-scanner-core/target/classes";
    public static void main(String[] args) throws NotFoundException {
        System.out.println(JSON.toJSONString(ClassScanUtils.scan(CLASS_PATH)));
    }
}
