package com.bluedot.executor;

import com.bluedot.common.Result;

/**
 * @Project JavaCallOthers
 * @Package com.bluedot.executor
 * @DateTime 2023/2/10 13:16
 * @Author FuZhichao
 **/
public abstract class LangExecutor {
    protected Result result = new Result();
    public abstract boolean call(String filePath, String jsonData);

    public Result getResult() {
        return result;
    }
}
