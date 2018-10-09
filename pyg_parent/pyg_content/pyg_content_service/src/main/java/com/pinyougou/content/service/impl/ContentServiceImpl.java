package com.pinyougou.content.service.impl;
import java.util.Arrays;
import java.util.List;

import com.pinyougou.constant.CacheKey;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.mapper.TbContentCategoryMapper;
import com.pinyougou.pojo.TbContentCategory;
import entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;

import entity.PageResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 增加
	 */
	@Override
	public void add(TbContent content) {

		redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).delete(content.getCategoryId());
		contentMapper.insert(content);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbContent content){

		Long newCategoryId = content.getCategoryId();
		Long contentId = content.getId();
		TbContent tbContent = contentMapper.selectByPrimaryKey(contentId);
		Long oldCategoryId = tbContent.getCategoryId();

		if (newCategoryId != null && newCategoryId.equals(oldCategoryId)){
			redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).delete(oldCategoryId);
		}else {
			redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).delete(newCategoryId);
			redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).delete(oldCategoryId);
		}



		contentMapper.updateByPrimaryKey(content);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ResultBean findOne(Long id){
		TbContent tbContent = contentMapper.selectByPrimaryKey(id);
		return new ResultBean<>(tbContent);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {

		TbContentExample example = new TbContentExample();
		List<Long> longs = Arrays.asList(ids);
		example.createCriteria().andIdIn(longs);
		List<TbContent> tbContents = contentMapper.selectByExample(example);

		long categoryId = -1;

		for (TbContent tbContent : tbContents) {

			if (categoryId == tbContent.getCategoryId()){
				continue;
			}
			categoryId = tbContent.getCategoryId();
			redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).delete(categoryId);

		}

		for(Long id:ids){
			contentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbContent content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();

		if(content!=null){
						if(content.getTitle()!=null && content.getTitle().length()>0){
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getUrl()!=null && content.getUrl().length()>0){
				criteria.andUrlLike("%"+content.getUrl()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0){
				criteria.andPicLike("%"+content.getPic()+"%");
			}
			if(content.getStatus()!=null && content.getStatus().length()>0){
				criteria.andStatusLike("%"+content.getStatus()+"%");
			}

		}

		Page<TbContent> page= (Page<TbContent>)contentMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public ResultBean findAllContentCategory() {
		List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(null);

		return new ResultBean<>(categoryList);
	}

	@Override
	public ResultBean findAll() {

		List<TbContent> contentList = contentMapper.selectByExample(null);

		return new ResultBean<>(contentList);
	}

	@Override
	public ResultBean findAllContent(Long categoryId) {


		List<TbContent> tbContents = (List<TbContent>)redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).get(categoryId);

		if (tbContents == null) {
			TbContentExample example = new TbContentExample();
			example.createCriteria().andStatusEqualTo("1").andCategoryIdEqualTo(categoryId);
			example.setOrderByClause("sort_order");
			tbContents = contentMapper.selectByExample(example);

			redisTemplate.boundHashOps(CacheKey.CONTENT_CACHE_KEY).put(categoryId, tbContents);
		}else {
			System.out.println("根据缓存查询数据");
		}


		return new ResultBean<>(tbContents);
	}

}
