package org.jeecg.modules.demo.goods_arch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.goods_arch.entity.ErpProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface ErpProductMapper extends BaseMapper<ErpProduct> {
    public String getSerialNo();
    public List<String> queryMatCodeName(@Param("keyWords") String keyWords);
}
