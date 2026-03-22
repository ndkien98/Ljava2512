package com.t3h.uniqlo.dao;

import java.util.List;

public class VisitCountDao extends BaseDao<Long> {

    public long getCount() {
        String sql = "SELECT visit_count AS total FROM visit_stats ORDER BY id LIMIT 1";
        // Reuse count() but we need a long - use query directly
        List<Long> result = query(sql, rs -> rs.getLong("total"));
        return result.isEmpty() ? 0L : result.get(0);
    }

    public void increment() {
        String sql = "UPDATE visit_stats SET visit_count = visit_count + 1 ORDER BY id LIMIT 1";
        update(sql);
    }
}
