package io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:12
 */
public class StringMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        StringMemberValue stringMemberValue = (StringMemberValue) memberValue;
        return stringMemberValue.getValue();
    }
}
