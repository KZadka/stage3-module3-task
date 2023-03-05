package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class TagModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tagModels", fetch = FetchType.LAZY)
    private List<NewsModel> newsModels;

    public TagModel() {}

    public TagModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagModel(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsModel> getNewsModels() {
        return newsModels;
    }

    public void setNewsModels(List<NewsModel> news) {
        this.newsModels = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagModel tagModel = (TagModel) o;
        return id.equals(tagModel.id) &&
                name.equals(tagModel.name) &&
                Objects.equals(newsModels, tagModel.newsModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, newsModels);
    }
}
