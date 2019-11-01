package io.github.tanghuibo.classcanner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:18
 */
public class ClassMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        ClassMemberValue classMemberValue = (ClassMemberValue) memberValue;
        return classMemberValue.getValue();
    }
}
