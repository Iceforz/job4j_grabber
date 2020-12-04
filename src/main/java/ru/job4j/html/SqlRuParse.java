package ru.job4j.html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class SqlRuParse {

    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements date = doc.select(".altCol");
        Elements row = doc.select(".postslisttopic");
       String data = conversionDates("dd/MMM/yy", "dd/MM/yy", );
        int index = 1;
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(date.get(index).text());
            index += 2;
        }
    }

    public static String conversionDates(
            String inputPattern, String outputPattern, String givenDate) throws ParseException {
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            String requiredDate = null;

            date = inputFormat.parse(givenDate);
            requiredDate = outputFormat.format(date);

            return requiredDate;
        }
}



