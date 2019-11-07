package io.github.tanghuibo.classcanner.core.util;

import com.sun.javadoc.MethodDoc;
import io.github.tanghuibo.classcanner.core.bean.JavaDocMethodInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JavaDoc方法工具
 * @author tanghuibo
 * @date 19-11-7上午12:15
 */
public class JavaDocMethodUtils {
    /**
     * 获取方法信息(批量)
     * @param methods 方法Doc列表
     * @return 方法信息列表
     */
    public static List<JavaDocMethodInfo> getMethodList(MethodDoc[] methods) {
        return Arrays.stream(methods).map(JavaDocMethodUtils::getMethod).collect(Collectors.toList());
    }

    /**
     * 获取方法信息
     * @param methodDoc 方法Doc
     * @return 方法信息
     */
    public static JavaDocMethodInfo getMethod(MethodDoc methodDoc) {
        JavaDocMethodInfo methodInfo = new JavaDocMethodInfo();
        methodInfo.setName(methodDoc.name());
        methodInfo.setCommentText(methodDoc.commentText());

        methodInfo.setArgumentInfoList(JavaDocArgumentUtils.getArgumentInfoList(methodDoc.parameters(),
                JavaDocTagUtils.getParamTagMap(methodDoc.paramTags())));
        Map<String, String> tagMap = JavaDocTagUtils.getTagMap(methodDoc.tags());
        methodInfo.setTagMap(tagMap);
        methodInfo.setReturnInfo(JavaDocArgumentUtils.getReturnInfo(methodDoc, tagMap));
        return methodInfo;
    }
}
