package com.bluedot;

import com.bluedot.common.Result;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot
 * @DateTime 2023/1/30 14:19
 * @Author FuZhichao
 **/
public class Main {
    public static void main(String[] args) {
//        String filePath = "C:\\Users\\FuZhichao\\Desktop\\Main.java";
//        Result result = CallExecutor.callJava(filePath, "{\"a\":1}");
//        if (result.isSuccess()) {
//            System.out.println(result.getOutputMsg());
//        }else {
//            System.out.println(result.getErrMsg());
//        }
        String jFile = "C:\\Users\\FuZhichao\\Desktop\\Main.java";
        String pFile = "C:\\Users\\FuZhichao\\Desktop\\test.py";
        String mFile = "C:\\Users\\FuZhichao\\Desktop\\test.m";
//        Result result = CallExecutor.callPython(pFile, "{\"a" +
//                "\":1}");
//        Result result = CallExecutor.callJava(jFile, "{\"a" +
//                "\":1}");
        Result result = CallExecutor.callMatlab(mFile, null);
        if (result.isSuccess()) {
            System.out.println(result.getOutputMsg());
        } else {
            System.out.println(result.getErrMsg());
        }


    }
}
