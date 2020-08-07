package com.cskaoyan.service;

import com.cskaoyan.domain.MaterialConsume;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Work;

import java.util.List;

public interface MaterialConsuemeService {

    PageBean<MaterialConsume> findList(Integer page, Integer rows);

    Work findWorkById(String workId);

    Integer insert(MaterialConsume materialConsume);

    Integer update(MaterialConsume materialConsume);

    void delete(String materialConsumeId);

    PageBean<MaterialConsume> findListByConsumeId(String searchValue, Integer page, Integer rows);

    PageBean<MaterialConsume> findListByWorkId(String searchValue, Integer page, Integer rows);

    PageBean<MaterialConsume> findListByMaterialId(String searchValue, Integer page, Integer rows);

    //--------------------------------------------
    //返回work类型，应在workservice
    List<Work> findAllWorks();



}
