package com.cskaoyan.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UnqualifyApply {
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

    @Override
    public String toString() {
        return "UnqualifyApply{" +
                "unqualifyApplyId='" + unqualifyApplyId + '\'' +
                ", productId='" + productId + '\'' +
                ", unqualifyItem='" + unqualifyItem + '\'' +
                ", unqualifyCount=" + unqualifyCount +
                ", assemblyDate=" + assemblyDate +
                ", empId='" + empId + '\'' +
                ", applyDate=" + applyDate +
                ", note='" + note + '\'' +
                '}';
    }

    public String getUnqualifyApplyId() {
        return unqualifyApplyId;
    }

    public void setUnqualifyApplyId(String unqualifyApplyId) {
        this.unqualifyApplyId = unqualifyApplyId == null ? null : unqualifyApplyId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getUnqualifyItem() {
        return unqualifyItem;
    }

    public void setUnqualifyItem(String unqualifyItem) {
        this.unqualifyItem = unqualifyItem == null ? null : unqualifyItem.trim();
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
        this.empId = empId == null ? null : empId.trim();
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
        this.note = note == null ? null : note.trim();
    }
}