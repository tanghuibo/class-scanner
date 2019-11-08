package io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:20
 */
public class FloatMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        FloatMemberValue floatMemberValue = (FloatMemberValue) memberValue;
        return floatMemberValue.getValue();
    }
}
