package com.cskaoyan.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Material {


    @Size(min = 3, max = 20, message = "物料编号长度不符！必须在{min}到{max}之间")
    private String materialId;

    @NotBlank(message = "物料类型不能为空")
    private String materialType;

    private String status;

    @NotNull(message = "剩余数量不能为空")
    private Integer remaining;

    @NotBlank(message = "备注不能为空")
    private String note;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId='" + materialId + '\'' +
                ", materialType='" + materialType + '\'' +
                ", status='" + status + '\'' +
                ", remaining=" + remaining +
                ", note='" + note + '\'' +
                '}';
    }
}