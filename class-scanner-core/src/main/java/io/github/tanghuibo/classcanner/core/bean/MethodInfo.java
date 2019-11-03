package io.github.tanghuibo.classcanner.core.bean;

import java.util.List;

/**
 * 方法信息
 * @author tanghuibo
 * @date 19-11-1下午11:40
 */
public class MethodInfo {

    /**
     * 方法名称
     */
    private String name;

    /**
     * 参数信息
     */
    private List<ArgumentInfo> argumentInfoList;

    /**
     * 返回类型
     */
    private ReturnInfo returnInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArgumentInfo> getArgumentInfoList() {
        return argumentInfoList;
    }

    public void setArgumentInfoList(List<ArgumentInfo> argumentInfoList) {
        this.argumentInfoList = argumentInfoList;
    }

    public ReturnInfo getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(ReturnInfo returnInfo) {
        this.returnInfo = returnInfo;
    }
}
