package ru.job4j.html;

import java.time.LocalDateTime;

public class Post {
    private final String title;
    private final String desc;
    private final LocalDateTime date;
    private final String link;

    public Post(String title, String desc, LocalDateTime date, String link) {
            this.title = title;
            this.desc = desc;
            this.date = date;
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public String getLink() {
            return link;
        }

        @Override
        public String toString() {
            return "Post{"
                    + "title='" + title + '\''
                    + ", description='" + desc + '\''
                    + ", date=" + date
                    + ", link='" + link + '\''
                    + '}';
        }
    }