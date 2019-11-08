package io.github.tanghuibo.classcanner.javadoc.core.util;

import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Tag;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JavaDoc注释工具
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
       return Arrays.stream(tags).filter(tag -> !"@param".equals(tag.name())).collect(Collectors.toMap(
                tag -> tag.name().substring(1),
                Tag::text,
                (a, b) -> a + '\n' + b
        ));
    }

    /**
     * 获取并移除元素
     * @param tagMap
     * @param name
     * @return
     */
    public static String getAndRemoveTag(Map<String, String> tagMap, String name) {
        String value = tagMap.get(name);
        if(value != null) {
            tagMap.remove(name);
        }
        return value;
    }

    /**
     * 获取参数列表
     * @param tags
     * @return
     */
    public static Map<String, String> getParamTagMap(ParamTag[] tags) {
        return Arrays.stream(tags).collect(Collectors.toMap(
                ParamTag::parameterName,
                ParamTag::parameterComment,
                (a, b) -> a + '\n' + b
        ));
    }
}
