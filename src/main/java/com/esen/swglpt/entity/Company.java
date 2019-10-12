package com.esen.swglpt.entity;

public class Company {
    private Integer companyId;

    private String companyAddress;

    private String companyDescription;

    private Integer companyPeopleNum;

    private String companyName;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription == null ? null : companyDescription.trim();
    }

    public Integer getCompanyPeopleNum() {
        return companyPeopleNum;
    }

    public void setCompanyPeopleNum(Integer companyPeopleNum) {
        this.companyPeopleNum = companyPeopleNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}