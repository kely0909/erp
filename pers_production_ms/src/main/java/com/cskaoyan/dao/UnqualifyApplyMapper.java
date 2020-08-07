package com.cskaoyan.dao;

import com.cskaoyan.domain.UnqualifyApply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UnqualifyApplyMapper {
    int deleteByPrimaryKey(String unqualifyApplyId);

    int insert(UnqualifyApply record);

    int insertSelective(UnqualifyApply record);

    UnqualifyApply selectByPrimaryKey(String unqualifyApplyId);

    int updateByPrimaryKeySelective(UnqualifyApply record);

    int updateByPrimaryKey(UnqualifyApply record);

    List<UnqualifyApply> selectAll();

    //List<UnqualifyApply> selectUnqualifyApplysByPrimaryKey(String s);
    //List<UnqualifyApply> selectUnqualifyApplysByPrimaryKey(Map s);

    List<UnqualifyApply> selectUnqualifyApplysByPrimaryKeyId(HashMap<String, String> map);

    List<UnqualifyApply> selectUnqualifyApplysByPrimaryKeyName(HashMap<String, String> map);

    int deleteByIds(String[] ids);

    int updateById(UnqualifyApply unqualifyApply);

    //String getEmpNameById();
}
