package com.pinyougou.shop.controller;
import com.pinyougou.manager.service.SellerService;
import com.pinyougou.pojo.TbSeller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
			boolean isInsert = sellerService.add(seller);

			return new ResultBean<>(isInsert);
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
	@RequestMapping("findOne")
	public TbSeller findOne(String id){
		return sellerService.findOne(id);		
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
}
