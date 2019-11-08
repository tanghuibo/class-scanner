package io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:11
 */
public class BooleanMemberValueParseImpl implements MemberValueParse {

    @Override
    public Object get(MemberValue memberValue) {
        BooleanMemberValue booleanMemberValue = (BooleanMemberValue) memberValue;
        return booleanMemberValue.getValue();
    }
}
