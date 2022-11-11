package com.example.boot.entity;

/**
 * 返回结果类
 *
 * @author ltk
 * @date 2019/11/24
 */
public class Result {
    private String result;
    private String message;

    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";
    public static final String EMPTY = "";

    public Result() {
        this.result = SUCCESS;
        this.message = EMPTY;
    }

    public Result(String message) {
        this.result = FAIL;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
