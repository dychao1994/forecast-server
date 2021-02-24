package com.dychao.forcastserver.model;


import com.dychao.forcastserver.tools.LogWorker;

import java.util.LinkedHashMap;

/**
 * Created by gaoshlsh@neusoft on 2019/6/14
 */
public class ReturnEnity {

    private LinkedHashMap data = new LinkedHashMap();
    private String info="处理成功";
    private boolean status=true;//默认返回成功
    private String errCode="2000";//默认成功返回2000错误码

    public ReturnEnity() {
    }

    public ReturnEnity(LinkedHashMap data, String info, boolean status) {
        this.data = data;
        this.info = info;
        this.status = status;
        this.errCode = "2000";
    }

    public ReturnEnity(LinkedHashMap data) {
        this.data = data;
        this.info = "ok";
        this.status = true;
        this.errCode = "2000";
    }

    public ReturnEnity(LinkedHashMap data, boolean flag) {
        this.data = data;
        if (true == flag) {
            this.info = "ok";
            this.status = true;
            this.errCode = "2000";
        } else {
            this.info = "ok";
            this.status = false;
            this.errCode = "5001";
            LogWorker.out(data);
        }
    }

    public ReturnEnity(LinkedHashMap data, boolean flag, String info) {
        this.data = data;
        if (true == flag) {
            this.info = info;
            this.status = true;
            this.errCode = "2000";
        } else {
            this.info = info;
            this.status = false;
            this.errCode = "5001";
            LogWorker.out(data);
        }
    }

    public LinkedHashMap getData() {
        return data;
    }

    public void setData(LinkedHashMap data) {
        this.data = data;
    }

    public void setData(String key, Object value) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(key, value);
        this.data = linkedHashMap;
    }

    public void addData(String key, Object value) {
        LinkedHashMap linkedHashMap = this.data;
        linkedHashMap.put(key, value);
        this.data = linkedHashMap;
    }

    public String getMessage() {
        return info;
    }

    public void setMessage(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }


    @Override
    public String toString() {
        return "ReturnEnity{" +
                "data=" + data +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", errCode='" + errCode + '\'' +
                '}';
    }
}
