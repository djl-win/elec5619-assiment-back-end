package com.group50.repository;

import com.group50.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

    /**
     * method name must match fields of entities
     * @param username 用户名
     * @param password 密码
     * @return 用户详细信息
     */
    Admin findByAdminUsernameAndAdminPassword(String username,String password);

    /**
     * 查询管理员id通过管理员的peopleId
     * @param id 管理员的peopleId
     * @return 管理员的id
     */
    Admin findAdminByAdminPeopleIdEquals(int id);

    /**
     * 通过管理员用户名查询
     * @param username 用户名
     * @return 查询的结果
     */
    Admin findAdminByAdminUsernameEquals(String username);
}
