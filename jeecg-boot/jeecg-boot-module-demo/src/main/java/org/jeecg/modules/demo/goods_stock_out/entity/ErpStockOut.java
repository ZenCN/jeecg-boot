package org.jeecg.modules.demo.goods_stock_out.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 货物出库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Data
@TableName("erp_stock_out")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="erp_stock_out对象", description="货物出库表")
public class ErpStockOut implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**出库编码*/
	@Excel(name = "出库编码", width = 15)
    @ApiModelProperty(value = "出库编码")
    private java.lang.String code;
	/**客户*/
	@Excel(name = "客户", width = 15, dicCode = "customer")
	@Dict(dicCode = "customer")
    @ApiModelProperty(value = "客户")
    private java.lang.String customer;
	/**货物编码*/
	@Excel(name = "货物编码", width = 15)
    @ApiModelProperty(value = "货物编码")
    private java.lang.String matCode;
	/**出库数量*/
	@Excel(name = "出库数量", width = 15)
    @ApiModelProperty(value = "出库数量")
    private java.lang.Double qty;
	/**出货仓库*/
	@Excel(name = "出货仓库", width = 15, dicCode = "warehouse")
	@Dict(dicCode = "warehouse")
    @ApiModelProperty(value = "出货仓库")
    private java.lang.String stock;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
