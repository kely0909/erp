package com.cskaoyan.dao;

import com.cskaoyan.domain.Material;

import java.util.List;

public interface MaterialMapper {

    int deleteByPrimaryKey(String materialId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String materialId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> queryAll();

    List<Material> selectById(String searchValue);

    List<Material> selectByType(String searchValue);
}