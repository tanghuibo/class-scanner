package io.github.tanghuibo.classcanner.core.util.parse.membervalue;

import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:10
 */
public interface MemberValueParse {

    /**
     * 解析
     * @param memberValue
     * @return
     */
    Object get(MemberValue memberValue);


}
