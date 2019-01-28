package ru.alex.vic.hhtool.html.entities;

public class Education {
    private  String city;
    private  String caption;
    private  String department;
    private  String specialization;
    /**
     * class="bloko-link bloko-link_list"
     */
    @HtmlAttribute(attrName = "class", attrValue = "bloko-link bloko-link_list", valFromAttr = "href")
    private String internalId;

    @HtmlAttribute(attrName = "class", attrValue = "bloko-column bloko-column_s-2 bloko-column_m-2 bloko-column_l-2")
    private  int finished;

    public Education() {
    }

    public Education(String city, String caption, String department, String specialization, int finished) {
        this.city = city;
        this.caption = caption;
        this.department = department;
        this.specialization = specialization;
        this.finished = finished;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }
}
