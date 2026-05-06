package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Base Repository Implementation (Generic DAO Pattern).
 * Dùng Hibernate Session API.
 */
public abstract class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {

    @Autowired
    protected EntityManager entityManager;

    protected final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void save(T entity) {
        // saveOrUpdate handle được cả việc cấp phát ID cho insert và update bản ghi cũ
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().remove(entity);
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(getSession().get(persistentClass, id));
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM " + persistentClass.getSimpleName();
        return getSession().createQuery(hql, persistentClass).list();
    }
}
