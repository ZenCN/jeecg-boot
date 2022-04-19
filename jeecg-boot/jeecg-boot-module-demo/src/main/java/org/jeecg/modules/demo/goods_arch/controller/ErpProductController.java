package org.jeecg.modules.demo.goods_arch.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.goods_arch.entity.ErpProductPrice;
import org.jeecg.modules.demo.goods_arch.entity.ErpProduct;
import org.jeecg.modules.demo.goods_arch.vo.ErpProductPage;
import org.jeecg.modules.demo.goods_arch.service.IErpProductService;
import org.jeecg.modules.demo.goods_arch.service.IErpProductPriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 货物档案表
 * @Author: jeecg-boot
 * @Date:   2022-04-19
 * @Version: V1.0
 */
@Api(tags="货物档案表")
@RestController
@RequestMapping("/goods_arch/erpProduct")
@Slf4j
public class ErpProductController {
	@Autowired
	private IErpProductService erpProductService;
	@Autowired
	private IErpProductPriceService erpProductPriceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param erpProduct
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "货物档案表-分页列表查询")
	@ApiOperation(value="货物档案表-分页列表查询", notes="货物档案表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ErpProduct>> queryPageList(ErpProduct erpProduct,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpProduct> queryWrapper = QueryGenerator.initQueryWrapper(erpProduct, req.getParameterMap());
		Page<ErpProduct> page = new Page<ErpProduct>(pageNo, pageSize);
		IPage<ErpProduct> pageList = erpProductService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param erpProductPage
	 * @return
	 */
	@AutoLog(value = "货物档案表-添加")
	@ApiOperation(value="货物档案表-添加", notes="货物档案表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ErpProductPage erpProductPage) {
		ErpProduct erpProduct = new ErpProduct();
		BeanUtils.copyProperties(erpProductPage, erpProduct);
		erpProductService.saveMain(erpProduct, erpProductPage.getErpProductPriceList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param erpProductPage
	 * @return
	 */
	@AutoLog(value = "货物档案表-编辑")
	@ApiOperation(value="货物档案表-编辑", notes="货物档案表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ErpProductPage erpProductPage) {
		ErpProduct erpProduct = new ErpProduct();
		BeanUtils.copyProperties(erpProductPage, erpProduct);
		ErpProduct erpProductEntity = erpProductService.getById(erpProduct.getId());
		if(erpProductEntity==null) {
			return Result.error("未找到对应数据");
		}
		erpProductService.updateMain(erpProduct, erpProductPage.getErpProductPriceList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货物档案表-通过id删除")
	@ApiOperation(value="货物档案表-通过id删除", notes="货物档案表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		erpProductService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "货物档案表-批量删除")
	@ApiOperation(value="货物档案表-批量删除", notes="货物档案表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpProductService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "货物档案表-通过id查询")
	@ApiOperation(value="货物档案表-通过id查询", notes="货物档案表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ErpProduct> queryById(@RequestParam(name="id",required=true) String id) {
		ErpProduct erpProduct = erpProductService.getById(id);
		if(erpProduct==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(erpProduct);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "货物价格表通过主表ID查询")
	@ApiOperation(value="货物价格表主表ID查询", notes="货物价格表-通主表ID查询")
	@GetMapping(value = "/queryErpProductPriceByMainId")
	public Result<List<ErpProductPrice>> queryErpProductPriceListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ErpProductPrice> erpProductPriceList = erpProductPriceService.selectByMainId(id);
		return Result.OK(erpProductPriceList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param erpProduct
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpProduct erpProduct) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ErpProduct> queryWrapper = QueryGenerator.initQueryWrapper(erpProduct, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ErpProduct> queryList = erpProductService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ErpProduct> erpProductList = new ArrayList<ErpProduct>();
      if(oConvertUtils.isEmpty(selections)) {
          erpProductList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          erpProductList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ErpProductPage> pageList = new ArrayList<ErpProductPage>();
      for (ErpProduct main : erpProductList) {
          ErpProductPage vo = new ErpProductPage();
          BeanUtils.copyProperties(main, vo);
          List<ErpProductPrice> erpProductPriceList = erpProductPriceService.selectByMainId(main.getId());
          vo.setErpProductPriceList(erpProductPriceList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "货物档案表列表");
      mv.addObject(NormalExcelConstants.CLASS, ErpProductPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("货物档案表数据", "导出人:"+sysUser.getRealname(), "货物档案表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ErpProductPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ErpProductPage.class, params);
              for (ErpProductPage page : list) {
                  ErpProduct po = new ErpProduct();
                  BeanUtils.copyProperties(page, po);
                  erpProductService.saveMain(po, page.getErpProductPriceList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

	 /**
	  *   获取流水号
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "货物档案表-获取流水号")
	 @ApiOperation(value="货物档案表-获取流水号", notes="货物档案表-获取流水号")
	 @GetMapping(value = "/getSerialNo")
	 public Result<String> getSerialNo() {
		 return Result.OK(erpProductService.getSerialNo());
	 }

	 @GetMapping(value = "/queryMatCodeName")
	 public Result<List<String>> queryMatCodeName(@RequestParam(name = "keyWords", required = true) String keyWords){
		 return Result.OK(erpProductService.queryMatCodeName(keyWords));
	 }
}
