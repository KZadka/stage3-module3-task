package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.repository.model.implementation.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class)
                .getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        entityManager.getTransaction().begin();
        NewsModel updatedModel = entityManager.find(NewsModel.class, entity.getId());
        updatedModel.setTitle(entity.getTitle());
        updatedModel.setContent(entity.getContent());
        updatedModel.setAuthorModel(entity.getAuthorModel());
        List<TagModel> tagsToUpdate = entity.getTags();
        if (!tagsToUpdate.isEmpty()) {
            updatedModel.setTags(tagsToUpdate);
        }
        entityManager.getTransaction().commit();
        return updatedModel;
    }

    @Override
    public boolean deleteById(Long id) {
        if (readById(id).isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(NewsModel.class, id));
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
