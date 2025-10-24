package com.example.hrms.model;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String idCard;
    private String address;
    private String phone;
    private String email;
    private String specialty;
    private String education;
    private LocalDate politicalOrgDate;
    private LocalDate hireDate;
    private Long gradeId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public LocalDate getPoliticalOrgDate() { return politicalOrgDate; }
    public void setPoliticalOrgDate(LocalDate politicalOrgDate) { this.politicalOrgDate = politicalOrgDate; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }
}
