package ru.job4j.html;

import java.time.LocalDateTime;

public class Post {
private int id;
private String name;
private String desc;
private String link;
private LocalDateTime created;

public Post(int id, String name, String desc, String link, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.created = created;
        }

        public Post(String name, String desc, String link, LocalDateTime created) {
        this(0, name, desc, link, created);
        }

        public Post setId(int id) {
        this.id = id;
        return this;
        }

        public String getName() {
        return name;
        }

        public String getDesc() {
        return desc;
        }

        public String getLink() {
        return link;
        }

        public LocalDateTime getCreated() {
        return created;
        }

        public int getId() {
        return id;
    }

@Override
public String toString() {
        return "Post detail:{" + System.lineSeparator()
        + "id='" + id + "'," + System.lineSeparator()
        + "name='" + name + "'," + System.lineSeparator()
        + "text='" + desc +  "'," + System.lineSeparator()
        + "link='" + link +  "'," + System.lineSeparator()
        + "created=" + created + System.lineSeparator()
        + '}' + System.lineSeparator();
        }
}