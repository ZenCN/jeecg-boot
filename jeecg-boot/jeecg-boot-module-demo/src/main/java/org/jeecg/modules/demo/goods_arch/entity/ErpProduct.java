package org.jeecg.modules.demo.goods_arch.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

/**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@ApiModel(value="erp_product对象", description="货物档案表")
@Data
@TableName("erp_product")
public class ErpProduct implements Serializable {
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
    private transient java.lang.String imageString;

    private byte[] image;

    public byte[] getImage(){
        if(imageString==null){
            return null;
        }
        try {
            return imageString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getImageString(){
        if(image==null || image.length==0){
            return "";
        }
        try {
            return new String(image,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**商品描述*/
	@Excel(name = "商品描述", width = 15)
    @ApiModelProperty(value = "商品描述")
    private java.lang.String description;
}
