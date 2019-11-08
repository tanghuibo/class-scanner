package io.github.tanghuibo.classcanner.javadoc.core.bean;

/**
 * JavaDoc参数信息
 * @author tanghuibo
 * @date 19-11-6下午11:55
 */
public class JavaDocArgumentInfo {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 注释
     */
    private String commentText;

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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
