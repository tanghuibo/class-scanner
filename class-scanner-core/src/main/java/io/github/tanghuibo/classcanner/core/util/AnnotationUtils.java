package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParseUtil;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.MemberValue;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 获取类或方法注解信息
     * @param attributes
     * @return
     */
    public static List<AnnotationInfo> getAnnotationInfoList(List<AttributeInfo> attributes) {
        if(attributes == null) {
            return new ArrayList<>(0);
        }
        return attributes.stream()
                //过滤非注解信息
                .filter(attribute -> attribute instanceof AnnotationsAttribute)
                .flatMap(attribute -> {
                    AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) attribute;
                    return Arrays.stream(annotationsAttribute.getAnnotations());
                })
                .map(AnnotationUtils::toAnnotationInfo)
                .collect(Collectors.toList());
    }


    public static List<List<AnnotationInfo>> getParamAnnotationInfoList(List<AttributeInfo> attributes) {
        if(attributes == null) {
            return new ArrayList<>(0);
        }
        ParameterAnnotationsAttribute parameterAnnotationsAttribute =
                attributes.stream().filter(attribute -> attribute instanceof ParameterAnnotationsAttribute)
                        .map(attributeInfo -> (ParameterAnnotationsAttribute) attributeInfo)
                        .findFirst().orElse(null);
        if(parameterAnnotationsAttribute == null) {
            return new ArrayList(0);
        }
        return Arrays.stream(parameterAnnotationsAttribute.getAnnotations()).map(
                annotations -> Arrays.stream(annotations).map(AnnotationUtils::toAnnotationInfo).collect(Collectors.toList())
        ).collect(Collectors.toList());


    }
}
