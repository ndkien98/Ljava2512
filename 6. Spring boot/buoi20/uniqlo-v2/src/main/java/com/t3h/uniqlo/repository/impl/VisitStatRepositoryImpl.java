package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.VisitStat;
import com.t3h.uniqlo.repository.VisitStatRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VisitStatRepositoryImpl extends BaseRepositoryImpl<VisitStat, Integer> implements VisitStatRepository {
}
