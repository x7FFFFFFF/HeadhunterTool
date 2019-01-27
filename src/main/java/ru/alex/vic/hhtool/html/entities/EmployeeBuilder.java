package ru.alex.vic.hhtool.html.entities;

import org.joda.time.LocalDate;
import org.jsoup.select.Elements;
import ru.alex.vic.hhtool.html.Converter;
import ru.alex.vic.hhtool.html.Util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmployeeBuilder {
    private static final Map<String, Field> fieldMap = Util.getFields(Employee.class);
    private Sex sex;
    private String vacancy;
    private int age;
    private LocalDate birth;
    private String city;
    private String station;
    private final List<Education> educations = new ArrayList<>();
    private byte[] foto;
    private byte[] source;
    private String link;
    private BigDecimal salary;

    public EmployeeBuilder setSex(Elements elements) {
        final String text = getString(elements, "sex");
        this.sex = Converter.convertGender(text);
        return this;
    }

    public EmployeeBuilder setVacancy(String vacancy) {
        this.vacancy = vacancy;
        return this;
    }

    public EmployeeBuilder setAge(Elements elements) {
        final String text = getString(elements, "age");
        this.age = Converter.convertAge(text);
        return this;
    }

    private String getString(Elements elements, String name) {
        final Field field = fieldMap.get(name);
        return Util.getTextByAttr(elements, field);
    }

    public EmployeeBuilder setBirth(Elements elements) {
        final String text = getString(elements, "birth");
        this.birth = Converter.convertDate(text);
        return this;
    }

    public EmployeeBuilder setCity(Elements elements) {
        this.city = getString(elements, "city");
        return this;
    }

    public EmployeeBuilder setStation(Elements elements) {
        this.station = getString(elements, "station");
        return this;
    }

    public EmployeeBuilder addEducations(Education education) {
        this.educations.add(education);
        return this;
    }

    public EmployeeBuilder setFoto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public EmployeeBuilder setSource(byte[] source) {
        this.source = source;
        return this;
    }

    public EmployeeBuilder setLink(String link) {
        this.link = link;
        return this;
    }

    public EmployeeBuilder setSalary(Elements elements) {
        final String text = getString(elements, "salary");
        this.salary = Converter.convertSalary(text);
        return this;
    }



    public Employee createEmployee() {
        return new Employee(sex, vacancy, age, birth, city, station, link, educations, foto, source, salary);
    }
}