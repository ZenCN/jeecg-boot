package org.jeecg.modules.demo.goods_arch.vo;

import java.util.List;
import org.jeecg.modules.demo.goods_arch.entity.ErpProduct;
import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Data
@ApiModel(value="erp_productPage对象", description="货物档案表")
public class ErpProductPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
	@ApiModelProperty(value = "商品编码")
    private java.lang.String code;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
	@ApiModelProperty(value = "商品名称")
    private java.lang.String name;
	/**商品类型*/
	@Excel(name = "商品类型", width = 15, dicCode = "product_class")
    @Dict(dicCode = "product_class")
	@ApiModelProperty(value = "商品类型")
    private java.lang.String type;
	/**重量*/
	@Excel(name = "重量", width = 15)
	@ApiModelProperty(value = "重量")
    private java.lang.Double weight;
	/**单位*/
	@Excel(name = "单位", width = 15, dicCode = "unit")
    @Dict(dicCode = "unit")
	@ApiModelProperty(value = "单位")
    private java.lang.String unit;
	/**商品图片*/
	@Excel(name = "商品图片", width = 15)
	@ApiModelProperty(value = "商品图片")
    private java.lang.String imageString;
	/**商品描述*/
	@Excel(name = "商品描述", width = 15)
	@ApiModelProperty(value = "商品描述")
    private java.lang.String description;

	@ExcelCollection(name="货物价格表")
	@ApiModelProperty(value = "货物价格表")
	private List<ErpProductPrice> erpProductPriceList;

}
