package com.czt.temprxb.entity;

/**
 * Created by {冯中萌} on 2017/4/19
 */

public class Bean {

    /**
     * 域名   http://120.27.118.19:902/index.php/api/user/
     */
    public static final String url = "http://api.szwyx.com/";
    /**
     * Base  URL
     */
    public static final String base_url = url + "index.php/api/user/";
    /**
     * 图片路径 前缀   http://120.27.118.19:902/data/upload/
     */
    public static final String image = "http://120.27.118.19:902/data/upload/";
    /**
     * 接口名称     30      我-- 获取爱好列表
     * url     http://120.27.118.19:902/index.php/api/user/get_hobby
     */
    public static final String get_hobby = "index.php/api/user/get_hobby";
    /**
     * 接口名称    22     我-- 获取用户基本信息
     * url    http://120.27.118.19:902/index.php/api/user/get_user_info
     * token     token串
     */
    public static final String get_user_info = "index.php/api/user/get_user_info";
    /**
     * 接口名称     29            我-- 完善用户个人资料
     * url    http://120.27.118.19:902/index.php/api/user/updata_info
     * head_pic                头像
     * nickname               昵称
     * user_name           姓名
     * tel                  电话
     * card_no               身份证号
     * type_of_work          工种id（多个以‘,’隔开）
     * hobby                   爱好id（多个以‘,’隔开）
     * card_pic                手持身份证
     * time                 请求接口时间
     * token                  token串
     */
    public static final String updata_info = "index.php/api/user/updata_info";
    /**
     * 接口名称        40            上传图片
     *      http://120.27.118.19:902/index.php/api/user/fileUpload
     */
    public static final String fileUpload = "http://120.27.118.19:902/index.php/api/user/fileUpload";


}
