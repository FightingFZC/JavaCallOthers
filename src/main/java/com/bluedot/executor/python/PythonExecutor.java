package com.bluedot.executor.python;

import com.bluedot.common.ProcessExecutor;
import com.bluedot.common.Result;
import com.bluedot.executor.LangExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.executor.python
 * @DateTime 2023/2/8 13:22
 * @Author FuZhichao
 **/
public class PythonExecutor extends LangExecutor {

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
        String app = "python";
        String args = "\"" + filePath + "\"" + " \"" + jsonData + "\"";
        Result result = executor.execApp(app, args);
        this.result = result;

        return result.isSuccess();
    }
}
