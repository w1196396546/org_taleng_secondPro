package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000118646074";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCiR3aroQcmZVfbol4S7tYuD6oA3TEgv5+rB7mxBtVum7kvDF/IgBJCJ3+vbQVad0sDzhcRA86rd6UUntc5AdcdEG2x6i1lWD2c32Z3RL9PhZUQ1z/1+QPvgdBTcGa1j5+5NgmTQVK/NLNDhvG4fgMriT6Ppj2UYQ2m8lvRicNrQoDmwFBv4EBKBgwC3q7WADVA96kY08/V4NwWcT84hB3bWuvB3cMstLYQT5aIyusR4iNl79e8Wbk5LSOjpfblllHWYIMLd0iVpbzWFcOKJ3//vqwVls0vS6WC8zWShUtc8zKHDSxnD0w+5jJyO8qPy9EvcIKpPyR89ZD5P1tfbJVbAgMBAAECggEAWNB75lrvKth0SUSH8By/I9520Jo+dQnrPROxUW3ICg04QMOo+Hb/hy1O+JfWQjjf2WlFvo4y4i1NMAfvCsgzDzkakDD2P20X629pCQWbHs6hUuI7o0dyG/cUKVZvIBdG93rXqrwpqXQSWHbWfH6bBYzLrJrzQO4NJr2l+vxG88BEOQHFY9YuUAlPBAk6W2bqCOk79kAz4+sFJNBSEi8lJf7uPAMknjOUnY4Xaoe3L0RMjfrOx37FU+ntnWWO3qXX0VNDf554OGFqTcIvs0rmqQUdzhaLsEPHY1t8aA1YYNYZNw285/4WsFfslRcuydcdF+p0dw7FrgMdMPcLxZJ1wQKBgQDuA9tduXt85eKEl2piXKT+nsyQwZQAp15ox484sLMz/SYYo/qyofe9qBt5ctghax5S+Jvplxbef4Wfq+OoN/Iww/wDtGAADPz4gMgtkaHs5PNvJq7SAEl8uPfAqgbeuPBdjmhP6skrvPTUDIrJQLALaJvaaV0jxnhUFxImfGFWiQKBgQCuipPhSTmNlrsEdI9KA6rw3FP/71uZ0o+Eshv1qy4EWnrpP/b8xr878jgh0glFrRPz7Qha9Vg7WEdSlSf/VruFmWhuayWdu4z4Ly+91+ZtiNo0xhtirsUCdCSgTl+H+aQIwEulQlON8pQ8IYvdEqgKwh+RQbclMWqD6cTash+TwwKBgQDZOv/tjKoeFholdaSM3Y1hLkH6JBMWfar26pvW277SxksM1iyq+JSJrnJU5/JGneNxSqHSEim3LKAiWNlQ4K7Uvyq1nty3byM7Ex6gKHinI1birWWFGOn5QUAtwYC/qf8HBEBEID83AiBZoJDSmY2S/Aiy0NNHIY8VAHF6Wq7kMQKBgFBhJysX0rZe1O9/UkGuoLldbh2uOEwFiOx+LZwcYgvFM1dZir/sM0NDEBCbLoP06fxqi2+AD0r9I5Nnd00Cv0cOV+aJ13/lUE03RdAyrj6BSKQHG7N9cuLnY7qjfSEcmSsii3OmsN3s+dEbTdLYdaTGs0zsq5AoSrpHHM3uO9aLAoGBAIKCTXp2vWf/Aiam5C0ENMAO3ucfxsEhHYTPVGQcZX4ATChlV7NgwS4cJSApJ7wvgftGBjvcRBibOWjGdhy6rxlTx68TqywxfttjWTrI8wgqH1UqwryIZXCd13f7UBX9F5gTd4Le3KfYMUWUu05nXzYpvnOBQlj1aaYZdA8sIUSw";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgkTO5AxKy/RSU+44UTdwXbm0sd/M/obv3IL9kFjOcHfed2mKY8swrT3HLXvAUiQHlDC1cm/MdDtgtU2JFbDPGkCJNCKuHasnOI3xmfAWolDJVHmCMkTNC3ZXQsv1BdXyhfnmIcvEkZZT4MO8LegjK4aQCrV5YeTi1zAJ7Fdo9znnKWemYWGxedOYqgeOGX5TBAcrNAUSDdbmlU1Ai1+AigWr//hB/YNKVkFly2XL4ibPyX/d2lK3kjfoWlh7hf6yz/oSkaakZ3EV6LCn0QeWsl5bUmpq3z3KMBSF32Xvfp6hplMIUnE254TuTNzpbRldy4n12U/PWVQ/LR1veaY/OwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:10001/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:10001/ok.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
