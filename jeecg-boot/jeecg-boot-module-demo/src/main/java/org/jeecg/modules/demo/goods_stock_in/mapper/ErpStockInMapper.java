package org.jeecg.modules.demo.goods_stock_in.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.goods_stock_in.entity.ErpStockIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 货物入库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface ErpStockInMapper extends BaseMapper<ErpStockIn> {
    public String getSerialNo();
}
