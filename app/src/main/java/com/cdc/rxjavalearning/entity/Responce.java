package com.cdc.rxjavalearning.entity;

/**
 * @author Charlie Zheng on 2017/4/27.
 */

public class Responce {
    private int error_code;
    private String resultCode;
    private String reason;
    private JUHEResult result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JUHEResult getResult() {
        return result;
    }

    public void setResult(JUHEResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Responce{" +
                "error_code=" + error_code +
                ", resultCode='" + resultCode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
