package com.bluedot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.util
 * @DateTime 2023/1/30 15:44
 * @Author FuZhichao
 **/
public class ProcessExecutor {
    private static final Runtime RUNTIME = Runtime.getRuntime();

    public static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutor.class);

    /**
     * 允许程序运行多久的数值，默认为【3】
     */
    private int timeout = 3;

    /**
     * 允许程序运行时间的单位，默认为【秒】
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public Result execApp(String appName, String args) {
        Result result = new Result();

        if (appName == null || appName.isEmpty()) {
            LOGGER.debug("程序名不能为null或者程序名不能为空");
            return result;
        }

        if (args == null) {
            args = "";
        }

        Process pro = null;

        TimeUnit unit = TimeUnit.SECONDS;
        try {
            pro = RUNTIME.exec(appName + " " + args);
            LOGGER.debug(appName + " " + args);
            boolean isExit = pro.waitFor(timeout, unit);


            //要是一直跑那肯定是不行的，得制约一下，定个3秒中的时间吧。
            //超时就返回
            if (!isExit) {
                LOGGER.warn("运行超时（" + timeout + unit + "）");
                return result;
            }

            try (
                    InputStream errorStream = pro.getErrorStream();
                    InputStream inputStream = pro.getInputStream()) {
                //接下来就获取信息吧，分错误和正常
                //如果运行出错
                if (pro.exitValue() != 0) {
                    //获取错误信息
                    String errMsg = getMsg(errorStream);
                    result.setErrMsg(errMsg);
                    LOGGER.warn("\n运行错误：" + errMsg);
                } else {
                    //运行正确
                    //获取正确信息
                    result.setOutputMsg(getMsg(inputStream));
                    result.setSuccess(true);
                }
            }

        } catch (IOException | InterruptedException e) {
            result.setErrMsg(e.getMessage());
        } finally {
            if (pro != null) {
                pro.destroy();
            }
        }

        return result;

    }

    /**
     * 获得信息
     * @param ins 结果流
     */
    private static String getMsg(InputStream ins) {
        String line;
        StringBuilder builder = new StringBuilder();

        try(
                InputStreamReader isr = new InputStreamReader(ins, "GBK");
                BufferedReader in = new BufferedReader(isr)) {

            while ((line = in.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }


    /**
     * @return 允许程序运行的时长
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout 允许程序运行的时长
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return 允许程序运行时长的单位
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * @param timeUnit 允许程序运行时长的单位
     */
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
