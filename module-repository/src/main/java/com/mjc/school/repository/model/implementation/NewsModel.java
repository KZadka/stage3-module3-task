package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "news")
public class NewsModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tagModels;

    public NewsModel(Long id, String title,
                     String content, LocalDateTime createDate,
                     LocalDateTime lastUpdateDate, AuthorModel authorModel,
                    List<TagModel> tagModels) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorModel = authorModel;
        this.tagModels = tagModels;
    }

    public NewsModel() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public List<TagModel> getTagModels() {
        return tagModels;
    }

    public void setTagModels(List<TagModel> tags) {
        this.tagModels = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return Objects.equals(id, newsModel.id) &&
                Objects.equals(title, newsModel.title) &&
                Objects.equals(content, newsModel.content) &&
                Objects.equals(createDate, newsModel.createDate) &&
                Objects.equals(lastUpdateDate, newsModel.lastUpdateDate) &&
                Objects.equals(authorModel, newsModel.authorModel) &&
                Objects.equals(tagModels, newsModel.tagModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorModel, tagModels);
    }
}
