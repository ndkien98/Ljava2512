package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.repository.UserRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class UserRepositoryImpl implements UserRepository {

    private static final String ATTR_DELETED = "deleted";
    private static final String ATTR_FULL_NAME = "fullName";
    private static final String ATTR_EMAIL = "email";
    private static final String ATTR_CREATED_AT = "createdAt";
    private static final String ATTR_ROLES = "roles";
    private static final String ATTR_ROLE_NAME = "name";

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> findAll(String keyword, String role, int page, int size) {
        Session s = session();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(ATTR_DELETED), (byte) 0));

        if (keyword != null && !keyword.isBlank()) {
            String like = "%" + keyword.trim().toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get(ATTR_FULL_NAME)), like),
                    cb.like(cb.lower(root.get(ATTR_EMAIL)), like)
            ));
        }

        if (role != null && !role.isBlank()) {
            // use INNER join so filter actually restricts results
            Join<User, Role> roleJoin = root.join(ATTR_ROLES, JoinType.INNER);
            predicates.add(cb.equal(cb.lower(roleJoin.get(ATTR_ROLE_NAME)), role.trim().toLowerCase()));
            cq.distinct(true);
        }

        cq.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(root.get(ATTR_CREATED_AT)));

        int safePage = Math.max(page, 1);
        int safeSize = Math.min(Math.max(size, 1), 100);
        int offset = (safePage - 1) * safeSize;

        return s.createQuery(cq)
                .setFirstResult(offset)
                .setMaxResults(safeSize)
                .getResultList();
    }

    // keep countAll same but switch to INNER join for role filter
    @Override
    public long countAll(String keyword, String role) {
        Session s = session();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(ATTR_DELETED), (byte) 0));

        if (keyword != null && !keyword.isBlank()) {
            String like = "%" + keyword.trim().toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get(ATTR_FULL_NAME)), like),
                    cb.like(cb.lower(root.get(ATTR_EMAIL)), like)
            ));
        }

        if (role != null && !role.isBlank()) {
            Join<User, Role> roleJoin = root.join(ATTR_ROLES, JoinType.INNER);
            predicates.add(cb.equal(cb.lower(roleJoin.get(ATTR_ROLE_NAME)), role.trim().toLowerCase()));
            cq.distinct(true);
        }

        cq.select(cb.countDistinct(root)).where(predicates.toArray(new Predicate[0]));
        Long result = s.createQuery(cq).uniqueResult();
        return result == null ? 0 : result;
    }

    @Override
    public Optional<User> findById(Integer id) {
        if (id == null) return Optional.empty();

        // fetch roles eagerly for UI form mapping
        Query<User> q = session().createQuery(
                "select distinct u from User u left join fetch u.roles where u.deleted = 0 and u.id = :id",
                User.class
        );
        q.setParameter("id", id);
        return q.uniqueResultOptional();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.isBlank()) return Optional.empty();

        // Session is managed by Spring Transaction (@Transactional) via getCurrentSession()
        String hql = "select u from User u where u." + ATTR_DELETED + " = 0 and lower(u." + ATTR_EMAIL + ") = :email";
        Query<User> q = session().createQuery(hql, User.class);
        q.setParameter("email", email.trim().toLowerCase());
        return q.uniqueResultOptional();
    }

    @Override
    public User save(User user) {
        Session s = session();
        if (user.getId() == null) {
            s.persist(user);
            return user;
        }
        return s.merge(user);
    }

    @Override
    public int softDeleteById(Integer id) {
        if (id == null) return 0;

        MutationQuery q = session().createMutationQuery("update User u set u.deleted = 1 where u.id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
