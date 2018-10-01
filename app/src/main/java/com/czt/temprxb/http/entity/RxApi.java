package com.czt.temprxb.http.entity;


import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by {冯中萌} on 2017/6/6.
 */

public interface RxApi {
    /**
     * 22、登录：
     * ulogin
     * 传入：mobile  password  device_token
     */
//    @GET("ulogin/{mobile}/{password}/{device_token}")
//    Call<ResponseBody> ulogin(@Path("mobile") String mobile, @Path("password") String password
//            , @Path("device_token") String device_token);
//    @GET("ulogin")
//    Call<ResponseBody> ulogin(@QueryMap Map<String,String> params);
    @FormUrlEncoded
    @POST("ulogin")
    Call<ResponseBody> ulogin(@Url String s, @Header("token") String token, @Field("mobile")
            String mobile, @Field("password") String password
            , @Field("device_token") String device_token);


    /**
     * 1、首页banner图
     * http://120.27.118.19:902/index.php/api/user/index_banner/
     *
     * @return
     */
    @GET("index.php/api/user/index_banner/")
    Observable<JsonObject> index_banner();

    /**
     * 注册
     * url   |        http://120.27.118.19:902/index.php/api/user/reg
     * <p>
     * 参数         注释
     * mobile         手机号
     * password                密码
     * password2         确认密码
     * code                 验证码
     * time           请求接口时间
     * sign   签名
     */
    @FormUrlEncoded
    @POST("renxingbao/api/schoolFamily/list")
    Observable<JsonObject> register(@Field("page") String page, @Field("type") String type,
                                    @Field("userId") String userId, @Field("idType") String idType
            , @Field("crDate") String crDate, @Field("readStatus") String readStatus);
    @FormUrlEncoded
    @POST("renxingbao/api/schoolFamily/list")
    Observable<JsonObject> sendMessage(@Field("page") String page,@Field("userId")  String userId,@Field("crDate")  String crDate,@Field("type")  String type,@Field("idType") String idType);
    /**
     * 接口名称    3  |   发送验证码
     * url         |      http://120.27.118.19:902/index.php/api/mobileverify/send
     * <p>
     * 参数       |        注释
     * mobile     |    手机号
     */
    @FormUrlEncoded
    @POST("index.php/api/mobileverify/send")
    Observable<JsonObject> send(@Field("mobile") String mobile);

    /**
     * 接口名称  2      登录
     * url       http://120.27.118.19:902/index.php/api/user/login
     * 参数         注释
     * mobile       手机号
     * password      密码
     * device_num      设备号
     * j_du        经度
     * w_du       维度
     * time       请求接口时间
     */
    @FormUrlEncoded
    @POST("index.php/api/user/login")
    Observable<JsonObject> login(@Field("mobile") String mobile, @Field("password") String password,
                                 @Field("device_num") String device_num, @Field("j_du") String j_du,
                                 @Field("w_du") String w_du, @Field("time") String time,
                                 @Field("sign") String sign);


    /**
     * 接口名称     7
     * 获取首页两排地址信息     http://120.27.118.19:902/index.php/api/user/get_double_addr
     */
    @GET("index.php/api/user/get_double_addr")
    Observable<JsonObject> get_double_addr();

    /**
     * 接口名称   6
     * 获取字母排序地址    http://120.27.118.19:902/index.php/api/user/region_info
     */
    @GET("index.php/api/user/region_info")
    Observable<JsonObject> region_info();

    /**
     * 接口名称    10
     * 找工人--发布订单选择工种  http://120.27.118.19:902/index.php/api/user/work_type
     */
    @GET("index.php/api/user/work_type")
    Observable<JsonObject> work_type();

    /**
     * 接口名称        12
     * 找工人--发布订单选择风格   http://120.27.118.19:902/index.php/api/user/style
     */
    @GET("index.php/api/user/style")
    Observable<JsonObject> style();

    /**
     * 接口名称      8
     * 找工人--完善订单
     * http://120.27.118.19:902/index.php/api/user/release_order
     * province_id  工作省id ( 省市区id通过接口5传入中文地址获取，格式如接口5实例)
     * city_id       工作市id
     * district        工作县id
     * address    工作详细地址   //通过地图获取
     * start_time  工作开始时间（10位时间戳）
     * end_time  工作结束时间 （10位时间戳）
     * work_type       工作类型（传id）
     * people_num        工作人数
     * day_num            工作天数
     * style          风格      （有就传没有就不传  传入情况参考功能表）
     * accident_server      额外服务    （有就传没有就不传  传入情况参考功能表）
     * remarks              留言
     * money                总价
     * order_type           工作类型 //1（装修订单） 2 （建筑订单） 3 （安装订单） 4（团队订
     * j_du        经度      //详细地址经度
     * w_du               维度      //详细地址维度
     * time           请求接口时间
     * token            token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/release_order")
    Observable<JsonObject> release_order(@FieldMap Map<String, String> map);

    /**
     * 接口名称   5
     * 获取定位地址  http://120.27.118.19:902/index.php/api/user/gps_addr
     * name       北京市（一级） 北京 （二级） 通州区（三级）/前端定位获取的市ID
     */
    @FormUrlEncoded
    @POST("index.php/api/user/gps_addr")
    Observable<JsonObject> gps_addr(@FieldMap Map<String, String> map);

    /**
     * 接口名称     11
     * 找工人--发布订单选择额外服务
     * http://120.27.118.19:902/index.php/api/user/accident_server
     */
    @GET("index.php/api/user/accident_server")
    Observable<JsonObject> accident_server();

    /**
     * 接口名称      13
     * 找工作-- 订单列表展示
     * http://120.27.118.19:902/index.php/api/user/order_list
     * city_id           定位的城市
     * time              请求接口时间
     * token              token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/order_list")
    Observable<JsonObject> order_list(@FieldMap Map<String, String> map);


    /**
     * 接口名称    22
     * 我-- 获取用户基本信息
     * http://120.27.118.19:902/index.php/api/user/get_user_info
     * token      token串
     * time       请求接口时间
     */
    @FormUrlEncoded
    @POST("index.php/api/user/get_user_info")
    Observable<JsonObject> get_user_info(@FieldMap Map<String, String> map);

    /**
     * 接口名称    23     我-- 我的钱包
     * http://120.27.118.19:902/index.php/api/user/my_money
     * token                  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/my_money")
    Observable<JsonObject> my_money(@FieldMap Map<String, String> map);

    @GET()
    Observable<JsonObject> get(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST()
    Observable<JsonObject> post(@Url String url, @FieldMap Map<String, String> map);

    /**
     * 接口名称      39         找工作-- 抢单
     * http://120.27.118.19:902/index.php/api/user/confirm_order
     * id               订单id
     * token          token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/confirm_order")
    Observable<JsonObject> confirm_order(@FieldMap Map<String, String> map);

    /**
     * 接口名称       31     我-- 获取文章信息
     * url            http://120.27.118.19:902/index.php/api/user/get_news
     * type        1为最新公告  2服务协议   3平台奖惩   4学习园地   5评分说明
     */
    @GET("index.php/api/user/get_news")
    Observable<JsonObject> get_news(@QueryMap Map<String, String> map);

    /**
     * 接口名称     32  我-- 联系客服
     * url         http://120.27.118.19:902/index.php/api/user/contact_us
     */
    @GET("index.php/api/user/contact_us")
    Observable<JsonObject> contact_us();

    /**
     * 接口名称     33   我--反馈
     * url          http://120.27.118.19:902/index.php/api/user/feedback
     * token  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/feedback")
    Observable<JsonObject> feedback(@FieldMap Map<String, String> map);

    /**
     * 接口名称  27    我-- 获取用户银行卡列表
     * url          http://120.27.118.19:902/index.php/api/user/bank_list
     * token  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/bank_list")
    Observable<JsonObject> bank_list(@FieldMap Map<String, String> map);

    /**
     * 接口名称  24            我-- 绑定银行卡
     * url         http://120.27.118.19:902/index.php/api/user/bind_bank
     * token  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/bind_bank")
    Observable<JsonObject> bind_bank(@FieldMap Map<String, String> map);

    /**
     * 接口名称    25              我-- 获取银行卡类型
     * url         http://120.27.118.19:902/index.php/api/user/get_bink_type
     * token  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/get_bink_type")
    Observable<JsonObject> get_bink_type(@FieldMap Map<String, String> map);

    /**
     * 接口名称     26            我-- 设置默认卡号
     * url         http://120.27.118.19:902/index.php/api/user/set_default_bank
     * token  token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/set_default_bank")
    Observable<JsonObject> set_default_bank(@FieldMap Map<String, String> map);

    /**
     * 接口名称     28        我-- 提现申请
     * url         http://120.27.118.19:902/index.php/api/user/withdraw_cash
     * money            提现金额
     * bank_id          绑定银行id
     * time                请求接口时间
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/withdraw_cash")
    Observable<JsonObject> withdraw_cash(@FieldMap Map<String, String> map);
    /**
     * 接口名称      34     发单管理
     * url         http://120.27.118.19:902/index.php/api/user/send_order_manage
     * state       全部订单传0  //未完成订单传1  //已完成传2   //取消订单传3
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/send_order_manage")
    Observable<JsonObject> send_order_manage(@FieldMap Map<String, String> map);
    /**
     * 接口名称 35         待服务 --点击开始
     * url        http://120.27.118.19:902/index.php/api/user/work_start
     * id              订单id
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/work_start")
    Observable<JsonObject> work_start(@FieldMap Map<String, String> map);
    /**
     * 接口名称   36            进行中 -- 点击取消和终止
     * url         http://120.27.118.19:902/index.php/api/user/work_cancel
     * id              订单id
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/work_cancel")
    Observable<JsonObject> work_cancel(@FieldMap Map<String, String> map);
    /**
     * 接口名称        37       待完成--点击完成
     * url        http://120.27.118.19:902/index.php/api/user/finish_order
     * id              订单id
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/finish_order")
    Observable<JsonObject> finish_order(@FieldMap Map<String, String> map);
    /**
     * 接口名称         38            接单管理
     * url          http://120.27.118.19:902/index.php/api/user/accept_order_manage
     * state     //全部订单传0  //未完成订单传1  //已完成传2   //取消订单传3
     * token      token串
     */
    @FormUrlEncoded
    @POST("index.php/api/user/accept_order_manage")
    Observable<JsonObject> accept_order_manage(@FieldMap Map<String, String> map);

}
