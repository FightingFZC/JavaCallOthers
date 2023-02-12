package com.bluedot.executor.matlab;

import com.bluedot.common.ProcessExecutor;
import com.bluedot.common.Result;
import com.bluedot.executor.LangExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.executor.matlab
 * @DateTime 2023/2/10 17:49
 * @Author FuZhichao
 **/
public class MatlabExecutor extends LangExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Thread.currentThread().getName());
    @Override
    public boolean call(String filePath, String jsonData) {
        if (filePath == null || filePath.isEmpty()) {
            LOGGER.warn("传入地址不能为null");
            return false;
        }
        filePath = filePath.trim();

        return run(filePath, jsonData);
    }

    private boolean run(String filePath, String jsonData) {
        ProcessExecutor executor = new ProcessExecutor();
        String app = "matlab";
        File file = new File(filePath);
        //无窗口运行的参数
        String arg1 = "-nodesktop -nosplash -r ";
        //指定目录的参数
        String arg2 = "-sd \"" + file.getParent() + "\" ";
        //获得不带后缀名的文件名
        String fileName = file.getName();
        String arg3 = "\"" + fileName.substring(0, fileName.lastIndexOf(".m")) + "\"";
        Result result = executor.execApp(app, arg1 + arg2 + arg3);
        this.result = result;
        return result.isSuccess();
    }
}
