package org.jeecg.modules.demo.goods_stock_in.entity;

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
 * @Description: 货物入库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Data
@TableName("erp_stock_in")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="erp_stock_in对象", description="货物入库表")
public class ErpStockIn implements Serializable {
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
	/**入库编号*/
	@Excel(name = "入库编号", width = 15)
    @ApiModelProperty(value = "入库编号")
    private java.lang.String code;
	/**供应商*/
	@Excel(name = "供应商", width = 15, dicCode = "vendor")
	@Dict(dicCode = "vendor")
    @ApiModelProperty(value = "供应商")
    private java.lang.String vendor;
	/**货物编码*/
	@Excel(name = "货物编码", width = 15)
    @ApiModelProperty(value = "货物编码")
    private java.lang.String matCode;
	/**入库数量*/
	@Excel(name = "入库数量", width = 15)
    @ApiModelProperty(value = "入库数量")
    private java.lang.Double qty;
	/**入货仓库*/
	@Excel(name = "入货仓库", width = 15, dicCode = "warehouse")
	@Dict(dicCode = "warehouse")
    @ApiModelProperty(value = "入货仓库")
    private java.lang.String stock;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
