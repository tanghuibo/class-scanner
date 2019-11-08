package io.github.tanghuibo.classinfo.canner.core.util;

import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import io.github.tanghuibo.classinfo.canner.core.bean.JavaDocArgumentInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.JavaDocReturnInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * javaDoc参数工具
 * @author tanghuibo
 * @date 19-11-7下午11:46
 */
public class JavaDocArgumentUtils {
    /**
     * 获取参数信息列表
     * @param parameters 参数Doc列表
     * @param tagMap 注释信息
     * @return
     */
    public static List<JavaDocArgumentInfo> getArgumentInfoList(Parameter[] parameters, Map<String, String> tagMap) {
        return Arrays.stream(parameters).map(parameter ->  JavaDocArgumentUtils.getArgumentInfo(parameter, tagMap))
                .collect(Collectors.toList());
    }

    /**
     * 获取参数信息
     * @param parameter 参数Doc
     * @param tagMap 注释信息
     * @return
     */
    public static JavaDocArgumentInfo getArgumentInfo(Parameter parameter, Map<String, String> tagMap) {
        JavaDocArgumentInfo argumentInfo = new JavaDocArgumentInfo();
        argumentInfo.setName(parameter.name());
        argumentInfo.setType(parameter.type().qualifiedTypeName());
        argumentInfo.setCommentText(tagMap.get(parameter.name()));
        return argumentInfo;
    }

    /**
     * 获取返回值信息
     * @param methodDoc 方法Doc
     * @param tagMap 注释Tag
     * @return
     */
    public static JavaDocReturnInfo getReturnInfo(MethodDoc methodDoc, Map<String, String> tagMap) {
        JavaDocReturnInfo argumentInfo = new JavaDocReturnInfo();
        argumentInfo.setCommentText(JavaDocTagUtils.getAndRemoveTag(tagMap, "return"));
        argumentInfo.setType(methodDoc.returnType().qualifiedTypeName());
        return argumentInfo;
    }
}
