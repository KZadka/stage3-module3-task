package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.TagModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class)
                .getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        entityManager.getTransaction().begin();
        TagModel updatedModel = entityManager.find(TagModel.class, entity.getId());
        updatedModel.setName(entity.getName());
        entityManager.getTransaction().commit();
        return updatedModel;
    }

    @Override
    public boolean deleteById(Long id) {
        if (readById(id).isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(TagModel.class, id));
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
