package com.cskaoyan.domain.vo;

import com.cskaoyan.domain.Product;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UnqualifyApplyVO {
    private String unqualifyApplyId;

    private String productId;

    private String unqualifyItem;

    private Integer unqualifyCount;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date assemblyDate;

    private String empId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    private String note;

    private String productName;

    private String empName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getUnqualifyApplyId() {
        return unqualifyApplyId;
    }

    public void setUnqualifyApplyId(String unqualifyApplyId) {
        this.unqualifyApplyId = unqualifyApplyId;
    }

    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnqualifyItem() {
        return unqualifyItem;
    }

    public void setUnqualifyItem(String unqualifyItem) {
        this.unqualifyItem = unqualifyItem;
    }

    public Integer getUnqualifyCount() {
        return unqualifyCount;
    }

    public void setUnqualifyCount(Integer unqualifyCount) {
        this.unqualifyCount = unqualifyCount;
    }

    public Date getAssemblyDate() {
        return assemblyDate;
    }

    public void setAssemblyDate(Date assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "UnqualifyApplyVO{" +
                "unqualifyApplyId='" + unqualifyApplyId + '\'' +
                ", productId='" + productId + '\'' +
                ", unqualifyItem='" + unqualifyItem + '\'' +
                ", unqualifyCount=" + unqualifyCount +
                ", assemblyDate=" + assemblyDate +
                ", empId='" + empId + '\'' +
                ", applyDate=" + applyDate +
                ", note='" + note + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}

