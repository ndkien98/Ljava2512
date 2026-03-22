package com.t3h.uniqlo.model.response;

import com.t3h.uniqlo.dao.BaseDao;
import com.t3h.uniqlo.model.dto.ProductionDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProductionResponse extends BaseResponse<List<ProductionDTO>> {

}
