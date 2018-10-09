package com.pinyougou.content.service;

import com.pinyougou.pojo.TbContent;
import entity.PageResult;
import entity.ResultBean;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ContentService {

	/**
	 * 增加
	*/
	public void add(TbContent content);
	
	
	/**
	 * 修改
	 */
	public void update(TbContent content);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public ResultBean findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbContent content, int pageNum, int pageSize);

	ResultBean findAllContentCategory();


    ResultBean findAll();

    ResultBean findAllContent(Long categoryId);
}
