package com.cskaoyan.service.impl;

import com.cskaoyan.dao.MaterialConsumeMapper;
import com.cskaoyan.dao.WorkMapper;
import com.cskaoyan.domain.MaterialConsume;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Work;
import com.cskaoyan.service.MaterialConsuemeService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialConsuemeServiceImpl implements MaterialConsuemeService{

    @Autowired
    private MaterialConsumeMapper materialConsumeMapper;

    @Autowired
    private WorkMapper workMapper;

    private Logger logger = LogManager.getLogger(MaterialConsuemeServiceImpl.class);

    @Override
    public PageBean<MaterialConsume> findList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);
        List<MaterialConsume> materials = materialConsumeMapper.queryAll();
        logger.info("MaterialConsuemeServiceImpl---findList---materials" + materials);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materials);
        return pageBean;
    }

    @Override
    public Work findWorkById(String workId) {
        return workMapper.selectByWorkId(workId);
    }

    @Override
    public List<Work> findAllWorks() {
        return workMapper.queryAll();
    }

    @Override
    public Integer insert(MaterialConsume materialConsume) {
        return materialConsumeMapper.insert(materialConsume);
    }

    @Override
    public Integer update(MaterialConsume materialConsume) {
        return materialConsumeMapper.updateByPrimaryKey(materialConsume);
    }

    @Override
    public void delete(String materialConsumeId) {
        materialConsumeMapper.deleteByPrimaryKey(materialConsumeId);
    }

    @Override
    public PageBean<MaterialConsume> findListByConsumeId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<MaterialConsume> materialConsumes = materialConsumeMapper.selectByConsumeId(searchValue);

        logger.info("findListByConsumeId --- materialConsumes:" + materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes);
        return pageBean;
    }

    @Override
    public PageBean<MaterialConsume> findListByWorkId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<MaterialConsume> materialConsumes = materialConsumeMapper.selectByWorkId(searchValue);

        logger.info("findListByWorkId --- materialConsumes:" + materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes);
        return pageBean;

    }

    @Override
    public PageBean<MaterialConsume> findListByMaterialId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<MaterialConsume> materialConsumes = materialConsumeMapper.selectByMaterialId(searchValue);

        logger.info("findListByMaterialId --- materialConsumes:" + materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes);
        return pageBean;
    }


}
