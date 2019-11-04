package io.github.tanghuibo.classcanner.core.util.parse.membervalue;


import io.github.tanghuibo.classcanner.core.bean.AnnotationInfo;
import io.github.tanghuibo.classcanner.core.bean.ArgumentInfo;
import io.github.tanghuibo.classcanner.core.bean.MethodInfo;
import io.github.tanghuibo.classcanner.core.bean.ReturnInfo;
import io.github.tanghuibo.classcanner.core.util.AnnotationUtils;
import javassist.CtMethod;
import javassist.bytecode.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanghuibo
 * @date 19-11-3下午2:33
 */
public class MethodInfoUtils {
    public static MethodInfo getMethodInfo(CtMethod ctMethod) {
        MethodInfo result = new MethodInfo();
        result.setName(ctMethod.getName());
        try {
            result.setAnnotationInfoList(getAnnotationInfoList(ctMethod));
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            result.setArgumentInfoList(getArgumentInfoList(ctMethod));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            result.setReturnInfo(getReturnInfo(ctMethod));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<AnnotationInfo> getAnnotationInfoList(CtMethod ctMethod) {
        List<AttributeInfo> attributes = ctMethod.getMethodInfo().getAttributes();
        if(attributes == null) {
            return new ArrayList<>(0);
        }
        return AnnotationUtils.getAnnotationInfoList(attributes);
    }

    private static ReturnInfo getReturnInfo(CtMethod cm) throws BadBytecode {

        ReturnInfo returnInfo = new ReturnInfo();
        SignatureAttribute.MethodSignature methodSignature = getMethodSignature(cm.getMethodInfo());
        if(methodSignature != null) {
            if(methodSignature.getReturnType() != null) {
                returnInfo.setType(methodSignature.getReturnType().jvmTypeName());
            }

        }
        return returnInfo;
    }





    private static List<ArgumentInfo> getArgumentInfoList(CtMethod cm) throws BadBytecode {
        List<ArgumentInfo> resultList = new ArrayList<>();
        javassist.bytecode.MethodInfo methodInfo = cm.getMethodInfo();
        List<List<AnnotationInfo>> paramAnnotationInfoList
                = AnnotationUtils.getParamAnnotationInfoList(methodInfo.getAttributes());
        int paramAnnotationInfoListSize = 0;
        if(paramAnnotationInfoList != null) {
            paramAnnotationInfoListSize = paramAnnotationInfoList.size();
        }

        SignatureAttribute.MethodSignature methodSignature = getMethodSignature(methodInfo);
        SignatureAttribute.Type[] parameterTypes = methodSignature.getParameterTypes();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        List<String> params = getParams(codeAttribute);
        int length = parameterTypes.length;
        for (int i = 0; i < length; i++) {
            ArgumentInfo argumentInfo = new ArgumentInfo();
            argumentInfo.setType(parameterTypes[i].jvmTypeName());
            argumentInfo.setName(i < params.size() ? params.get(i) : getNewName(resultList));
            if(i < paramAnnotationInfoListSize) {
                argumentInfo.setAnnotationInfoList(paramAnnotationInfoList.get(i));
            }
            resultList.add(argumentInfo);
        }

        return resultList;
    }

    private static List<String> getParams(CodeAttribute codeAttribute) {
        if(codeAttribute != null) {
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            return getParamNames(attr);
        }
        return new ArrayList<>(0);
    }

    private static String getNewName(List<ArgumentInfo> resultList) {
        List<String> nameList = resultList.stream().map(ArgumentInfo::getName).collect(Collectors.toList());
        int i = 0;
        while (nameList.contains("arg" + i)) {
            i ++;
        }
        return "arg" + i;
    }

    private static List<String> getParamNames(LocalVariableAttribute attr) {
        int length = attr.tableLength();
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            resultList.add(attr.variableName(i));
        }
        int thisIndex = resultList.indexOf("this");
        if(thisIndex < 0) {
            return resultList;
        }
        return resultList.stream().skip(thisIndex + 1).collect(Collectors.toList());
    }

    private static SignatureAttribute.MethodSignature getMethodSignature(javassist.bytecode.MethodInfo methodInfo) throws BadBytecode {

        return SignatureAttribute.toMethodSignature(methodInfo.getDescriptor());
    }
}
