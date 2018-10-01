package com.czt.temprxb.persenter;

import java.util.Map;

/**
 * Created by {冯中萌} on 2017/4/19.
 */

public interface IPresenter {
    /**
     * 主页碎片管理层
     */
    interface IHomeFragmentPrenester extends IPresenter {
        void getBanner(boolean isShowLoading);//获取banner 图片

        void getCityID(Map<String, String> map, boolean isShowLoading);//获取城市id
    }

    /**
     * 全部订单界面的 管理者
     */
    interface IAllOrderFragmentPrenester extends IPresenter {
        void getOrder(Map map, boolean isShowLoading);//获取订单

        void getMyJiedanOrder(Map map, boolean isShowLoading);//获取我的接单

        void loodOrder(Map map);//获取更多订单

        void loodMyjieOrder(Map map);//获取更多我的接单

        void workStart(Map map, int position);//点击开始

        void workCancel(Map map, int position);//点击终止 或者结束
    }

    /**
     * 地址界面的管理者
     */
    interface IAddressActivityPrenester extends IPresenter {
        void getAddressHot();//获取热门城市

        void getAddressCity();//获取城市列表
    }

    /**
     * 我的界面 管理者
     */
    interface IMyFragmentPrenester extends IPresenter {
        void getUserInfo(Map<String, String> map, boolean isShowLoading);//获取我的信息

        void getCustomerService();//获取客服信息

        void setfeedback(Map<String, String> map);//提交投诉建议
    }

    /**
     * 1为最新公告  2服务协议   3平台奖惩   4学习园地   5评分说明
     */
    interface IMyAppInfoActivityPresenter extends IPresenter {
        void getData(Map<String, String> map, boolean isShowLoading);//获取我的信息
    }

    /**
     * 感想界面 管理者
     */
    interface IFeelFragmentPrenester extends IPresenter {
        void getFeelData(boolean isShowLoading);//获取感想信息

        void loodMoreFeelData();//获取更多
    }

    /**
     * 发布感想界面 管理者
     */
    interface ISendFeelPrenester extends IPresenter {
        void subMitFeelData();//发布感想
    }

    /**
     * 找工作 列表
     */
    interface IJobPresenter extends IPresenter {
        void getJobData(boolean isShowLoading, Map<String, String> stringMap);//获取工作列表

        void loodMoreJobData(Map<String, String> stringMap);//获取更多工作列表

        void confirmOrder(Map<String, String> stringMap);//抢单
    }

    /**
     * 找工人 发布订单界面管理者
     */
    interface IDecorationWorkerPresenter extends IPresenter {
        void loodWorkType();//获取工种类型

        void loodWorkStyle();//获取工种风格

        void accidentServer();//获取额外服务

        void releaseOrders(Map<String, String> map);//发布订单

        void getCityId(Map<String, String> map);//获取城市id
    }

    /**
     * 订单详情
     */
    interface IOrderInfoPresenter extends IPresenter {

        void canleOrder(Map map);//终止订单

        void finishOrder(Map map);//点击完成订单
    }

    /**
     * 银行卡界面
     */
    interface IBankPresenter extends IPresenter {
        void getBankList(Map<String, String> map, boolean isShowLoading);//获取银行卡列表

        void setDefaultBank(Map<String, String> map);//   我-- 设置默认卡号
    }

    /**
     * 添加银行卡
     */
    interface IAddBankPresenter extends IPresenter {

        void getBankName(Map<String, String> map);//获取银行卡名字

        void bandBank(Map<String, String> map);//绑定银行卡
    }

    /**
     * 信息管理
     */
    interface IXinxiGuanliPresenter extends IPresenter {
        void getUserInfo(boolean isShowload, Map<String, String> map);//获取用户信息

        void getHobbyList(Map<String, String> map);//获取用户爱好列表

        void updata_info(Map<String, String> map);//修改用户信息

        void getWorkerType();//修改用户信息
    }

    /**
     * 我的钱包
     */
    interface IQianBaoPresenter extends IPresenter {
        void getMoney(Map<String, String> map, boolean isShowLoading);//获取我的余额

        void setWithdrawCash(Map<String, String> mapBankList, Map<String, String> mapTiXian);//申请提现
    }

    /**
     * 登陆界面
     */
    interface ILoginPresenter extends IPresenter {
        void login(String mobile, String password, String device_num, String j_du, String w_du);
    }

    /**
     * 注册界面
     */
    interface IRegisterPresenter extends IPresenter {
//        void sendCode(String phone);

        void registerUser(String phone, String pas, String pass, String code);
    }

    public interface IMySendPresenter {
        void getMySendMessage(String page,String userId,String startDateStr);
    }
}
