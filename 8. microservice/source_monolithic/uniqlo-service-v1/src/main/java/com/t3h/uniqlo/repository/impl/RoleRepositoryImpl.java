package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.repository.RoleRepository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    private final EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session session() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Optional<Role> findByName(String name) {
        if (name == null || name.isBlank()) return Optional.empty();
        Query<Role> q = session().createQuery(
                "select r from Role r where r.deleted = 0 and lower(r.name) = :name",
                Role.class
        );
        q.setParameter("name", name.trim().toLowerCase());
        return q.uniqueResultOptional();
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            session().persist(role);
            return role;
        }
        return session().merge(role);
    }
}

