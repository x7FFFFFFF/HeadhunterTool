package ru.alex.vic.hhtool.html;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ru.alex.vic.hhtool.html.entities.Sex;

import java.math.BigDecimal;
import java.util.Arrays;

public class Converter {
    private static final DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static final String DELIM = " ";

    public static Sex convertGender(String gender) {
        return Sex.convert(gender);
    }

    public static int convertAge(String text) {
        return Arrays.stream(text.split(DELIM))
                .filter(Util::isDigit)
                .map(Integer::valueOf)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }


    public static BigDecimal convertSalary(String text) {
        return null;
    }

    public static LocalDate convertDate(String text) {
        return dateStringFormat.parseLocalDate(text);
    }
}
