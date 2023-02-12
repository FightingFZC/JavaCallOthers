package com.bluedot;


import com.bluedot.common.Language;
import com.bluedot.common.Result;
import com.bluedot.executor.LangExecutor;
import com.bluedot.executor.java.JavaExecutor;
import com.bluedot.executor.matlab.MatlabExecutor;
import com.bluedot.executor.python.PythonExecutor;

public class CallExecutor {

    public static Result callPython(String filePath, String jsonData) {
        return call(filePath, jsonData, Language.Python);
    }

    public static Result callJava(String filePath, String jsonData) {
        return call(filePath, jsonData, Language.Java);
    }

    /**
     * TODO 目前还有问题，Windows里调用会出现matlab的命令窗，不能获取输出的参数
     * 不过无桌面的操作系统例如Linux调用并不会出现matlab的命令窗口，这样或许就能获得输出的参数了
     */
    public static Result callMatlab(String filePath, String jsonData) {
        return call(filePath, jsonData, Language.Matlab);
    }

    private static Result call(String filePath, String jsonData, Language language) {
        LangExecutor executor = null;
        switch (language) {
            case Java: {
                executor = new JavaExecutor();
            }break;
            case Python: {
                executor = new PythonExecutor();
            }break;
            case Matlab: {
                executor = new MatlabExecutor();
            }break;
            default: {
                executor = new JavaExecutor();
            }
        }
        executor.call(filePath, jsonData);
        return executor.getResult();
    }
}
