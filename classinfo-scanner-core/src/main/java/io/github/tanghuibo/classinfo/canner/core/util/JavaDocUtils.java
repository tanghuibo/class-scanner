package io.github.tanghuibo.classinfo.canner.core.util;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import io.github.tanghuibo.classinfo.canner.core.bean.JavaDocInfo;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JavaDoc工具
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
        String[] commandList = getCommand(sourcePath);
        com.sun.tools.javadoc.Main.execute(
                commandList
        );
        ClassDoc[] classes = rootDoc.classes();
        return Arrays.stream(classes).map(JavaDocUtils::getJavaDocInfo).collect(Collectors.toList());
    }

    private static String[] getCommand(String sourcePath) {
        List<String> commandList = Arrays.stream(new String[]{
                "-doclet",
                JavaDocUtils.class.getName(),
                "-encoding", "utf-8",
                "-sourcepath", sourcePath
        }).collect(Collectors.toList());
        commandList.addAll(Arrays.stream(new File(sourcePath).list())
                .flatMap(item -> Stream.of("-subpackages", item))
                .collect(Collectors.toList()));
        return commandList.toArray(new String[0]);
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
