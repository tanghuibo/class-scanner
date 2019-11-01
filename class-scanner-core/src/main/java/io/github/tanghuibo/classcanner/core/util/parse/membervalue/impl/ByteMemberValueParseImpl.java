package io.github.tanghuibo.classcanner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:17
 */
public class ByteMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        ByteMemberValue byteMemberValue = (ByteMemberValue) memberValue;
        return byteMemberValue.getValue();
    }
}
