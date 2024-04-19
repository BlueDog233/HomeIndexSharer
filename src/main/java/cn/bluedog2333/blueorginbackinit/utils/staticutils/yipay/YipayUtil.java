package cn.bluedog2333.blueorginbackinit.utils.staticutils.yipay;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;

import java.util.HashMap;

public class YipayUtil {
    private static String key;
    private static String pid;
    private static String feedback;
    static{
        Setting setting=new Setting("config/yipay.setting");
        key=setting.get("key");
        pid=setting.get("pid");
        feedback=setting.get("feedback");
    }
    public static String pay(String type,String name,String money,String ip){
        HashMap map=new HashMap(){{
            put("pid",pid);
            put("type",type);
            put("out_trade_no",System.currentTimeMillis());
            put("notify_url",feedback);
            put("name",name);
            put("money",money);
            put("clientip",ip);

        }};
        String sign=getSign(map);
        map.put("sign",sign);
        map.put("sign_type","MD5");
        String s= HttpUtil.post("https://yi-pay.com/mapi.php",map);
        String qrcode= JSONUtil.parseObj(s).getStr("qrcode");
        return qrcode;
    }
    public static String getSign(HashMap map){
        /**
         * 1、将发送或接收到的所有参数按照参数名ASCII码从小到大排序（a-z），sign、sign_type、和空值不参与签名！
         *
         * 2、将排序后的参数拼接成URL键值对的格式，例如 a=b&c=d&e=f，参数值不要进行url编码。
         *
         * 3、再将拼接好的字符串与商户密钥KEY进行MD5加密得出sign签名参数，sign = md5 ( a=b&c=d&e=f + KEY ) （注意：+ 为各语言的拼接符，不是字符！），md5结果为小写。
         *
         * 4、具体签名与发起支付的示例代码可下载SDK查看。
         */
        String[] keys= (String[]) map.keySet().toArray(new String[0]);
        java.util.Arrays.sort(keys);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<keys.length;i++){
            String key=(String)keys[i];
            String value=map.get(key).toString();
            if(value.equals("")){
                continue;
            }
            sb.append(key).append("=").append(value).append("&");
        }
        String s=sb.substring(0,sb.length()-1);

        return MD5.create().digestHex(s.toString() + key);
    }
}
