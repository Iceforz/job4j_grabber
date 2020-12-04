package ru.job4j.html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.DocFlavor;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class SqlRuParse {

    public static void main(String[] args) throws Exception {

        FileOutputStream file = new FileOutputStream("./src/dates.txt");
        TeePrintStream tee = new TeePrintStream(file, System.out);
        System.setOut(tee);
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements date = doc.select(".altCol");
        Elements row = doc.select(".postslisttopic");
        //   String data = conversionDates("dd/MMM/yy", "dd/MM/yy", "reader");
        int index = 1;
        for (Element td : row) {
            Element href = td.child(0);
            //System.out.println(href.attr("href"));
           // System.out.println(href.text());
            System.out.println(date.get(index).text());
         //   System.out.println(data);
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

    public static class TeePrintStream extends PrintStream {
        private final PrintStream second;

        public TeePrintStream(OutputStream main, PrintStream second) {
            super(main);
            this.second = second;
        }

        @Override
        public void close() {
            super.close();
        }

        @Override
        public void flush() {
            super.flush();
            second.flush();
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            second.write(buf, off, len);
        }

        @Override
        public void write(int b) {
            super.write(b);
            second.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
            second.write(b);
        }
    }
}




