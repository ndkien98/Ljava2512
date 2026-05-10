package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.dto.UserFormDto;
import com.t3h.uniqlo.dto.UserListItemDto;
import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.mapper.UserMapper;
import com.t3h.uniqlo.repository.RoleRepository;
import com.t3h.uniqlo.repository.UserRepository;
import com.t3h.uniqlo.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SessionFactory sessionFactory;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, SessionFactory sessionFactory) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserListItemDto> getUsers(String keyword, String role, int page, int size) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            List<UserListItemDto> result = userRepository.findAll(keyword, role, page, size)
                    .stream()
                    .map(UserMapper::toListItemDto)
                    .collect(Collectors.toList());
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public long countUsers(String keyword, String role) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            long result = userRepository.countAll(keyword, role);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public UserFormDto getUserForm(Integer id) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
            UserFormDto result = UserMapper.toFormDto(user);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void createUser(UserFormDto dto) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            User user = new User();
            UserMapper.copyFormToEntity(dto, user);

            if (user.getEmail() == null || user.getEmail().isBlank()) {
                throw new IllegalArgumentException("Email is required");
            }
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new IllegalArgumentException("Email already exists");
            });
            if (dto.getPassword() == null || dto.getPassword().isBlank()) {
                throw new IllegalArgumentException("Password is required");
            }

            user.setPasswordHash(dto.getPassword());
            user.setDeleted((byte) 0);

            applyRoleFromDto(user, dto.getRole());

            userRepository.save(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void updateUser(Integer id, UserFormDto dto) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            User existing = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

            UserMapper.copyFormToEntity(dto, existing);

            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                existing.setPasswordHash(dto.getPassword());
            }

            applyRoleFromDto(existing, dto.getRole());

            userRepository.save(existing);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    private void applyRoleFromDto(User user, String roleName) {
        String normalized = (roleName == null || roleName.isBlank()) ? "USER" : roleName.trim().toUpperCase();

        Role role = roleRepository.findByName(normalized)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(normalized);
                    r.setDeleted((byte) 0);
                    return roleRepository.save(r);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    public void deleteUser(Integer id) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            int updated = userRepository.softDeleteById(id);
            if (updated == 0) {
                throw new IllegalArgumentException("User not found: " + id);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
}
