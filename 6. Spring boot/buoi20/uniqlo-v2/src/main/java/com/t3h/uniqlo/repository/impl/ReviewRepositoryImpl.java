package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Review;
import com.t3h.uniqlo.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl extends BaseRepositoryImpl<Review, Integer> implements ReviewRepository {
}
