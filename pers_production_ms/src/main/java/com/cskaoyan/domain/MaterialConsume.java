package com.cskaoyan.domain;



import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class MaterialConsume {

    private String consumeId;

    private String workId;

    private String materialId;

    private Material material;

    private Work work;

    @NotNull(message = "消耗数量不能为空！")
    private Integer consumeAmount;

    private Date consumeDate;

    @NotBlank(message = "发送者不能为空！")
    private String sender;

    @NotBlank(message = "接受者不能为空！")
    private String receiver;

    @NotBlank(message = "备注不能为空！")
    private String note;

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId == null ? null : consumeId.trim();
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Date getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "MaterialConsume{" +
                "consumeId='" + consumeId + '\'' +
                ", workId='" + workId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", material=" + material +
                ", work=" + work +
                ", consumeAmount=" + consumeAmount +
                ", consumeDate=" + consumeDate +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

}