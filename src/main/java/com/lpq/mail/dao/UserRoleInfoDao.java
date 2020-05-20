package com.lpq.mail.dao;

import com.lpq.mail.entity.UserRoleInfo;
import com.lpq.mail.entity.UserRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleInfoDao {
    long countByExample(UserRoleInfoExample example);

    int deleteByExample(UserRoleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    List<UserRoleInfo> selectByExample(UserRoleInfoExample example);

    UserRoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    int updateByExample(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    int updateByPrimaryKeySelective(UserRoleInfo record);

    int updateByPrimaryKey(UserRoleInfo record);
}