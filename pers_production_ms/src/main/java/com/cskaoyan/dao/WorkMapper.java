package com.cskaoyan.dao;

import com.cskaoyan.domain.Work;
import com.cskaoyan.domain.WorkExample;

import java.util.List;

public interface WorkMapper {
    long countByExample(WorkExample example);

    int deleteByPrimaryKey(String workId);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(String workId);

    int updateByPrimaryKeySelective(Work record);

    Work selectByWorkId(String workId);

    List<Work> queryAll();
}