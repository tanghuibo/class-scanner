package io.github.tanghuibo.classcanner.core.util.parse.membervalue;

import javassist.bytecode.annotation.MemberValue;

/**
 * @author tanghuibo
 * @date 19-11-2上午12:10
 */
public class MemberValueParseUtil {

    /**
     * 解析
     * @param memberValue
     * @return
     */
    public static Object parse(MemberValue memberValue) {
        if(memberValue == null) {
            return null;
        }
        MemberValueParse parser = MemberValueParseFactor.get(memberValue);
        if(parser == null) {
            return null;
        }
        return parser.get(memberValue);
    }


}
