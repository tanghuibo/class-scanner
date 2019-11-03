package io.github.tanghuibo.classcanner.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanghuibo
 * @date 19-11-3下午7:16
 */
public class MavenUtils {

    /**
     * 扫描mvn工程下的class文件夹
     * @param baseClass
     * @return /home/thb/workspace/java_workspace/classscanner/class-scanner-core/target/classes
     */
    public static List<String> scanClassPaths(String baseClass) {
        return Arrays.stream(new File(baseClass).listFiles())

                .map(file -> {
                    try {
                        return file.getCanonicalPath() + File.separator + "target" + File.separator + "classes";
                    } catch (IOException e) {
                        e.printStackTrace();
                        return file.getAbsolutePath() + File.separator + "target" + File.separator + "classes";
                    }
                })
                .filter(filePath -> new File(filePath).isDirectory())
                .collect(Collectors.toList());
    }
}
