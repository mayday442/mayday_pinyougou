package com.pinyougou.manager.controller;
import java.util.List;

import com.pinyougou.pojo.TbCities;
import com.pinyougou.manager.service.CitiesService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;


import entity.PageResult;
import entity.ResultBean;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/cities")
public class CitiesController {

	@Reference
	private CitiesService citiesService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbCities> findAll(){
		return citiesService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return citiesService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param cities
	 * @return
	 */
	@RequestMapping("/add")
	public ResultBean add(@RequestBody TbCities cities){
		try {
			citiesService.add(cities);
			return new ResultBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param cities
	 * @return
	 */
	@RequestMapping("/update")
	public ResultBean update(@RequestBody TbCities cities){
		try {
			citiesService.update(cities);
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
	@RequestMapping("/findOne")
	public TbCities findOne(Long id){
		return citiesService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultBean delete(Long [] ids){
		try {
			citiesService.delete(ids);
			return new ResultBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbCities cities, int page, int rows  ){
		return citiesService.findPage(cities, page, rows);		
	}
	
}
