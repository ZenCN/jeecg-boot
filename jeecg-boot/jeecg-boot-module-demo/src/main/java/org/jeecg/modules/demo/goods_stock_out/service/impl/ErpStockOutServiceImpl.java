package org.jeecg.modules.demo.goods_stock_out.service.impl;

import org.jeecg.modules.demo.goods_stock_in.mapper.ErpStockInMapper;
import org.jeecg.modules.demo.goods_stock_out.entity.ErpStockOut;
import org.jeecg.modules.demo.goods_stock_out.mapper.ErpStockOutMapper;
import org.jeecg.modules.demo.goods_stock_out.service.IErpStockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 货物出库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Service
public class ErpStockOutServiceImpl extends ServiceImpl<ErpStockOutMapper, ErpStockOut> implements IErpStockOutService {
    @Autowired
    private ErpStockOutMapper erpStockOutMapper;

    @Override
    public String getSerialNo(){
        return erpStockOutMapper.getSerialNo();
    }
}
