package ru.alex.vic.hhtool.html.entities;

public class EducationBuilder {
    private String city;
    private String caption;
    private String department;
    private String specialization;
    private int finished;

    public EducationBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public EducationBuilder setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public EducationBuilder setDepartment(String department) {
        this.department = department;
        return this;
    }

    public EducationBuilder setSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public EducationBuilder setFinished(int finished) {
        this.finished = finished;
        return this;
    }

    public Education createEducation() {
        return new Education(city, caption, department, specialization, finished);
    }
}