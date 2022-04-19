package org.jeecg.modules.demo.goods_stock_in.service;

import org.jeecg.modules.demo.goods_stock_in.entity.ErpStockIn;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 货物入库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface IErpStockInService extends IService<ErpStockIn> {
    String getSerialNo();
}
