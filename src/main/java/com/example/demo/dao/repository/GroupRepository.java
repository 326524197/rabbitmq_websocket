package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Group;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.GroupInfoDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    /**
     * 获取当前用户的群组
     */
    @Query(value = "select new com.example.demo.dto.GroupDTO(t2.id,t2.groupName,t2.avatar) from UserGroup t1,Group t2 " +
            "where t1.username=:username and t1.groupId=t2.id ")
    List<GroupDTO> getUserGroup(@Param("username")String username);

    /**
     * 获取当前群内成员
     * @param groupId
     */
    @Query("select new com.example.demo.dto.UserDTO(t2.username,t1.nickName,t2.avatar,t2.signature,t2.lineState) " +
            "from UserGroup t1,User t2 where t1.groupId=:groupId and t1.username=t2.username")
    List<UserDTO> getGroupUsers(@Param("groupId")Long groupId);

    /**
     * 查找群
     */
    @Query("select new com.example.demo.dto.GroupInfoDTO(id,groupName,avatar,groupDsc) from Group where " +
            "(groupName like :name or id like :name) and id not in(select groupId from UserGroup where username=:username)")
    List<GroupInfoDTO> searchGroup(@Param("username")String username,@Param("name")String name);
}