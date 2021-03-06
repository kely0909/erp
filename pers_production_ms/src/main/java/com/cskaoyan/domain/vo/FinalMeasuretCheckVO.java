package com.cskaoyan.domain.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FinalMeasuretCheckVO {
    private String fMeasureCheckId;

    private String orderId;

    private String checkItem;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cdate;

    private String measureData;

    private String empId;

    private String result;

    private String note;

    private String empName;

    public String getfMeasureCheckId() {
        return fMeasureCheckId;
    }

    public void setfMeasureCheckId(String fMeasureCheckId) {
        this.fMeasureCheckId = fMeasureCheckId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getMeasureData() {
        return measureData;
    }

    public void setMeasureData(String measureData) {
        this.measureData = measureData;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "FinalMeasuretCheckVO{" +
                "fMeasureCheckId='" + fMeasureCheckId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", checkItem='" + checkItem + '\'' +
                ", cdate=" + cdate +
                ", measureData='" + measureData + '\'' +
                ", empId='" + empId + '\'' +
                ", result='" + result + '\'' +
                ", note='" + note + '\'' +
                ", empName='" + empName + '\'' +
                '}';
    }
}
