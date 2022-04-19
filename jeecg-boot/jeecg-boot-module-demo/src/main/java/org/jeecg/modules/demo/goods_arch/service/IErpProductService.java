package org.jeecg.modules.demo.goods_arch.service;

import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import org.jeecg.modules.demo.goods_arch.entity.ErpProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
public interface IErpProductService extends IService<ErpProduct> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ErpProduct erpProduct,List<ErpProductPrice> erpProductPriceList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ErpProduct erpProduct,List<ErpProductPrice> erpProductPriceList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	String getSerialNo();

	List<String> queryMatCodeName(String keyWords);
}
