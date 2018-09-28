package com.pinyougou.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.manager.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author mayday
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult<TbSpecification> searchSpecificationList(int pageNum, int pageSize, TbSpecification spec) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isEmpty(spec.getSpecName())) {
            List<TbSpecification> specificationList = specMapper.selectByExample(null);
            return new PageResult<>(specificationList);
        }

        TbSpecificationExample specExample = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = specExample.createCriteria();
        criteria.andSpecNameLike("%" + spec.getSpecName() + "%");
        List<TbSpecification> tbSpecificationList = specMapper.selectByExample(specExample);


        return new PageResult<>(tbSpecificationList);
    }

    @Override
    public List<TbSpecificationOption> findSpecificationById(long id) {

        TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);

        return specificationOptionMapper.selectByExample(specificationOptionExample);
    }

    @Override
    public void saveSpecification(TbSpecification specification) {
        if (specification != null) {
            specMapper.insertSelective(specification);
            saveSpecOptionListBySpecId(specification);
        }
    }

    @Override
    public void deleteSpecification(Long[] ids) {

        List<Long> longs = Arrays.asList(ids);

        deleteSpecListById(longs);
        deleteSpecOptionListBySpecId(longs);

    }



    @Override
    public void updateSpecification(TbSpecification specification) {

        TbSpecificationExample specExample = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = specExample.createCriteria();
        criteria.andIdEqualTo(specification.getId());
        specMapper.updateByExampleSelective(specification, specExample);

        TbSpecificationOptionExample sepcOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria specOptionCriteria = sepcOptionExample.createCriteria();
        specOptionCriteria.andSpecIdEqualTo(specification.getId());
        specificationOptionMapper.deleteByExample(sepcOptionExample);



        saveSpecOptionListBySpecId(specification);
    }

    @Override
    public List<Map> findAll() {
        return specMapper.findAll();
    }


    /**
     * 根据 ids 删除多个规格
     * @param ids
     */
    private void deleteSpecListById(List<Long> ids) {
        TbSpecificationExample specExample = new TbSpecificationExample();
        TbSpecificationExample.Criteria specCriteria = specExample.createCriteria();
        specCriteria.andIdIn(ids);
        specMapper.deleteByExample(specExample);
    }

    /**
     * 根据 ids 删除多个规格选项
     * @param ids
     */
    private void deleteSpecOptionListBySpecId(List<Long> ids) {
        TbSpecificationOptionExample sepcOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria sepcOptionCriteria = sepcOptionExample.createCriteria();
        sepcOptionCriteria.andSpecIdIn(ids);
        specificationOptionMapper.deleteByExample(sepcOptionExample);
    }

    /**
     * 添加一个规格的所有规格选项
     * @param specification
     */
    private void saveSpecOptionListBySpecId(TbSpecification specification) {
        if (specification.getSpecOptionList() != null && specification.getSpecOptionList().size() > 0) {
            for (TbSpecificationOption specOption : specification.getSpecOptionList()) {
                specOption.setSpecId(specification.getId());

                specificationOptionMapper.insertSelective(specOption);
            }
        }
    }
}
