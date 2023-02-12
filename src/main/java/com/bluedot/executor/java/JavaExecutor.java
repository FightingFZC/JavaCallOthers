package com.bluedot.executor.java;

import com.bluedot.common.ProcessExecutor;
import com.bluedot.common.Result;
import com.bluedot.executor.LangExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.executor.java
 * @DateTime 2023/1/30 14:15
 * @Author FuZhichao
 **/
public class JavaExecutor extends LangExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Thread.currentThread().getName());



    /**
     * 运行java文件
     * @param filePath 运行java文件的地址
     * @param jsonData 使用命令行传入的参数
     */
    @Override
    public boolean call(String filePath, String jsonData) {
        if (filePath == null || filePath.isEmpty()) {
            LOGGER.warn("传入地址不能为null");
            return false;
        }
        filePath = filePath.trim();
        String classFilePath = compile(filePath);
        LOGGER.debug("输出的类地址为：" + classFilePath);
        if (classFilePath.isEmpty()) {
            return false;
        }else {
            boolean run = run(classFilePath, jsonData);
            return run;
        }
    }

    /**
     * 编译java文件并返回java类文件的地址
     * 如果失败则返回空字符串
     * @param filePath java源代码文件地址
     * @return 编译后java代码的位置
     */
    private String compile(String filePath) {
        ProcessExecutor executor = new ProcessExecutor();
        String compileCommand = "javac";
        String specifiedLocationCommand = "-d";
        String targetDirectory = getTargetDirectory();

        String args = specifiedLocationCommand + " \"" + targetDirectory + "\" \"" + filePath + "\"";

        Result result = executor.execApp(compileCommand, args);
        if (!result.isSuccess()) {
            this.result = result;
            return "";
        }
        String className = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf(".java"));
        File classFile = new File(targetDirectory + File.separator + className + ".class");
        if (classFile.exists()) {
            return classFile.getAbsolutePath();
        }else {
            return "";
        }
    }

    private String getTargetDirectory() {
        String userDirectory = System.getProperty("user.home");
        String projectName = "JavaCallOthers";
        String language = "java";
        // 定义这次编译的id，格式为当前线程的哈希码+当前时间戳
        String id = "" + Thread.currentThread().hashCode() + System.currentTimeMillis();
        // 目录格式为：系统当前用户目录\.JavaCallOthers\java\id
        String targetDirectoryPath = userDirectory + File.separator + "." + projectName + File.separator + language + File.separator + id;
        File targetDirectory = new File(targetDirectoryPath);
        if (!targetDirectory.exists()) {
            boolean mkdir = targetDirectory.mkdirs();
            if (!mkdir) {
                LOGGER.warn("目录创建失败");
            }
        }

        return targetDirectory.getPath();
    }

    /**
     * 运行java类文件
     * @param classFilePath 类地址
     * @param jsonData 传入到main方法的args参数
     */
    private boolean run(String classFilePath, String jsonData) {
        String classPathCommand = "-cp \"" + new File(classFilePath).getParent() + "\"";
        String className = classFilePath.substring(classFilePath.lastIndexOf(File.separator) + 1, classFilePath.lastIndexOf(".class"));
        jsonData = "\"" + jsonData + "\"";
        String app = "java";
        String args = classPathCommand + " " + className + " " + jsonData;
        ProcessExecutor executor = new ProcessExecutor();
        Result result = executor.execApp(app, args);
        if (result.isSuccess()) {
            this.result = result;
            return true;
        } else {
            this.result = result;
            return false;
        }

    }

}

