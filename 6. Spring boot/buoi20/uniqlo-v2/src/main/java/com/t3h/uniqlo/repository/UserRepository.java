package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository giao tiếp với DB sử dụng API Hibernate Session.
 * Cần được gọi trong vòng Context có session đang mở (thường là qua @Transactional ở Service).
 */
@Repository
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    /** Lấy Hibernate Session từ EntityManager mặc định của Spring Boot */
    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void save(User user) {
        if (user.getId() == null) {
            getSession().persist(user); // Insert
        } else {
            getSession().merge(user);   // Update
        }
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(getSession().get(User.class, id));
    }

    public Optional<User> findByEmail(String email) {
        Query<User> query = getSession().createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }

    public void delete(User user) {
        getSession().remove(user);
    }

    public boolean existsByEmailAndIdNot(String email, Integer id) {
        Query<Long> query = getSession().createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.id != :id", Long.class);
        query.setParameter("email", email);
        query.setParameter("id", id);
        return query.uniqueResult() > 0;
    }

    public long countByRole(String role) {
        Query<Long> query = getSession().createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.role = :role", Long.class);
        query.setParameter("role", role);
        return query.uniqueResult();
    }

    /**
     * Tìm kiếm và phân trang
     */
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

    /**
     * Đếm tổng số lượng bản ghi thỏa mãn điều kiện tìm kiếm (dùng cho phân trang)
     */
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
}
