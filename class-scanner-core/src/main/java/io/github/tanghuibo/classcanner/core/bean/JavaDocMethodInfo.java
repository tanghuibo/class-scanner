package io.github.tanghuibo.classcanner.core.bean;

import java.util.List;
import java.util.Map;

/**
 * JavaDoc方法信息
 * @author tanghuibo
 * @date 19-11-6下午11:54
 */
public class JavaDocMethodInfo {
    /**
     * 名称
     */
    private String name;

    /**
     * 注释
     */
    private Map<String, String> tagMap;

    /**
     * 文本注释
     */
    private String commentText;

    /**
     * 参数列表
     */
    private List<JavaDocArgumentInfo> argumentInfoList;

    /**
     * 返回值信息
     */
    private JavaDocReturnInfo returnInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<JavaDocArgumentInfo> getArgumentInfoList() {
        return argumentInfoList;
    }

    public void setArgumentInfoList(List<JavaDocArgumentInfo> argumentInfoList) {
        this.argumentInfoList = argumentInfoList;
    }

    public JavaDocReturnInfo getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(JavaDocReturnInfo returnInfo) {
        this.returnInfo = returnInfo;
    }
}
