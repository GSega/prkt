package com.project.prkt.model;

public class EquipmentAssignmentRequest {

    private Long assignedSnowboardId;
    private Long assignedSnowboardBootsId;
    private Long assignedSkiId;
    private Long assignedSkiBootsId;

    public EquipmentAssignmentRequest() {
    }

    public EquipmentAssignmentRequest(Long assignedSnowboardId, Long assignedSnowboardBootsId, Long assignedSkiId, Long assignedSkiBootsId) {
        this.assignedSnowboardId = assignedSnowboardId;
        this.assignedSnowboardBootsId = assignedSnowboardBootsId;
        this.assignedSkiId = assignedSkiId;
        this.assignedSkiBootsId = assignedSkiBootsId;
    }


    public Long getAssignedSnowboardId() {
        return assignedSnowboardId;
    }

    public void setAssignedSnowboardId(Long assignedSnowboardId) {
        this.assignedSnowboardId = assignedSnowboardId;
    }

    public Long getAssignedSnowboardBootsId() {
        return assignedSnowboardBootsId;
    }

    public void setAssignedSnowboardBootsId(Long assignedSnowboardBootsId) {
        this.assignedSnowboardBootsId = assignedSnowboardBootsId;
    }

    public Long getAssignedSkiId() {
        return assignedSkiId;
    }

    public void setAssignedSkiId(Long assignedSkiId) {
        this.assignedSkiId = assignedSkiId;
    }

    public Long getAssignedSkiBootsId() {
        return assignedSkiBootsId;
    }

    public void setAssignedSkiBootsId(Long assignedSkiBootsId) {
        this.assignedSkiBootsId = assignedSkiBootsId;
    }
}
