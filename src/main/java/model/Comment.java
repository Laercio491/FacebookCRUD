package model;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private String content;
    private LocalDateTime date;
    private User user;
    private Post post;

    public Comment() {
    }

    public Comment(int id, String content, LocalDateTime date,
                   User user, Post post) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
        this.post = post;
    }

    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}