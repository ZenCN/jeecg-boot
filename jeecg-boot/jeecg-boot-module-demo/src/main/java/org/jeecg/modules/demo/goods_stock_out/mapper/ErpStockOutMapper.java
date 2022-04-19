package org.jeecg.modules.demo.goods_stock_out.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.goods_stock_out.entity.ErpStockOut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 货物出库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface ErpStockOutMapper extends BaseMapper<ErpStockOut> {
    public String getSerialNo();
}
