package io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:22
 */
public class ShortMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        ShortMemberValue shortMemberValue = (ShortMemberValue) memberValue;
        return shortMemberValue.getValue();
    }
}
