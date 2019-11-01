package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类扫描工具
 * @author tanghuibo
 * @date 19-11-2上午12:54
 */
public class ClassScanUtils {

    /**
     * 扫描classInfo
     * @param classPath
     * @return
     * @throws NotFoundException
     */
    public static List<ClassInfo> scan(String classPath) throws NotFoundException {
        List<ClassInfo> resultList = new ArrayList<>();
        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath(classPath);
        List<File> fileList = Arrays.asList(new File(classPath).listFiles());
        while (fileList.size() != 0) {
            List<File> newFileList = fileList;
            fileList = new ArrayList<>();
            for (File file: newFileList) {
                String path = file.getPath();
                if(file.isDirectory()) {
                    fileList.addAll(Arrays.asList(file.listFiles()));
                    continue;
                }
                //以.class文件结尾为字节码
                if(path.endsWith(".class")) {
                    //去除掉头尾
                    path = path.substring(classPath.length() + 1, path.length() - ".class".length());
                    //斜杠转成. => io/github/tanghuibo -> io.github.tanghuibo
                    String className = path.replace(File.separatorChar, '.');
                    ClassInfo classInfo = ClassInfoUtils.getClassInfo(classPool.getCtClass(className));
                    resultList.add(classInfo);
                }
            }
        }
        return resultList;
    }
}
