package com.cskaoyan.dao;

import com.cskaoyan.domain.MaterialConsume;

import java.util.List;

public interface MaterialConsumeMapper {
    int deleteByPrimaryKey(String consumeId);

    int insert(MaterialConsume record);

    int insertSelective(MaterialConsume record);

    MaterialConsume selectByPrimaryKey(String consumeId);

    int updateByPrimaryKeySelective(MaterialConsume record);

    int updateByPrimaryKey(MaterialConsume record);

    List<MaterialConsume> queryAll();

    List<MaterialConsume> selectByConsumeId(String searchValue);

    List<MaterialConsume> selectByWorkId(String searchValue);

    List<MaterialConsume> selectByMaterialId(String searchValue);

}