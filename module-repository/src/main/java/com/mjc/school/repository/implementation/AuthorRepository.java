package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class)
                .getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        entityManager.getTransaction().begin();
        AuthorModel updatedModel = entityManager.find(AuthorModel.class, entity.getId());
        updatedModel.setName(entity.getName());
        entityManager.getTransaction().commit();
        return updatedModel;
    }

    @Override
    public boolean deleteById(Long id) {
        if (readById(id).isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(AuthorModel.class, id));
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
