package com.pinyougou.manager.controller;
import java.util.List;

import com.pinyougou.pojo.TbSeckillGoods;
import com.pinyougou.manager.service.SeckillGoodsService;
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
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

	@Reference
	private SeckillGoodsService seckillGoodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSeckillGoods> findAll(){
		return seckillGoodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return seckillGoodsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param seckillGoods
	 * @return
	 */
	@RequestMapping("/add")
	public ResultBean add(@RequestBody TbSeckillGoods seckillGoods){
		try {
			seckillGoodsService.add(seckillGoods);
			return new ResultBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seckillGoods
	 * @return
	 */
	@RequestMapping("/update")
	public ResultBean update(@RequestBody TbSeckillGoods seckillGoods){
		try {
			seckillGoodsService.update(seckillGoods);
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
	public TbSeckillGoods findOne(Long id){
		return seckillGoodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultBean delete(Long [] ids){
		try {
			seckillGoodsService.delete(ids);
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
	public PageResult search(@RequestBody TbSeckillGoods seckillGoods, int page, int rows  ){
		return seckillGoodsService.findPage(seckillGoods, page, rows);		
	}
	
}
