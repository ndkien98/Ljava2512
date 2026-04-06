package com.t3h.uniqlo.dao;

public interface IProductionDao {

    int countProductions(String keySearch, Integer colorId, Integer categoryId);
}
