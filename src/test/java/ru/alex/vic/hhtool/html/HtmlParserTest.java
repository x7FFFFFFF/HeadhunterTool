package ru.alex.vic.hhtool.html;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import ru.alex.vic.hhtool.html.entities.Education;
import ru.alex.vic.hhtool.html.entities.Employee;
import ru.alex.vic.hhtool.html.entities.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HtmlParserTest {

    @Test
    public void parse() throws Exception {

        final String content = getContent(getClass(), "employee1.html");
        final Employee employee = HtmlParser.parse(content, Employee.class);
        Assert.assertEquals(26, employee.getAge());
        Assert.assertEquals(Sex.MALE, employee.getSex());
        Assert.assertEquals("Москва", employee.getCity());
        Assert.assertEquals("м. Бабушкинская", employee.getStation());
        final LocalDate birth = employee.getBirth();
        Assert.assertEquals(1992, birth.getYear());
        Assert.assertEquals(4, birth.getMonthOfYear());
        Assert.assertEquals(1, birth.getDayOfMonth());


        final Education education = employee.getEducations().iterator().next();

        Assert.assertEquals("Долгопрудный", education.getCity());
        Assert.assertEquals("Московский физико-технический институт (Государственный университет)", education.getCaption());
        Assert.assertEquals("Общей и прикладной физики", education.getDepartment());
        Assert.assertEquals( "Прикладная физика и математека", education.getSpecialization());

    }

    private String getContent(Class<?> clz, String name) {
        try (final InputStream is = clz.getResourceAsStream(name)) {
            return new BufferedReader(new InputStreamReader(is, "UTF-8"))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}