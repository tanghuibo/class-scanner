package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import io.github.tanghuibo.classcanner.core.bean.MethodInfo;
import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MethodInfoUtils;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * class文件工具
 * @author tanghuibo
 * @date 19-11-1下午11:48
 */
public class ClassInfoUtils {

    private Predicate<MethodInfo> afterPredicate = item -> true;
    private Predicate<CtMethod> beforePredicate = item -> true;

    /**
     * 获取类信息
     * @param ctClass ctClass
     * @return 类信息
     */
    public  ClassInfo getClassInfo(CtClass ctClass) {
        ClassInfo classInfo = new ClassInfo();
        ClassFile classFile = ctClass.getClassFile();
        //设置type
        classInfo.setType(classFile.getName());
        classInfo.setAnnotationInfoList(getAnnotationInfoList(classFile));
        classInfo.setMethodInfoList(getMethodInfoList(ctClass));
        return classInfo;
    }

    private  List<MethodInfo> getMethodInfoList(CtClass classFile) {
        return Arrays.stream(classFile.getDeclaredMethods()).filter(beforePredicate::test)
                .map(MethodInfoUtils::getMethodInfo).filter(afterPredicate::test).collect(Collectors.toList());
    }

    private  List<AnnotationInfo> getAnnotationInfoList(ClassFile classFile) {
        List<AttributeInfo> attributes = classFile.getAttributes();
        return getAnnotationInfoList(attributes);
    }

    private  List<AnnotationInfo> getAnnotationInfoList(List<AttributeInfo> attributes) {
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

    public void addAfterFilter(Predicate<MethodInfo> predicate) {
        this.afterPredicate = this.afterPredicate.and(predicate);
    }

    public void addBeforeFilter(Predicate<CtMethod> predicate) {
        this.beforePredicate = this.beforePredicate.and(predicate);
    }


}
