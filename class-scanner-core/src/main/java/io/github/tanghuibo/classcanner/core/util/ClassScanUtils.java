package io.github.tanghuibo.classcanner.core.util;

import io.github.tanghuibo.classcanner.core.bean.ClassInfo;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类扫描工具
 * @author tanghuibo
 * @date 19-11-2上午12:54
 */
public class ClassScanUtils {

    private ClassInfoUtils classInfoUtils = null;
    private Predicate<ClassInfo> predicate = item -> true;

    public ClassScanUtils(ClassInfoUtils classInfoUtils) {
        this.classInfoUtils = classInfoUtils;
    }

    public List<ClassInfo> scan(List<String> classPaths) {
        return classPaths.stream().flatMap(classPath -> scan(classPath).stream()).collect(Collectors.toList());
    }

    /**
     * 扫描classInfo
     * @param classPath
     * @return
     */
    public List<ClassInfo> scan(String classPath) {
        List<ClassInfo> resultList = new ArrayList<>();
        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath(classPath);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
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
                    ClassInfo classInfo = null;
                    try {
                        classInfo = classInfoUtils.getClassInfo(classPool.getCtClass(className));
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                    Stream stream = null;

                    if(predicate.test(classInfo)) {
                        resultList.add(classInfo);
                    }

                }
            }
        }
        return resultList;
    }

    public void addFilter(Predicate<ClassInfo> predicate) {
        this.predicate = this.predicate.and(predicate);
    }

}
