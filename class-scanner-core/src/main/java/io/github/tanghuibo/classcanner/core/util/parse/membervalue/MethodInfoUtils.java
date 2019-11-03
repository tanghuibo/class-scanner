package io.github.tanghuibo.classcanner.core.util.parse.membervalue;


import io.github.tanghuibo.classcanner.core.bean.ArgumentInfo;
import io.github.tanghuibo.classcanner.core.bean.MethodInfo;
import io.github.tanghuibo.classcanner.core.bean.ReturnInfo;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.LocalVariableAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanghuibo
 * @date 19-11-3下午2:33
 */
public class MethodInfoUtils {
    public static MethodInfo getMethodInfo(CtMethod ctMethod) {
        MethodInfo result = new MethodInfo();
        result.setName(ctMethod.getName());
        try {
            result.setArgumentInfoList(getArgumentInfoList(ctMethod));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            result.setReturnInfo(getReturnInfo(ctMethod));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static ReturnInfo getReturnInfo(CtMethod cm) throws NotFoundException {

        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setType(cm.getReturnType().getName());
        return returnInfo;
    }





    private static List<ArgumentInfo> getArgumentInfoList(CtMethod cm) throws NotFoundException {
        List<ArgumentInfo> resultList = new ArrayList<>();
        javassist.bytecode.MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if(codeAttribute == null) {
            return null;
        }
        CtClass[] parameterTypes = cm.getParameterTypes();
        int length = parameterTypes.length;
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr != null) {
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < length; i++) {
                ArgumentInfo argumentInfo = new ArgumentInfo();
                argumentInfo.setType(parameterTypes[i].getName());
                argumentInfo.setName(attr.variableName(i + pos));
                resultList.add(argumentInfo);
            }
        }
        return resultList;
    }
}
