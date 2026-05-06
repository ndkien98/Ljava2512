package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.repository.UserRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UserRepository {

    @Override
    public Optional<User> findByEmail(String email) {
        Query<User> query = getSession().createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Integer id) {
        Query<Long> query = getSession().createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.id != :id", Long.class);
        query.setParameter("email", email);
        query.setParameter("id", id);
        return query.uniqueResult() > 0;
    }

    @Override
    public long countByRole(String role) {
        Query<Long> query = getSession().createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.role = :role", Long.class);
        query.setParameter("role", role);
        return query.uniqueResult();
    }

    @Override
    public List<User> search(String keyword, String role, int offset, int limit) {
        StringBuilder hql = new StringBuilder("FROM User u WHERE 1=1 ");

        if (keyword != null && !keyword.trim().isEmpty()) {
            hql.append("AND (LOWER(u.fullName) LIKE LOWER(:keyword) OR LOWER(u.email) LIKE LOWER(:keyword)) ");
        }
        if (role != null && !role.trim().isEmpty()) {
            hql.append("AND u.role = :role ");
        }
        hql.append("ORDER BY u.createdAt DESC");

        Query<User> query = getSession().createQuery(hql.toString(), User.class);

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword.trim() + "%");
        }
        if (role != null && !role.trim().isEmpty()) {
            query.setParameter("role", role.trim());
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.list();
    }

    @Override
    public long countSearch(String keyword, String role) {
        StringBuilder hql = new StringBuilder("SELECT COUNT(u) FROM User u WHERE 1=1 ");

        if (keyword != null && !keyword.trim().isEmpty()) {
            hql.append("AND (LOWER(u.fullName) LIKE LOWER(:keyword) OR LOWER(u.email) LIKE LOWER(:keyword)) ");
        }
        if (role != null && !role.trim().isEmpty()) {
            hql.append("AND u.role = :role ");
        }

        Query<Long> query = getSession().createQuery(hql.toString(), Long.class);

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword.trim() + "%");
        }
        if (role != null && !role.trim().isEmpty()) {
            query.setParameter("role", role.trim());
        }

        return query.uniqueResult();
    }

    public List<User> searchWithCriteria(String keyword, String role, int offset, int limit) {
        final String kw = keyword == null ? null : keyword.trim();
        final String rl = role == null ? null : role.trim();

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (kw != null && !kw.isEmpty()) {
            String like = "%" + kw.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("fullName")), like),
                    cb.like(cb.lower(root.get("email")), like)
            ));
        }

        if (rl != null && !rl.isEmpty()) {
            predicates.add(cb.equal(root.get("role"), rl));
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }
        cq.orderBy(cb.desc(root.get("createdAt")));

        return getSession().createQuery(cq)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
