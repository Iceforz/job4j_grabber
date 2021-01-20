package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class FivePages {

        public static void main(String[] args) throws IOException {
            printPages(5);
        }

        public static void printPages(int repeat) throws IOException {
            String name = "https://www.sql.ru/forum/job-offers/";
            for (int i = 1; i <= repeat; i++) {
                Document doc = Jsoup.connect(name + i).get();
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
    }
