package com.mhd.basekit.viewkit.util.wholeCommon;

/**
 * Created by 13660 on 2019/4/1.
 */

public class EventBusVariable {
    //eventbus类型变量
    public static final int EXIT = 10000;//退出操作
    public static final int LOGIN = 10001;//登陆判断,跳转事件继续
    public static final int IS_LOGIN = 10002;//用户登录

    public static final int EXPECT = 1;//预期按钮
    public static final int TOTAL = 2;//累计按钮
    public static final int FROM_CARLIST_BACK = 3;//选中车返回
    public static final int ADD_CONTACT = 4;//添加联系人
    public static final int LAST_WEEK = 5;//驾驶报告上一周
    public static final int NEXT_WEEK = 6;//驾驶报告下一周
    public static final int FRESH_CONTACT = 7;//添加联系人
    public static final int CONTACT_CHANGEED = 8;//联系人发生变化
    public static final int SETTING_CHANGEED = 9;//事件设置发生变化
}
