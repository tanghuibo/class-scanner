package io.github.tanghuibo.classcanner.core.util.parse.membervalue.impl;

import io.github.tanghuibo.classcanner.core.util.AnnotationUtils;
import io.github.tanghuibo.classcanner.core.util.parse.membervalue.MemberValueParse;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:15
 */
public class AnnotationMemberValueParseImpl implements MemberValueParse {
    @Override
    public Object get(MemberValue memberValue) {
        AnnotationMemberValue annotationMemberValue = (AnnotationMemberValue) memberValue;
        Annotation value = annotationMemberValue.getValue();
        return AnnotationUtils.toAnnotationInfo(value);
    }
}
