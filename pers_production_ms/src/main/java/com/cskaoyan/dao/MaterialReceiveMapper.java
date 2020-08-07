package com.cskaoyan.dao;

import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.MaterialReceive;

import java.util.List;

public interface MaterialReceiveMapper {
    int deleteByPrimaryKey(String receiveId);

    int insert(MaterialReceive record);

    int insertSelective(MaterialReceive record);

    MaterialReceive selectByPrimaryKey(String receiveId);

    int updateByPrimaryKeySelective(MaterialReceive record);

    int updateByPrimaryKey(MaterialReceive record);

    List<MaterialReceive> queryAll();

    List<MaterialReceive> selectByReceiveId(String searchValue);

    List<MaterialReceive> selectByMaterialId(String searchValue);

}