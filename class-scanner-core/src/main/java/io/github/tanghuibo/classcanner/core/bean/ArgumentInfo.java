package io.github.tanghuibo.classcanner.core.bean;

import java.util.List;

/**
 * 方法参数信息
 * @author tanghuibo
 * @date 19-11-1下午11:43
 */
public class ArgumentInfo {
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 注解
     */
    private List<AnnotationInfo> annotationInfoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
