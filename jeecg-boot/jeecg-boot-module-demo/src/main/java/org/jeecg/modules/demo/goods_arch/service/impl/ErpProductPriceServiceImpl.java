package org.jeecg.modules.demo.goods_arch.service.impl;

import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import org.jeecg.modules.demo.goods_arch.mapper.ErpProductPriceMapper;
import org.jeecg.modules.demo.goods_arch.service.IErpProductPriceService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 货物价格表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Service
public class ErpProductPriceServiceImpl extends ServiceImpl<ErpProductPriceMapper, ErpProductPrice> implements IErpProductPriceService {
	
	@Autowired
	private ErpProductPriceMapper erpProductPriceMapper;
	
	@Override
	public List<ErpProductPrice> selectByMainId(String mainId) {
		return erpProductPriceMapper.selectByMainId(mainId);
	}
}
