package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Size;
import com.t3h.uniqlo.repository.SizeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SizeRepositoryImpl extends BaseRepositoryImpl<Size, Integer> implements SizeRepository {
}
