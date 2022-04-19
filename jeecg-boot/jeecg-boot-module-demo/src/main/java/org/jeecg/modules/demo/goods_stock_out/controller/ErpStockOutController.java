package org.jeecg.modules.demo.goods_stock_out.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.goods_stock_out.entity.ErpStockOut;
import org.jeecg.modules.demo.goods_stock_out.service.IErpStockOutService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 货物出库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Api(tags="货物出库表")
@RestController
@RequestMapping("/goods_stock_out/erpStockOut")
@Slf4j
public class ErpStockOutController extends JeecgController<ErpStockOut, IErpStockOutService> {
	@Autowired
	private IErpStockOutService erpStockOutService;
	
	/**
	 * 分页列表查询
	 *
	 * @param erpStockOut
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "货物出库表-分页列表查询")
	@ApiOperation(value="货物出库表-分页列表查询", notes="货物出库表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ErpStockOut>> queryPageList(ErpStockOut erpStockOut,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpStockOut> queryWrapper = QueryGenerator.initQueryWrapper(erpStockOut, req.getParameterMap());
		Page<ErpStockOut> page = new Page<ErpStockOut>(pageNo, pageSize);
		IPage<ErpStockOut> pageList = erpStockOutService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param erpStockOut
	 * @return
	 */
	@AutoLog(value = "货物出库表-添加")
	@ApiOperation(value="货物出库表-添加", notes="货物出库表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ErpStockOut erpStockOut) {
		erpStockOutService.save(erpStockOut);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param erpStockOut
	 * @return
	 */
	@AutoLog(value = "货物出库表-编辑")
	@ApiOperation(value="货物出库表-编辑", notes="货物出库表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ErpStockOut erpStockOut) {
		erpStockOutService.updateById(erpStockOut);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货物出库表-通过id删除")
	@ApiOperation(value="货物出库表-通过id删除", notes="货物出库表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		erpStockOutService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "货物出库表-批量删除")
	@ApiOperation(value="货物出库表-批量删除", notes="货物出库表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpStockOutService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "货物出库表-通过id查询")
	@ApiOperation(value="货物出库表-通过id查询", notes="货物出库表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ErpStockOut> queryById(@RequestParam(name="id",required=true) String id) {
		ErpStockOut erpStockOut = erpStockOutService.getById(id);
		if(erpStockOut==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(erpStockOut);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param erpStockOut
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpStockOut erpStockOut) {
        return super.exportXls(request, erpStockOut, ErpStockOut.class, "货物出库表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ErpStockOut.class);
    }

	 /**
	  *   获取流水号
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "货物出库表-获取流水号")
	 @ApiOperation(value="货物出库表-获取流水号", notes="货物出库表-获取流水号")
	 @GetMapping(value = "/getSerialNo")
	 public Result<String> getSerialNo() {
		 return Result.OK(erpStockOutService.getSerialNo());
	 }
}
