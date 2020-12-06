package ru.job4j.html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;

public class SqlRuParse {
    private static int id;

    public static void main(String[] args) throws IOException {
        printJobsList(5);
        System.out.println(getJobInfo("https://www.sql.ru/forum/1331208/"));
    }

    public static void printJobsList(int repeats) throws IOException {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements date = doc.select(".altCol");
        Elements row = doc.select(".postslisttopic");
        int index = 1;
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(RussianDate.convert(date.get(index).text()));
            index += 2;
        }
    }

    public static Post getJobInfo(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .get();
        Elements header = doc.select(".messageHeader");
        Elements body = doc.select(".msgBody");
        Elements footer = doc.select(".msgFooter");
        String title = header.first().text();
        String desc = body.get(1).text();
        LocalDateTime date = RussianDate.convert(footer.first()
                .text()
                .split("\\s\\[")[0]);
        return new Post(title, desc, url, date);
    }
}




