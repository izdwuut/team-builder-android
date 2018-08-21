package com.example.konikiewiczb.myapplication.model;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
    Integer id;
    String description, status, name;
    LocalDateTime startDate, endDate;
    List<User> projectParticipants, projectCandidates;
    List<ProjectEntry> projectEntries;
    List<Technology> projectTechnologies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }



    public List<User> getProjectParticipants() {
        return projectParticipants;
    }

    public void setProjectParticipants(List<User> projectParticipants) {
        this.projectParticipants = projectParticipants;
    }

    public List<User> getProjectCandidates() {
        return projectCandidates;
    }

    public void setProjectCandidates(List<User> projectCandidates) {
        this.projectCandidates = projectCandidates;
    }

    public List<ProjectEntry> getProjectEntries() {
        return projectEntries;
    }

    public void setProjectEntries(List<ProjectEntry> projectEntries) {
        this.projectEntries = projectEntries;
    }

    public List<Technology> getProjectTechnologies() {
        return projectTechnologies;
    }

    public void setProjectTechnologies(List<Technology> projectTechnologies) {
        this.projectTechnologies = projectTechnologies;
    }


}
