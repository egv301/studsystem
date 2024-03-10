package com.example.studsystem.dto;

import java.util.List;

public class PointsForAssignmentFormWrapperDTO {
	
	private Long assignmentId;
	private String assignmentTitle;
	private List<PointsForAssignmentFormDTO> pointsAssignmentList;
	public Long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getAssignmentTitle() {
		return assignmentTitle;
	}
	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}
	public List<PointsForAssignmentFormDTO> getPointsAssignmentList() {
		return pointsAssignmentList;
	}
	public void setPointsAssignmentList(List<PointsForAssignmentFormDTO> pointsAssignmentList) {
		this.pointsAssignmentList = pointsAssignmentList;
	}
	
	
}
