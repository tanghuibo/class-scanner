package io.github.tanghuibo.classcanner.core.bean;

import java.util.Map;

/**
 * 注解信息
 * @author tanghuibo
 * @date 19-11-1下午11:38
 */
public class AnnotationInfo {

    /**
     * 注解名称
     */
    private String type;

    /**
     * 注解参数
     */
    private Map<String, Object> paramMap;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
