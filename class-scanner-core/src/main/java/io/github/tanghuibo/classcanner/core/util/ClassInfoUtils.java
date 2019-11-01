package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class文件工具
 * @author tanghuibo
 * @date 19-11-1下午11:48
 */
public class ClassInfoUtils {

    /**
     * 获取类信息
     * @param ctClass ctClass
     * @return 类信息
     */
    public static ClassInfo getClassInfo(CtClass ctClass) {
        ClassInfo classInfo = new ClassInfo();
        ClassFile classFile = ctClass.getClassFile();
        //设置type
        classInfo.setType(classFile.getName());
        classInfo.setAnnotationInfoList(getAnnotationInfoList(classFile));
        return classInfo;
    }

    private static List<AnnotationInfo> getAnnotationInfoList(ClassFile classFile) {
        List<AttributeInfo> attributes = classFile.getAttributes();
        return getAnnotationInfoList(attributes);
    }

    private static List<AnnotationInfo> getAnnotationInfoList(List<AttributeInfo> attributes) {
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


}
