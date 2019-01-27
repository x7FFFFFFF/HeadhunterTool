package ru.alex.vic.hhtool.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.alex.vic.hhtool.html.entities.Employee;
import ru.alex.vic.hhtool.html.entities.EmployeeBuilder;

public class HtmlParser {


    public static Employee parse(String html) {
        EmployeeBuilder builder = new EmployeeBuilder();
        final Document document = Jsoup.parse(html);
        final Element body = document.body();
        final Elements header = body.getElementsByClass("resume-header-block");
        builder.setSex(header);
        builder.setAge(header);
        builder.setCity(header);
        builder.setStation(header);
        builder.setBirth(header);
        final Elements education = body.getElementsByAttributeValue("data-qa", "resume-block-education");


        return builder.createEmployee();
    }










}
