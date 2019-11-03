package io.github.tanghuibo.classcanner.core.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tanghuibo
 * @date 19-11-3下午7:16
 */
public class MavenUtils {

    /**
     * 获取 modules
     * @param path
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    static List<String> getModules(String path) throws JDOMException, IOException {
        String dirPath = (path.endsWith(File.separator) ? path : (path + File.separator));
        String pomPah = dirPath + "pom.xml";
        if(!new File(pomPah).exists()) {
            return new ArrayList<>(0);
        }
        // 创建sax解析器
        SAXBuilder saxBuilder = new SAXBuilder();

        // 获取Document
        Document document = saxBuilder.build(pomPah);

        Element classElement = document.getRootElement();

        List<String> names = classElement.getChildren().stream().filter(element -> "modules".equals(element.getName()))
                .map(Element::getChildren).filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(Element::getText).filter(Objects::nonNull)
                .map(item -> dirPath + item)
                .collect(Collectors.toList());
        return names;

    }

    /**
     * 获取所有子模块
     * @param path
     * @return
     */
    static List<String> getAllModules(String path) {
        List<String> newList = new ArrayList<>(0);
        List<String> resultList = new ArrayList<>(0);
        try {
            newList = getModules(path);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (newList.size() > 0) {
            resultList.addAll(newList);
            List<String> list = newList;
            newList = new ArrayList<>();
            for (String newPath: list) {
                try {
                    newList.addAll(getModules(newPath));
                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    /**
     * 模块路径转换classPath
     * @param modulePaths
     * @return
     */
    public static List<String> getClassPaths(List<String> modulePaths) {
        return modulePaths.stream()
                .map(item -> (item.endsWith(File.separator) ? item : item + File.separator) + "target/classes")
                .filter(item -> new File(item).isDirectory()).collect(Collectors.toList());
    }
}
