package com.lpq.mail.dao;

import com.lpq.mail.entity.ManagerInfo;
import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.entity.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    ManagerInfo selectManager(String username);
}