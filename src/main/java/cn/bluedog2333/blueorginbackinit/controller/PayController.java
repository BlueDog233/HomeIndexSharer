package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.model.pojo.pay.PayResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pay")
public class PayController {
    /**
     * 通知类型：服务器异步通知（notify_url）、页面跳转通知（return_url）
     *
     * 请求方式：GET
     *
     * 请求参数说明：
     *
     * 字段名	变量名	必填	类型	示例值	描述
     * 商户ID	pid	是	Int	1001
     * 易支付订单号	trade_no	是	String	20160806151343349021	易支付订单号
     * 商户订单号	out_trade_no	是	String	20160806151343349	商户系统内部的订单号
     * 支付方式	type	是	String	alipay	支付方式列表
     * 商品名称	name	是	String	VIP会员
     * 商品金额	money	是	String	1.00
     * 支付状态	trade_status	是	String	TRADE_SUCCESS	只有TRADE_SUCCESS是成功
     * 业务扩展参数	param	否	String
     * 签名字符串	sign	是	String	202cb962ac59075b964b07152d234b70	签名算法点此查看
     * 签名类型	sign_type	是	String	MD5	默认为MD5
     * 收到异步通知后，需返回success以表示服务器接收到了订单通知
     */
    @GetMapping("/notify")
    public String notify(PayResult payResult){
        //TODO deal payResult to sql


        return "success";
    }
}
