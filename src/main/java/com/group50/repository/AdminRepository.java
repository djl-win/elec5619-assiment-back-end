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
}
