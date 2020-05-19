package com.lpq.mail.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeMessage {
    /**
     * description: 成功时默认返回0
     * @author: Wei Yuyang
     * @time: 2020.01.29
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * description: 字段校验错误默认返回1
     * @author: Wei Yuyang
     * @time: 2020.01.30
     */
    public static final int CODE_PARAM_ERROR = 1;
    public static final CodeMessage VERIFICATION_CODE_ERROR = new CodeMessage(2,"验证码错误");
    public static final CodeMessage USERNAME_DUPLICATE = new CodeMessage(3,"用户名重复");
    public static final CodeMessage ERROR_USERNAME_PASSWORD = new CodeMessage(4,"用户名或密码错误");
    public static final CodeMessage USER_NOT_EXIST = new CodeMessage(5,"该用户不存在");
    public static final CodeMessage INTERNAL_SERVER_ERROR = new CodeMessage(500,"服务器内部错误");
    public static final CodeMessage GENERATE_CAPTCHA_FAIL = new CodeMessage(1001,"生成验证码失败");
    public static final CodeMessage CLOSE_IMAGE_OUTPUT_STREAM_FAIL = new CodeMessage(1002,"关闭图像流失败");

    public static final CodeMessage BLOG_ADD_ERROR = new CodeMessage(2001, "添加博客失败");
    public static final CodeMessage BLOG_DELETE_ERROR = new CodeMessage(2005, "删除博客失败");
    public static final CodeMessage BLOG_EDIT_ERROR = new CodeMessage(2010, "编辑博客失败");
    public static final CodeMessage BLOG_NOT_AUTHENTICATED = new CodeMessage(2015, "没有权限进行此操作");
    public static final CodeMessage BLOG_SEARCH_ERROR = new CodeMessage(2020, "博客搜索失败");
    public static final CodeMessage BLOG_GET_MESSAGE_ERROR = new CodeMessage(2025, "获取博客消息失败");
    public static final CodeMessage BLOG_READ_MESSAGE_ERROR = new CodeMessage(2030, "已读博客消息失败");
    public static final CodeMessage BLOG_GET_MESSAGE_NUM_ERROR = new CodeMessage(2035, "获取博客消息数量失败");
    public static final CodeMessage BLOG_DELETE_COMMENT_ERROR = new CodeMessage(2040, "删除评论失败");
    public static final CodeMessage BLOG_POST_COMMENT_ERROR = new CodeMessage(2045, "发表评论失败");

    public static final CodeMessage BLOG_ARTICLE_NOT_EXIST = new CodeMessage(2011, "操作失败:文章不存在");
    public static final CodeMessage BLOG_ARTICLE_IS_DELETED = new CodeMessage(2012, "操作失败:文章已删除");
    public static final CodeMessage BLOG_ARTICLE_NOT_AUTHOR = new CodeMessage(2013, "操作失败:当前用户非该文章作者");
    public static final CodeMessage BLOG_ARTICLE_AUTHOR_NOT_EXSIT = new CodeMessage(2014, "操作失败:该文章作者不存在");

    public static final CodeMessage BLOG_PAGENUM_ERROR = new CodeMessage(2016, "pageNum错误");

    public static final CodeMessage BLOG_MESSAGE_NOT_EXIST = new CodeMessage(2026, "消息不存在");


    private int code;
    private String message;
}
