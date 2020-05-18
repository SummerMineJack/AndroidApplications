package ungpay.com.androidapplications.retrofit;

/**
 * 服务端接口地址
 */
public class ServerInterfaceConstant {
        private static final String Domain = "http://130.252.86.191:9527";//etc内网地址
//    private static final String Domain = "http://47.114.120.59:9527";//etc外网地址

    //登录
    public static final String appSdkUserLogin = Domain + "/front/sdkit/user/login";

    //注册
    public static final String appSdkUserRegister = Domain + "/front/sdkit/user/userRegister";

    //通过车辆VIN码获取车辆信息
    public static final String appSdkGetCardInfo4VINCode = Domain + "/front/sdkit/vehicle/checkByVin";

    //获取用户信息【含车辆】
    public static final String appSdkGetUserInfoFull = Domain + "/front/sdkit/user/getInfoFull";

    //获取短信验证码【通用接口】
    public static final String appSdkGetSMSCode = Domain + "/front/sdkit/common/sendVerifyCode";

    //修改车辆信息
    public static final String appSdkModifyInfo = Domain + "/front/sdkit/vehicle/modifyInfo";

    // 实名认证--新
    public static final String appSdkRealCheck = Domain + "/front/sdkit/user/identify";

    //绑定签约渠道
    public static final String appSdkBindPayment = Domain + "/front/sdkit/etc/bindPayment";

    //获取银行列表
    public static final String appSdkGetBankList = Domain + "/front/sdkit/common/getBankList";

    //获取已签约的渠道
    public static final String appSdkGetPaymentList = Domain + "/front/sdkit/etc/getPaymentList";

    //设备激活成功回写
    public static final String appSdkSyncEquState = Domain + "/front/sdkit/etc/syncEquState";

    //保存车辆信息--业务新接口
    public static final String appSdkSaveCarInfo = Domain + "/front/sdkit/vehicle/saveVehicleFirstStep";

    //查看消费记录列表
    public static final String appSdkTradeRecordList = Domain + "/front/sdkit/user/getTradeRecordList";

    //查看消费记录详细
    public static final String appSdkGetTradeRecord = Domain + "/front/sdkit/user/getTradeRecord";

    //车主身份信息验证
    public static final String appSdkVerifyOwner = Domain + "/front/sdkit/vehicle/verifyOwner";

    //车辆出售
    public static final String appSdkSaleVehicle = Domain + "/front/sdkit/vehicle/saleVehicle";

    //车辆出售后回写
    public static final String appSdkSyncEquSale = Domain + "/front/sdkit/vehicle/syncEquSale";

    //挂失/解挂
    public static final String appSdkReportLoss = Domain + "/front/sdkit/etc/reportLoss";

    //更换新设备激活
    public static final String appSdkRebindEqu = Domain + "/front/sdkit/etc/rebindEqu";

    //获取车辆信息
    public static final String appSdkGetVehicleInfo = Domain + "/front/sdkit/vehicle/getFullVehicleInfo";

    //修改手机号
    public static final String appSdkUpdatePhone = Domain + "/front/sdkit/user/editUser";

    //查看审核进度列表
    public static final String appSdkGetAuditList = Domain + "/front/sdkit/etc/getProgressAudit";

    //行驶证图片识别
    public static final String appSdkGetVehicleLicenseInfo = Domain + "/front/sdkit/card/getVehicleLicenseInfo";

    //身份证图片识别
    public static final String appSdkGetIDCardInfo = Domain + "/front/sdkit/card/getIDCardInfo";

    //银行绑定
    public static final String appSdkBankBind = Domain + "/front/sdkit/etc/bindingEtcAcount";

    //银行解绑
    public static final String appSdkBankUnbind = Domain + "/front/sdkit/etc/unbindPayment";

    //网点/加油站查询
    public static final String appSdkGetQueryDetail = Domain + "/front/sdkit/common/queryInfo";

    //获取车辆颜色
    public static final String appSdkGetCarsColorsList = Domain + "/front/sdkit/vehicle/getVehiclePlateColor";

    //获取保牌、不保牌的介绍
    public static final String appSdkGetVehicleRemark = Domain + "/front/sdkit/vehicle/getVehicleRemark";

    //获取月结单
    public static final String appSdkGetTradeMonth = Domain + "/front/sdkit/user/getTradeByMonth";

    //获取用户绑定车辆列表
    public static final String appSdkGetListByUser = Domain + "/front/sdkit/vehicle/getListByUser";
}
