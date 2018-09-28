package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.manager.service.SellerService;
import com.pinyougou.pojo.TbSeller;
import entity.PageResult;
import entity.ResultBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("seller")
public class SellerController {

	@Reference
	private SellerService sellerService;

	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping("add")
	public ResultBean add(@RequestBody TbSeller seller){

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(seller.getPassword());
		seller.setPassword(password);

		try {
			sellerService.add(seller);
			return new ResultBean<>(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean<>(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("update")
	public ResultBean update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
			return new ResultBean<>(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean<>(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("findOne/{id}")
	public ResultBean findOne(@PathVariable String id){
		TbSeller seller = sellerService.findOne(id);

		return new ResultBean<>(seller);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	public ResultBean delete(Long [] ids){
		try {
			sellerService.delete(ids);
			return new ResultBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(false, "删除失败");
		}
	}

	@RequestMapping("search/{pageNum}/{pageSize}")
	public PageResult search(@PathVariable int pageNum,
							 @PathVariable int pageSize,
							 @RequestBody TbSeller seller){

		System.err.println(pageNum);
		System.err.println(pageSize);
		System.err.println(seller);

        return sellerService.findPage(seller, pageNum, pageSize);
	}
}
