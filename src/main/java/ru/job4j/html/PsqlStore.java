package ru.job4j.html;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private final Connection cn;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cn = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password"));
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement st = cn.prepareStatement(
                "insert into post (name, post_desc, link, created) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, post.getName());
            st.setString(2, post.getDesc());
            st.setString(3, post.getLink());
            st.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> postList = new LinkedList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from post")) {
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Post post = new Post(rs.getString(2), rs.getString(
                        3), rs.getString(4), rs.getTimestamp(5).toLocalDateTime());
                post.setId(rs.getInt(1));
                postList.add(post);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement st = cn.prepareStatement("select * from post where id = ?")) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                post = new Post(rs.getString(2), rs.getString(3), rs.getString(
                        4), rs.getTimestamp(5).toLocalDateTime());
                post.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return post;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    public static void main(String[] args) {
        try (InputStream in = PsqlStore.class.getClassLoader()
                .getResourceAsStream("./db.properties")) {
            Properties config = new Properties();
            config.load(in);
            PsqlStore psqlStore = new PsqlStore(config);
            Post newPost = new Post("name", "desc", "link", LocalDateTime.now());
            System.out.println(newPost.toString());
            psqlStore.save(newPost);
            Post findPost =  psqlStore.findById(1);
            System.out.println(findPost.toString());
            List<Post> postList = psqlStore.getAll();
            postList.forEach(post -> System.out.println(post.toString()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}