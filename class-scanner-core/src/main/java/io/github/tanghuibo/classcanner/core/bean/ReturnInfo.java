package io.github.tanghuibo.classcanner.core.bean;

import java.util.List;

/**
 * 返回类型
 * @author tanghuibo
 * @date 19-11-1下午11:46
 */
public class ReturnInfo {

    /**
     * 返回值类型
     */
    private String type;

    /**
     * 注解信息
     */
    private List<AnnotationInfo> annotationInfoList;

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
