package com.cskaoyan.service.impl;

import com.cskaoyan.dao.MaterialMapper;
import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.MaterialService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service
public class MaterialServiceImpl implements MaterialService{

    /**
     * 自动注入持久层Dao对象
     */
    @Autowired
    private MaterialMapper materialMapper;

    private static Logger logger = LogManager.getLogger(MaterialServiceImpl.class);

    @Override
    public PageBean<Material> findList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<Material> materials = materialMapper.queryAll();
        logger.info("findList---------" + materials);
        PageBean<Material> pageBean = new PageBean<>(materials);

        return pageBean;
    }

    @Override
    public PageBean<Material> findListByMaterialId(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<Material> materials = materialMapper.selectById(searchValue);
        logger.info("findListByMaterialId --- materials:" + materials);
        PageBean<Material> pageBean = new PageBean<>(materials);
        return pageBean;
    }

    @Override
    public PageBean<Material> findListByMaterialType(String searchValue, Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);

        //%实现模糊查询
        searchValue = "%" + searchValue + "%";
        List<Material> materials = materialMapper.selectByType(searchValue);
        logger.info("findListByMaterialType --- materials:" + materials);
        PageBean<Material> pageBean = new PageBean<>(materials);
        return pageBean;
    }

    @Override
    public Material findById(String materialId) {
        return materialMapper.selectByPrimaryKey(materialId);
    }

    @Override
    public List<Material> findAllList() {
        return materialMapper.queryAll();
    }

    @Override
    public int insert(Material material) {
        return materialMapper.insert(material);
    }

    @Override
    public int update(Material material) {
        return materialMapper.updateByPrimaryKey(material);
    }

    @Override
    public void delete(String materialId) {
        materialMapper.deleteByPrimaryKey(materialId);
    }

}
