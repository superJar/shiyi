package generate;

import generate.SysMember;

public interface SysMemberDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMember record);

    int insertSelective(SysMember record);

    SysMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMember record);

    int updateByPrimaryKey(SysMember record);
}