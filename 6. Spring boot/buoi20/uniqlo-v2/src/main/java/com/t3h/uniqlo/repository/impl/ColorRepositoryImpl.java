package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Color;
import com.t3h.uniqlo.repository.ColorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ColorRepositoryImpl extends BaseRepositoryImpl<Color, Integer> implements ColorRepository {
}
