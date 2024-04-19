package cn.bluedog2333.blueorginbackinit.model.pojo.pay;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayResult {
    private String trade_no;
    private String out_trade_no;
    private String type;
    private String name;
    private String money;
    private String trade_status;
    private String param;
    private String sign;
    private String sign_type;
}
