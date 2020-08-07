package com.cskaoyan.service;

import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.PageBean;

import java.util.List;


public interface MaterialService {

    int insert(Material material);

    int update(Material material);

    void delete(String materialId);

    PageBean<Material> findList(Integer page, Integer rows);

    PageBean<Material> findListByMaterialId(String searchValue, Integer page, Integer rows);

    PageBean<Material> findListByMaterialType(String searchValue, Integer page, Integer rows);

    Material findById(String materialId);

    List<Material> findAllList();
}
