package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    Role save(Role role);
}

