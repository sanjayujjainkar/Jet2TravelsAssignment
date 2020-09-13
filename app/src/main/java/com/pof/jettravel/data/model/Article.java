package com.pof.jettravel.data.model;

import java.util.Date;
import java.util.List;

 public class Article {
    public String id;
    public Date createdAt;
    public String content;
    public int comments;
    public int likes;
    public List<Medium> media;
    public List<User> user;
}



