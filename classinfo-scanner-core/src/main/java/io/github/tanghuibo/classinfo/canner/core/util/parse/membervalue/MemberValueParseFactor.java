package io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue;

import io.github.tanghuibo.classinfo.canner.core.util.parse.membervalue.impl.*;
import javassist.bytecode.annotation.*;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:13
 */
public class MemberValueParseFactor {
    /**
     * 创建memberValue解析器
     * @param memberValue
     * @return
     */
    public static MemberValueParse get(MemberValue memberValue) {
        if(memberValue == null) {
            return new NullMemberValueParseImpl();
        }
        if(memberValue instanceof BooleanMemberValue) {
            return new BooleanMemberValueParseImpl();
        }
        if(memberValue instanceof ClassMemberValue) {
            return new ClassMemberValueParseImpl();
        }

        if(memberValue instanceof StringMemberValue) {
            return new StringMemberValueParseImpl();
        }

        if(memberValue instanceof AnnotationMemberValue) {
            return new AnnotationMemberValueParseImpl();
        }

        if(memberValue instanceof ArrayMemberValue) {
            return new ArrayMemberValueParseImpl();
        }

        if(memberValue instanceof CharMemberValue) {
            return new CharMemberValueParseImpl();
        }
        if(memberValue instanceof DoubleMemberValue) {
            return new DoubleMemberValueParseImpl();
        }
        if(memberValue instanceof FloatMemberValue) {
            return new FloatMemberValueParseImpl();
        }
        if(memberValue instanceof EnumMemberValue) {
            return new EnumMemberValueParseImpl();
        }

        if(memberValue instanceof ShortMemberValue) {
            return new ShortMemberValueParseImpl();
        }

        if(memberValue instanceof IntegerMemberValue) {
            return new IntegerMemberValueParseImpl();
        }

        if(memberValue instanceof LongMemberValue) {
            return new LongMemberValueParseImpl();
        }

        return new NullMemberValueParseImpl();

    }
}
