package org.jeecg.modules.demo.goods_stock_in.service.impl;

import org.jeecg.modules.demo.goods_stock_in.entity.ErpStockIn;
import org.jeecg.modules.demo.goods_stock_in.mapper.ErpStockInMapper;
import org.jeecg.modules.demo.goods_stock_in.service.IErpStockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 货物入库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Service
public class ErpStockInServiceImpl extends ServiceImpl<ErpStockInMapper, ErpStockIn> implements IErpStockInService {

    @Autowired
    private ErpStockInMapper erpStockInMapper;

    @Override
    public String getSerialNo(){
        return erpStockInMapper.getSerialNo();
    }
}
