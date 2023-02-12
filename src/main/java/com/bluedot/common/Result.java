package com.bluedot.common;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.common
 * @DateTime 2023/2/7 17:46
 * @Author FuZhichao
 **/
public class Result {
    /**
     * 是否运行成功
     */
    private boolean isSuccess;

    /**
     * 程序输出的信息
     */
    private String outputMsg;

    /**
     * 程序输出的错误信息
     */
    private String errMsg;

    /**
     * 返回程序是否运行成功，没调用execApp方法的时候值为null
     * @return 程序是否运行成功
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param success 布尔值
     * 设置程序是否运行成功
     */
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     * 获取程序运行时输出的信息
     * 例如调用java程序后，通过这个方法可以获取程序中输出到控制台的信息
     * @return 程序运行时输出的信息
     */
    public String getOutputMsg() {
        return outputMsg;
    }

    /**
     * @param outputMsg 设置程序运行时输出的信息
     */
    public void setOutputMsg(String outputMsg) {
        this.outputMsg = outputMsg;
    }

    /**
     * 返回程序运行错误的信息
     * @return 程序运行错误的信息
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg 程序运行错误的信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
