package com.pinyougou.manager.controller;
import java.util.List;

import com.pinyougou.pojo.TbContent;
import com.pinyougou.content.service.ContentService;
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
@RequestMapping("/content")
public class ContentController {

	@Reference
	private ContentService contentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAllContentCategory")
	public ResultBean findAllContentCategory(){
		return contentService.findAllContentCategory();
	}

	@RequestMapping("/findAll")
	public ResultBean findAll(){
		return contentService.findAll();
	}
	

	/**
	 * 增加
	 * @param content
	 * @return
	 */
	@RequestMapping("/add")
	public ResultBean add(@RequestBody TbContent content){
		try {
			contentService.add(content);
			return new ResultBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param content
	 * @return
	 */
	@RequestMapping("/update")
	public ResultBean update(@RequestBody TbContent content){
		try {
			contentService.update(content);
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
		return contentService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete/{ids}")
	public ResultBean delete(@PathVariable Long [] ids){
		try {
			contentService.delete(ids);
			return new ResultBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "删除失败");
		}
	}

	
}
