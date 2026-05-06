package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    private final SessionFactory sessionFactory;

    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
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

