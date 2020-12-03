package ru.job4j.html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 public class SqlRuParse {

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements date = doc.select(".altCol");
        Elements row = doc.select(".postslisttopic");
        int index = 1;
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(date.get(index).text());
            index += 2;
        }
    }
}



