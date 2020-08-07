package com.cskaoyan.service;

import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.MaterialReceive;
import com.cskaoyan.domain.PageBean;

public interface MaterialReceiveService {

    PageBean<MaterialReceive> findList(Integer page, Integer rows);

    int insert(MaterialReceive materialReceive);

    int update(MaterialReceive materialReceive);

    void delete(String materialReceiveId);

    PageBean<MaterialReceive> findListByReceiveId(String searchValue, Integer page, Integer rows);

    PageBean<MaterialReceive> findListByMaterialId(String searchValue, Integer page, Integer rows);
}
