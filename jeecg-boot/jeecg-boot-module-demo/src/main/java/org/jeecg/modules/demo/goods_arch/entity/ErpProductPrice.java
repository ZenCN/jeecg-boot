package org.jeecg.modules.demo.goods_arch.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 货物价格表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@ApiModel(value="erp_product_price对象", description="货物价格表")
@Data
@TableName("erp_product_price")
public class ErpProductPrice implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**进货价*/
	@Excel(name = "进货价", width = 15)
    @ApiModelProperty(value = "进货价")
    private java.lang.Double buyPrice;
	/**批发价*/
	@Excel(name = "批发价", width = 15)
    @ApiModelProperty(value = "批发价")
    private java.lang.Double tradePrice;
	/**样品价*/
	@Excel(name = "样品价", width = 15)
    @ApiModelProperty(value = "样品价")
    private java.lang.Double samplePrice;
	/**币种*/
	@Excel(name = "币种", width = 15, dicCode = "currency")
    @ApiModelProperty(value = "币种")
    private java.lang.String currency;
	/**外键*/
    @ApiModelProperty(value = "外键")
    private java.lang.String mainId;
}
