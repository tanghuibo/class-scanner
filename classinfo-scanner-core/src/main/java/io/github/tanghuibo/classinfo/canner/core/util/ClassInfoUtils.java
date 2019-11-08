package io.github.tanghuibo.classinfo.canner.core.util;

import io.github.tanghuibo.classinfo.canner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.ClassInfo;
import io.github.tanghuibo.classinfo.canner.core.bean.MethodInfo;
import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.MethodInfoUtils;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * class文件工具
 * @author tanghuibo
 * @date 19-11-1下午11:48
 */
public class ClassInfoUtils {

    /**
     * 后置过滤器
     */
    private Predicate<MethodInfo> afterPredicate = item -> true;

    /**
     * 前置过滤器
     */
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

    /**
     * 添加后置过滤器
     * @param predicate
     */
    public void addAfterFilter(Predicate<MethodInfo> predicate) {
        this.afterPredicate = this.afterPredicate.and(predicate);
    }

    /**
     * 添加前置过滤器
     * @param predicate
     */
    public void addBeforeFilter(Predicate<CtMethod> predicate) {
        this.beforePredicate = this.beforePredicate.and(predicate);
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
        return AnnotationUtils.getAnnotationInfoList(attributes);
    }

}
