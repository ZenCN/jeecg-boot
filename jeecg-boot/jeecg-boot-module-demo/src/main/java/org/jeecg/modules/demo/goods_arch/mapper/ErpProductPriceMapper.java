package org.jeecg.modules.demo.goods_arch.mapper;

import java.util.List;
import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 货物价格表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface ErpProductPriceMapper extends BaseMapper<ErpProductPrice> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ErpProductPrice> selectByMainId(@Param("mainId") String mainId);
}
