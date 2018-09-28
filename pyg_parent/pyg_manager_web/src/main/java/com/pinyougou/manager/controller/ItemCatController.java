package com.pinyougou.manager.controller;

import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.manager.service.ItemCatService;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;


	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage/{page}/{rows}/{id}")
	public PageResult  findPage(@PathVariable int page,
								@PathVariable int rows,
								@PathVariable long id){
		return itemCatService.findPage(page, rows, id);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public ResultBean add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new ResultBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public ResultBean update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
		TbItemCat itemCat = itemCatService.findOne(id);
		return new ResultBean<>(itemCat);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete/{ids}")
	public ResultBean delete(@PathVariable Long [] ids){
		try {
			itemCatService.delete(ids);
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
	@RequestMapping("/search/{page}/{rows}/{id}")
	public PageResult search(@RequestBody TbItemCat itemCat,
							 @PathVariable int page,
							 @PathVariable int rows,
							 @PathVariable long id ){
		return itemCatService.findPage(itemCat, page, rows, id);
	}
	
}
