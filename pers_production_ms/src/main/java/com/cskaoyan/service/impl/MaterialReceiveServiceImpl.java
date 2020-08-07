package com.cskaoyan.service.impl;

import com.cskaoyan.dao.MaterialReceiveMapper;
import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.MaterialReceive;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialReceiveServiceImpl implements MaterialReceiveService {

    @Autowired
    MaterialReceiveMapper materialReceiveMapper;

    private static Logger logger = LogManager.getLogger(MaterialReceiveServiceImpl.class);


    @Override
    public PageBean<MaterialReceive> findList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<MaterialReceive> materials = materialReceiveMapper.queryAll();
        logger.info("MaterialReceiveServiceImpl--findList---------" + materials);
        PageBean<MaterialReceive> pageBean = new PageBean<>(materials);
        return pageBean;
    }

    @Override
    public int insert(MaterialReceive materialReceive) {
        return materialReceiveMapper.insert(materialReceive);
    }

    @Override
    public int update(MaterialReceive materialReceive) {
        return materialReceiveMapper.updateByPrimaryKey(materialReceive);
    }

    @Override
    public void delete(String materialReceiveId) {
        materialReceiveMapper.deleteByPrimaryKey(materialReceiveId);
    }

    @Override
    public PageBean<MaterialReceive> findListByReceiveId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<MaterialReceive> materialReceives = materialReceiveMapper.selectByReceiveId(searchValue);
        logger.info("findListByReceiveId --- materialReceives:" + materialReceives);

        PageBean<MaterialReceive> pageBean = new PageBean<>(materialReceives);
        return pageBean;

    }

    @Override
    public PageBean<MaterialReceive> findListByMaterialId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);
        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<MaterialReceive> materialReceives = materialReceiveMapper.selectByMaterialId(searchValue);
        logger.info("findListByMaterialId --- materialReceives:" + materialReceives);

        PageBean<MaterialReceive> pageBean = new PageBean<>(materialReceives);
        return pageBean;
    }
}
