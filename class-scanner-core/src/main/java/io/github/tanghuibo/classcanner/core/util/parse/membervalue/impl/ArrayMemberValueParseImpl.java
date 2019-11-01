package io.github.tanghuibo.classcanner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParseFactor;
import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;

import java.util.Arrays;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:16
 */
public class ArrayMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {

        ArrayMemberValue arrayMemberValue = (ArrayMemberValue) memberValue;

        return Arrays.stream(arrayMemberValue.getValue()).map(value -> {
            MemberValueParse build = MemberValueParseFactor.get(memberValue);
            return build.get(value);
        });
    }
}
