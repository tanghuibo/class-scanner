package io.github.tanghuibo.classcanner.core.bean;

import java.util.List;
import java.util.Map;

/**
 * 类Doc
 * @author tanghuibo
 * @date 19-11-6下午11:53
 */
public class JavaDocInfo {
    /**
     * 类名
     */
    private String type;

    /**
     * 注释
     */
    private Map<String, String> tagMap;

    /**
     * 文本注释
     */
    private String commentText;

    /**
     * 方法信息
     */
    private List<JavaDocMethodInfo> methodInfoList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public List<JavaDocMethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(List<JavaDocMethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
