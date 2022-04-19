package org.jeecg.modules.demo.goods_arch.service;

import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 货物价格表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface IErpProductPriceService extends IService<ErpProductPrice> {

	public List<ErpProductPrice> selectByMainId(String mainId);
}
