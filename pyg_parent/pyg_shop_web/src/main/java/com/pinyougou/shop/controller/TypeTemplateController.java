package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.manager.service.TypeTemplateService;
import com.pinyougou.pojo.TbTypeTemplate;
import entity.PageResult;
import entity.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;


	@RequestMapping("/findAll")
	public ResultBean findAll(){
		List<Map> typeTemplateMap = typeTemplateService.findAll();

		return new ResultBean<>(typeTemplateMap);
	}

	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public ResultBean add(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.add(typeTemplate);
			return new ResultBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/update")
	public ResultBean update(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
			return new ResultBean(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne/{id}")
	public ResultBean findOne(@PathVariable Long id){
		TbTypeTemplate typeTemplate = typeTemplateService.findOne(id);
		return new ResultBean<>(typeTemplate);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete/{ids}")
	public ResultBean delete(@PathVariable Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new ResultBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search/{page}/{rows}")
	public PageResult search(@RequestBody TbTypeTemplate typeTemplate,
							 @PathVariable int page,
							 @PathVariable int rows){
		return typeTemplateService.findPage(typeTemplate, page, rows);		
	}

	@RequestMapping("findSpecList/{typeId}")
	public ResultBean findSpecList (@PathVariable Long typeId){
		List<Map> specList = typeTemplateService.findSpecList(typeId);
		return new ResultBean<>(specList);
	}
	
}
