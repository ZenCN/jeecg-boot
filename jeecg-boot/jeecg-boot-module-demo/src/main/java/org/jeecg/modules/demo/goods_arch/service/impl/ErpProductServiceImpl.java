package org.jeecg.modules.demo.goods_arch.service.impl;

import org.jeecg.modules.demo.goods_arch.entity.ErpProduct;
import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import org.jeecg.modules.demo.goods_arch.mapper.ErpProductPriceMapper;
import org.jeecg.modules.demo.goods_arch.mapper.ErpProductMapper;
import org.jeecg.modules.demo.goods_arch.service.IErpProductService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Service
public class ErpProductServiceImpl extends ServiceImpl<ErpProductMapper, ErpProduct> implements IErpProductService {

	@Autowired
	private ErpProductMapper erpProductMapper;
	@Autowired
	private ErpProductPriceMapper erpProductPriceMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ErpProduct erpProduct, List<ErpProductPrice> erpProductPriceList) {
		erpProductMapper.insert(erpProduct);
		if(erpProductPriceList!=null && erpProductPriceList.size()>0) {
			for(ErpProductPrice entity:erpProductPriceList) {
				//外键设置
				entity.setMainId(erpProduct.getId());
				erpProductPriceMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ErpProduct erpProduct,List<ErpProductPrice> erpProductPriceList) {
		erpProductMapper.updateById(erpProduct);
		
		//1.先删除子表数据
		erpProductPriceMapper.deleteByMainId(erpProduct.getId());
		
		//2.子表数据重新插入
		if(erpProductPriceList!=null && erpProductPriceList.size()>0) {
			for(ErpProductPrice entity:erpProductPriceList) {
				//外键设置
				entity.setMainId(erpProduct.getId());
				erpProductPriceMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		erpProductPriceMapper.deleteByMainId(id);
		erpProductMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			erpProductPriceMapper.deleteByMainId(id.toString());
			erpProductMapper.deleteById(id);
		}
	}

	@Override
	public String getSerialNo(){
		return erpProductMapper.getSerialNo();
	}

	@Override
	public List<String> queryMatCodeName(String keyWords){ return erpProductMapper.queryMatCodeName(keyWords); }
}
