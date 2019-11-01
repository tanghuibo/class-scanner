package io.github.tanghuibo.classcanner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:22
 */
public class IntegerMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        IntegerMemberValue integerMemberValue = (IntegerMemberValue) memberValue;
        return integerMemberValue.getValue();
    }
}
