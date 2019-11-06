package io.github.tanghuibo.classcanner.core.util;

import com.sun.javadoc.Tag;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tanghuibo
 * @date 19-11-7上午12:13
 */
public class JavaDocTagUtils {

    /**
     * 获取注释
     * @param tags
     * @return
     */
    public static Map<String, String> getTagMap(Tag[] tags) {
       return Arrays.stream(tags).collect(Collectors.toMap(
                tag -> tag.name().substring(1),
                Tag::text,
                (a, b) -> a + '\n' + b
        ));
    }
}
