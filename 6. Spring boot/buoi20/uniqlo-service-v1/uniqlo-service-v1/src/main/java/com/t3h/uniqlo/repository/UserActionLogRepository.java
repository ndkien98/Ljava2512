package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.UserActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActionLogRepository extends JpaRepository<UserActionLog, Long> {
}
