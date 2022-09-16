package com.group50.service;

import com.group50.entity.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional(timeout = -1, rollbackFor = Exception.class)
public interface AdminService {
    String findAdmin(Admin admin);
}
