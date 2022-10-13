package com.group50.service;

import com.group50.dto.CommentRecord;
import com.group50.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional(timeout = -1, rollbackFor = {Exception.class, CustomException.class})
public interface CommentService {
    /**
     * 查询所有的评论
     * @return 评论的集合
     */
    List<CommentRecord> searchAllComment();
}
