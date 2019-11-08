package io.github.tanghuibo.classinfo.canner.core.bean;

/**
 * javaDoc返回信息
 * @author tanghuibo
 * @date 19-11-7下午11:57
 */
public class JavaDocReturnInfo {

    /**
     * 类型
     */
    private String type;

    /**
     * 注释
     */
    private String commentText;

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
