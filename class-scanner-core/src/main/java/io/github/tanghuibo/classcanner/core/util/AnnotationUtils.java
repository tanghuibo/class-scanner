package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParseUtil;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.MemberValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 注解工具
 * @author tanghuibo
 * @date 19-11-2上午12:00
 */
public class AnnotationUtils {
    /**
     * 获取注解信息
     * @param annotation 注解
     * @return
     */
    public static AnnotationInfo toAnnotationInfo(Annotation annotation) {
        AnnotationInfo annotationInfo = new AnnotationInfo();
        annotationInfo.setType(annotation.getTypeName());
        annotationInfo.setParamMap(getParamMap(annotation));
        return annotationInfo;
    }

    private static Map<String, Object> getParamMap(Annotation annotation) {
        Set<String> memberNames = annotation.getMemberNames();
        if(memberNames == null) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>(memberNames.size());
        memberNames.forEach(memberName -> {
            MemberValue memberValue = annotation.getMemberValue(memberName);
            if(memberName == null) {
                return;
            }
            paramMap.put(memberName, MemberValueParseUtil.parse(memberValue));
        });

        return paramMap;
    }


}
