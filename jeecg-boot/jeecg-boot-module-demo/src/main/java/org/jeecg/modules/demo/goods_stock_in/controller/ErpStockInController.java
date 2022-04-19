package org.jeecg.modules.demo.goods_stock_in.controller;

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
import org.jeecg.modules.demo.goods_stock_in.entity.ErpStockIn;
import org.jeecg.modules.demo.goods_stock_in.service.IErpStockInService;

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
 * @Description: 货物入库表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Api(tags="货物入库表")
@RestController
@RequestMapping("/goods_stock_in/erpStockIn")
@Slf4j
public class ErpStockInController extends JeecgController<ErpStockIn, IErpStockInService> {
	@Autowired
	private IErpStockInService erpStockInService;
	
	/**
	 * 分页列表查询
	 *
	 * @param erpStockIn
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "货物入库表-分页列表查询")
	@ApiOperation(value="货物入库表-分页列表查询", notes="货物入库表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ErpStockIn>> queryPageList(ErpStockIn erpStockIn,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpStockIn> queryWrapper = QueryGenerator.initQueryWrapper(erpStockIn, req.getParameterMap());
		Page<ErpStockIn> page = new Page<ErpStockIn>(pageNo, pageSize);
		IPage<ErpStockIn> pageList = erpStockInService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  *   获取流水号
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "货物入库表-获取流水号")
	 @ApiOperation(value="货物入库表-获取流水号", notes="货物入库表-获取流水号")
	 @GetMapping(value = "/getSerialNo")
	 public Result<String> getSerialNo() {
		 return Result.OK(erpStockInService.getSerialNo());
	 }
	
	/**
	 *   添加
	 *
	 * @param erpStockIn
	 * @return
	 */
	@AutoLog(value = "货物入库表-添加")
	@ApiOperation(value="货物入库表-添加", notes="货物入库表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ErpStockIn erpStockIn) {
		erpStockInService.save(erpStockIn);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param erpStockIn
	 * @return
	 */
	@AutoLog(value = "货物入库表-编辑")
	@ApiOperation(value="货物入库表-编辑", notes="货物入库表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ErpStockIn erpStockIn) {
		erpStockInService.updateById(erpStockIn);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货物入库表-通过id删除")
	@ApiOperation(value="货物入库表-通过id删除", notes="货物入库表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		erpStockInService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "货物入库表-批量删除")
	@ApiOperation(value="货物入库表-批量删除", notes="货物入库表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpStockInService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "货物入库表-通过id查询")
	@ApiOperation(value="货物入库表-通过id查询", notes="货物入库表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ErpStockIn> queryById(@RequestParam(name="id",required=true) String id) {
		ErpStockIn erpStockIn = erpStockInService.getById(id);
		if(erpStockIn==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(erpStockIn);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param erpStockIn
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpStockIn erpStockIn) {
        return super.exportXls(request, erpStockIn, ErpStockIn.class, "货物入库表");
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
        return super.importExcel(request, response, ErpStockIn.class);
    }

}
