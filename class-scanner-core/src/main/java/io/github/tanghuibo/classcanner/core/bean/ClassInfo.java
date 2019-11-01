package io.github.tanghuibo.classcanner.core.bean;


import java.util.List;

/**
 * 类信息
 * @author tanghuibo
 * @date 19-11-1下午11:38
 */
public class ClassInfo {

    /**
     * 类名
     */
    private String type;

    /**
     * 注解
     */
    private List<AnnotationInfo> annotationInfoList;

    /**
     * 方法信息
     */
    private List<MethodInfo> methodInfoList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnnotationInfo> getAnnotationInfoList() {
        return annotationInfoList;
    }

    public void setAnnotationInfoList(List<AnnotationInfo> annotationInfoList) {
        this.annotationInfoList = annotationInfoList;
    }

    public List<MethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(List<MethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
    }
}
