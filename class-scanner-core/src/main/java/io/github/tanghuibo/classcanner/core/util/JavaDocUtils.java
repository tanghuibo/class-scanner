package io.github.tanghuibo.classcanner.core.util;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import io.github.tanghuibo.classcanner.core.bean.JavaDocInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanghuibo
 * @date 19-11-7上午12:00
 */
public class JavaDocUtils {
    /**
     * javaDoc工具
     */
    private static RootDoc rootDoc;

    public static boolean start(RootDoc rootDoc) {
        JavaDocUtils.rootDoc = rootDoc;
        return true;
    }

    /**
     * 扫描文件夹获取javaDoc信息
     * @param sourcePath
     * @return
     */
    public static synchronized List<JavaDocInfo> scan(String sourcePath) {
        com.sun.tools.javadoc.Main.execute(
                new String[] {
                        "-doclet",
                        JavaDocUtils.class.getName(),
                        "-encoding", "utf-8",
                        "-sourcepath",
                        sourcePath
                }
        );
        ClassDoc[] classes = rootDoc.classes();
        return Arrays.stream(classes).map(JavaDocUtils::getJavaDocInfo).collect(Collectors.toList());
    }

    /**
     * classDoc转换
     * @param classDoc
     * @return
     */
    private static JavaDocInfo getJavaDocInfo(ClassDoc classDoc) {
        JavaDocInfo javaDocInfo = new JavaDocInfo();
        javaDocInfo.setType(classDoc.qualifiedTypeName());
        javaDocInfo.setCommentText(classDoc.commentText());
        javaDocInfo.setTagMap(JavaDocTagUtils.getTagMap(classDoc.tags()));
        javaDocInfo.setMethodInfoList(JavaDocMethodUtils.getMethodList(classDoc.methods()));
        return javaDocInfo;
    }


}
