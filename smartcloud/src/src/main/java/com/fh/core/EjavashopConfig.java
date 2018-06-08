package com.fh.core;

/**
 * TODO：ejavashop 核心配置类，商城启动时首先配置此配置文件<br/>
 * 其中支付有打印的一些报文，用于替换正式账号之后调试使用，调试通过之后建议去掉<br/>
 * 配置常量类，主要配置有支付方式、短信通道、快递100、邮箱等等
 *                       
 * @Filename: EjavashopConfig.java
 * @Version: 1.0
 * @Author: 
 * @Email: wpjava@163.com
 *
 */
public class EjavashopConfig {

	  // -----------邮箱配置start-------------
    /** 邮箱服务提供商host，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_HOST    = "smtp.boyu100.com";//"smtp.ym.163.com";
    /** 邮箱服务提供商port，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_PORT    = "25";
    /** 发送邮件的邮箱地址 */
    public static final String SEND_EMAIL_NAME     = "chris.chen@boyu100.com";
    /** 发送邮件的邮箱密码 */
    public static final String SEND_EMAIL_PASSWORD = "Chenhong1005";

    // -----------邮箱配置end---------------

    // -----------JAVA定时器执行配置start-------------
    //是web项目的定时器，主要对首页进行静态化更新的，可以根据自己的业务逻辑进行调整
    /** java定时器第一次执行时间（毫秒） */
    public static final long TIME_DELAY  = 60000L;
    /** java定时器执行间隔时间（毫秒） */
    public static final long TIME_PERIOD = 600000L;

    public static final long ACCESS_TOKEN_DELAY  = 0;
    public static final long ACCESS_TOKEN_PERIOD = 1000 * 60 * 60 * 2;
    // -----------JAVA定时器执行配置end---------------

    /** 快递100授权key */
    public static String KUAIDI100_KEY = "989f7ca4007d02b0";

    /** 国信互联短信发送URL(向短信供应商申请) 其中URL中包括用户名和密码 */
    public static String SEND_SMS_URL = "";

    /**
     * 多订单支付的分隔符，注意不能使用 “_” 、 “-” 和一些特殊字符，否则银联不能支付成功，建议2到5位的字母，数字可能会解析错误
     */
    public final static String ORDER_SEPARATOR = "byshop";
    
    //支付宝开放平台开发者APPID
    public final static String ALIPAY_APP_ID = "2017030606081874";
    
    //服务端SDK生成APP支付订单信息url
    public final static String ALIPAY_SERVER_URL = "https://openapi.alipay.com/gateway.do";
    

    ///////alipay start////////////
    /**
     * 支付宝，显示订单名称，PC和Mobile公用，一般设置商城的名称
     */
    public final static String ALIPAY_ALL_SUBJECT = "VEG云平台";

    /**
     * HTML5 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
     */
    public static String ALIPAY_MOBILE_PARTNER = "2088912248013712";

    public static String ALIPAY_MOBILE_PRIVATE_KEY = "mbe0p0vogzacyh2b6t6l9tvct2wvyg71";//"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKh6HkfwcsHIz69VUYPLbZS9z3IBkIAFL5BdMEvXybWDwzm2eFD6a7nvinqfzTyWLzBHvisBMyqR0a+zOBe3Q7Pdb6eNK5kYc4loIraSxn0PvqeduSa2KLtqyeg12ndA0+9RpMM0xAPhhVGFbsA1z1DCi1o+j1i3DgyiDXQPMuzjAgMBAAECgYACzKyuI1e00qJo1nEZUIsMmq7UxSPijLOCoZnI2NgYNQ1MazTKm66ok0toyDj/1bsJAVgunMF95phmZAL9meT9FCPP6tG/tMGVMAh7BdakaN8rc7ccw2Zo8CUbizGHXIazosF6g53Wl4dy97+fECBdUE82k04UpoCS1AkCUfoQUQJBAPXVk7ajaMqu46JF04d1UaiVWSBTDswKjFGrs0YnEkr2A3VBK6AxjQwzjVBj/pPV8VmiLp+ogRyBWnHidNO1nAkCQQCvcaFxDoeiVA7xbiboIlSn6B0mhbdpfU+kvl49Qm0s3Dza86Gqli0q4M/qJhHahwVzmb23qnAIR0U0M9iu0pSLAkBtKuYIsfJEJ3vgwN9ZhQi5M3E7wIaOp5R+ZCAdZBZkgXExrbogzkBTjcUQUQdpQRWHd1T7A4oqTWsLrcOxjDF5AkBvQndoNBetyPLlBr7jrYnUJW3/FQN4gYB1cEhhPvPvZAevrptSjAQ+0ezoh5YA6Gl7ov7eL9b2WQQ5E7wROff7AkA5R2sHOdqYAwfWMUxJzFIZ0ZYe5JsWt5vn7dfIJ3DLWIWllctHJl4i5Xg4+z+PdQ8Sv33O04qTBGHu89G/d7hy";
    
    /**
     * 支付宝H5私钥
     */
    public static String ALIPAY_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKpd2t9Do6h+ZfNhKQDlp92gHn9PnLIX0rwgP4Q0TohnvsdmY6hpR+Awyd7c/DmuWebRjVsuudv1FuRU6/LGiyBHwTggA9oLfjZwyFZW6KhMwHAYfXErVo5BAi0p0wiJhlaK+NYCBcQkChTs4a++aaahluNEoSaXn3l137c92uGLAgMBAAECgYAYs27ufAIYhesGNIobZTDHg1Woo8RWVfnp6MsMwgC46kHQ61l7q3gOW0SJmYTAYDUMsElMdSPhblGZXW6wTnURRl6ieEqWC2VHFm4Vxbtti2xogVrRXhi7fTXb3KqTMDnLWGa0qqn7WWqnOu1TsMR7OrziPheOl0rex7EkcPozsQJBAOB21I7DNzUQ6nkKVjtcbNqz8CNd1DODjJ1RPwlXXo1zQNa60HgYdyTLOKvqJLg+DLReTbH6+9ywXqaDcFnUk0cCQQDCTVR0gRJSpXvujOg+ADVxBrx5lkx+2nQW24Xm+ks8cFCKGqU2VhqGRvq1jjz/l4egmIYwljLzlHiNTS+cIHmdAkEAw6Ht6itsj3yZzzraI0yPtyGC12As1WT/BhHMrfiO+RuNVFln3nP8S34ve25L6KRz8DQM5WVJZHP3WotOPYzPewJATp6nq7SGS3a2XOIbHutKtH87SB9uuG2AR7dvyPRB+SShEiCR9RiGBws1ar0xJWKU+IB+264K09ChrtsqhowFaQJAL2EA6MZ0nh44yQPyoDIXeU/mSSyiKzUhvB5SmVcIE8Hw9n592yq3BvMV9NmUrzrB+FDQfmsRUuXImYrIyty6Ig==";
    /**
     * 支付宝H5公钥
     */
    public static String ALIPAY_PUBLIC_KEY ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
    /**
     * 支付宝APP密钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDC60O8MJ/tlBGHtVrc1KV/KJQvlhya1h7yf81eMg3dQMNL09ysMitSKBe6QTPkp0yEXNpNl+EpeEpK3CrGcCvSQ8wRCFfSQGIYo0n8v1/Q7UlYd4g00m+f+m9YIktVsAMLj2ywBEqSRAWIsEHCPIAaBZPMxWghvAUkAIIK+YpFyWU3KgDcGHT+97HH8DDKGvNiuNLa16Q050pGFDO/7bBvrZ7WgACkbWUAFmx0V6Plk930FB4hTHLDLixJlzxAolmQ4D7grdGGZq7pv84MvseQJzChw03p6hQvXdEjHLgczIKM4k/Bc4mx+mPffzCYyyUrCfflulZOHiSvv0YGUKqXAgMBAAECggEBALa+5G6YGBkibZ0ShvHr3I3veZ9z2bXBcpphACaEP0Nk6LU7GPXIw5zvQe+6GfESeUbP10xQTSYYRW8SJHiPykYEklqVu+C/xDjdPwzcT/UNw2pSBz2e3lDa93FmFcNsli5MddZA77laVOXmeoClQGUlPYEKw6p9IUSzxTiM5PqvR0IB1ygt7oysadVzXEKI9K729NHfLJD3ThjoDsdF5IxpzzvkIi+Qf4GsUbUMbGDI3D1vR5EAsiHwH96jL/o7kuargCyVHn5Ok9izXXOjVoSGwczq9LIn07JR6NWAbb6gk5zKtAs9RhjQ6a3ff9rSqonpOXLMAQS2QcGLU9XxEaECgYEA8SQykQcHefGtXQ9Y6yM4mscUha0UZdKXPXKqfAEuslAkVgXGfAqUoqqdFNqqXvjNP7lHOJmPZLgGFqQbONenttnWWb2qNaaOJOKtosLspVTnbOLXHYmZV9SAJ520eFl1uc3YD5k/4oCzbNcmFAU4zzRLOJATehGPTDrLHX1ghKUCgYEAzu3ynZquqLaM4gEMQeConqugJxPqFLx1Fb20lLM+d+wc9anlgacEnDGpDu7DLbRoXrUk+FPsknDOpkaHIpGckgG5Sdi2qvFGCiwa7Rz8tI5J0imyItKORidYioJJFmtvX4wiOplsGAbEdovI3SM361oKJSlD6uHzQxWFqOeUAYsCgYAwgLVjEmTglEnok/OwqA70ouJv2NZnfKpLA8ti0eIQn6oPW24G+SHYbjTWQv1NyQGPtbarnceH3LPszeljz4y+bKZ+unWIAQ5KQQXn3Qk4YBGEh0P5fvnG/skgLspkAqp5SvwQdySwbG6qj8Pq9vnsQ3p10BT60pleHd1I+zT0uQKBgDX8alGn621kwviroVEGlbdfu1a2oVlrmXhkl9o7sU6EMUH+YzwiINfHNP4KWK1x5dDfnZeT/aJu/9rth5ADQT/uZzLW8UFD1SwOObAm4d8RSD0mV60yFaBD35uzRfItcU8SNlPndwYFrffnnC3lZE9onED5tRhwfpC/6rEYScrlAoGBANP/NRTWvpOJtSTPA5nNHZS1+2pJmq9N7cHaBIstKP9OqlT+TDtlBJfUgNfrS2YTCSzJwjM9fNL6afB71vWny0vKXk2VO265iUlnF2PzMEJdEKBLhATM5rSBKK/ZjrKwk5ZyCNAZMSSKlL1kW4uHuU4OWVuw2C4sKPEqP8hUflLP";

    /**
     * 支付宝APP公钥
     */
    public static String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmUPwaRuo06hueE3VbsO3gjgmpWD12a62EkAAFIsuko0QHyrvDEA7aLkEZ3z9j69j1zl1HIc16pJWnWiKjLwna42kal2CBcXx/UOthc5AoK6Ob4GMt6rwWrwRa+BQtHOx+knCpQuxqD058kyYkkP4Fnh4tmf/861NwoeuPMapiPm7beyxKaY643b/bnC5DynZExavC0POC8s55baC1D/zhp2M92w7dP/JBnetVbyodqfIC/4aD6iGAEBKZNv5A7UfEE3bhAa33qHARP3X3ShsignDJtk1X5mZmXDVzhqgwvSzbvziMS4jULiymbJ/kmj94gY/8Dy4TEfDdYio/B2SnwIDAQAB";

    /**
     * PC 支付宝：合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    public static String ALIPAY_PC_PARTNER = "2088912248013712";

    /**
     * PC 支付宝，收款支付宝账号，一般情况下收款账号就是签约账号
     */
    public static String ALIPAY_PC_SELLER_EMAIL = "linbo.zhao@boyu100.com";

    /**
     * PC 支付宝，商户的私钥，PC支付采用MD5 方式
     */
    public static String ALIPAY_PC_KEY = "mbe0p0vogzacyh2b6t6l9tvct2wvyg71";
    ///////alipay end///////////
    /**
     * 快递公司编码
     */
	public static String EXP_CODE;
	

    ////////银联支付 start 根据业务需求acp_sdk.properties中key 和 value 值进行替换/////////////
    /**
     * 银联支付，用户ID
     */
    public final static String UNIONPAY_MERID = "777290058144438";

    /**
     * 银联支付，请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节  
     */
    public final static String UNIONPAY_REQRESERVED = "VEG";

    /** 配置文件中的前台URL常量. 银联支付中对应的文件：acpsdk.frontTransUrl */
    public static final String SDK_FRONT_URL     = "https://101.231.204.80:5000/gateway/api/frontTransReq.do";
    /** 配置文件中的后台URL常量. 银联支付中对应的文件：acpsdk.backTransUrl*/
    public static final String SDK_BACK_URL      = "https://101.231.204.80:5000/gateway/api/backTransReq.do";
    /** 配置文件中的单笔交易查询URL常量. 银联支付中对应的文件：acpsdk.singleQueryUrl*/
    public static final String SDK_SIGNQ_URL     = "https://101.231.204.80:5000/gateway/api/queryTrans.do";
    /** 配置文件中的批量交易查询URL常量. 银联支付中对应的文件：acpsdk.batchQueryUrl*/
    public static final String SDK_BATQ_URL      = "acpsdk.batchQueryUrl";
    /** 配置文件中的批量交易URL常量. 银联支付中对应的文件：acpsdk.batchTransUrl*/
    public static final String SDK_BATTRANS_URL  = "https://101.231.204.80:5000/gateway/api/batchTransReq.do";
    /** 配置文件中的文件类交易URL常量. 银联支付中对应的文件：acpsdk.fileTransUrl*/
    public static final String SDK_FILETRANS_URL = "https://101.231.204.80:9080/";
    /** 配置文件中的有卡交易URL常量. 银联支付中对应的文件：acpsdk.cardTransUrl*/
    public static final String SDK_CARD_URL      = "acpsdk.cardTransUrl";
    /** 配置文件中的app交易URL常量. 银联支付中对应的文件：acpsdk.appTransUrl*/
    public static final String SDK_APP_URL       = "acpsdk.appTransUrl";
    
    /**银联APP支付请求地址*/
    public static final String SDK_APP_REQ_URL 	 = "https://gateway.test.95516.com/gateway/api/appTransReq.do";

    /** 配置文件中签名证书路径常量. 银联支付中对应的文件：acpsdk.signCert.path*/
    //    public static final String SDK_SIGNCERT_PATH         = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/700000000000001_acp.pfx";
    public static final String SDK_SIGNCERT_PATH         = "/mnt/tomcat8/webapps/veg/vegcloud/WEB-INF/classes/assets/700000000000001_acp.pfx";
    //public static final String SDK_SIGNCERT_PATH		 = "E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/700000000000001_acp.pfx";
    /** 配置文件中签名证书密码常量. 银联支付中对应的文件：acpsdk.signCert.pwd*/
    public static final String SDK_SIGNCERT_PWD          = "000000";
    /** 配置文件中签名证书类型常量. 银联支付中对应的文件：acpsdk.signCert.type*/
    public static final String SDK_SIGNCERT_TYPE         = "PKCS12";
    /** 配置文件中密码加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptCert.path*/
    //    public static final String SDK_ENCRYPTCERT_PATH      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/acp_test_enc.cer";
    public static final String SDK_ENCRYPTCERT_PATH      = "/mnt/tomcat8/webapps/veg/vegcloud/WEB-INF/classes/assets/acp_test_enc.cer";//"E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/acp_test_enc.cer";
    //public static final String SDK_ENCRYPTCERT_PATH      = "E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/acp_test_enc.cer";
    /** 配置文件中磁道加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptTrackCert.path*/
    //public static final String SDK_ENCRYPTTRACKCERT_PATH = "E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/acp_test_verify_sign.cer";//"acpsdk.encryptTrackCert.path";
    public static final String SDK_ENCRYPTTRACKCERT_PATH = "/mnt/tomcat8/webapps/veg/vegcloud/WEB-INF/classes/assets/acp_test_verify_sign.cer";//"acpsdk.encryptTrackCert.path";
    /** 配置文件中验证签名证书目录常量. 银联支付中对应的文件：acpsdk.validateCert.dir*/
    //    public static final String SDK_VALIDATECERT_DIR      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/";
    public static final String SDK_VALIDATECERT_DIR      = "/mnt/tomcat8/webapps/veg/vegcloud/WEB-INF/classes/assets/";//"E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/";
    //public static final String SDK_VALIDATECERT_DIR      = "E:/projects/vegcloud/trunk/src/byPlatform/byPlatform-api/src/main/resources/assets/";

    /** 配置文件中是否加密cvn2常量. 银联支付中对应的文件：acpsdk.cvn2.enc*/
    public static final String SDK_CVN_ENC    = "acpsdk.cvn2.enc";
    /** 配置文件中是否加密cvn2有效期常量. 银联支付中对应的文件：acpsdk.date.enc*/
    public static final String SDK_DATE_ENC   = "acpsdk.date.enc";
    /** 配置文件中是否加密卡号常量. 银联支付中对应的文件：acpsdk.pan.enc*/
    public static final String SDK_PAN_ENC    = "acpsdk.pan.enc";
    /** 配置文件中证书使用模式 银联支付中对应的文件：true*/
    public static final String SDK_SINGLEMODE = "true";
    ////////银联支付 end//////////////

    //////////微信支付 start////////////////////
    /**
     * 微信支付显示的订单名称
     */
    public static final String WEIXIN_ORDER_NAME         = "VEG云平台订单";
    //////////微信支付 end/////////////////////

    //------------------------短信发送相关参数bg--------------------------//
    /**
     * 默认固定线程数
     */
    public static final int    DEFAULT_RUN_THREAD_NUM    = 2;

    /**
     * 用户每日最多获取的短信验证码数量
     */
    public static final int    SMS_MAX_GIVEN_NUM         = 5;

    public static final String SMS_URL                   = "http://sz.ipyy.com/smsJson.aspx";
    public static final String SMS_VERIFY_CODE           = "您的验证码是：@【VEG云平台】";
    //------------------------短信发送相关参数ed--------------------------//
    
    //-----------------顺丰物流接口相关参数bg------------------------------//
    
/*    *//**
     * Checkword(秘钥) 
     *//*
    public static final String	CHECKWORD				  = "j8DzkIFgmlomPt0aLuwU";
    
    *//**
     * 顺丰HTTP/POST接口地址 
     *//*
    public static final String	SFEXPRESSURL			  = "https://bspoisp.sit.sf-express.com:11443/bsp-oisp/sfexpressService";
    
    *//**
     * 端口号 
     *//*
    public static final Integer	PORT					  = 11443;
    
    *//**
	 * 查询号类别 1:根据顺丰单号查询
	 * 		  2:根据客户订单号查询
	 *//*
    public static final Integer TRACKING_TYPE_1			  = 1;
    
    public static final Integer TRACKING_TYPE_2			  = 2;
    */
    //-----------------顺丰物流接口相关参数ed------------------------------//
	
	//-------------------------大于短信begin--------------------------------//
    
    public static final String DAYUSMS_URL = "http://gw.api.taobao.com/router/rest";//短信发送url
    
    public static final String DAYUSMS_APPKEY = "23639627";//应用秘钥
    
    public static final String DAYUSMS_APPSECRET = "b74f39cf480334409625bcb5356a3629";//应用secret
    
    public static final String DAYUSMS_SIGNNAME = "VEG云平台";//短信签名
    
    public static final String DAYUSMS_SMSTYPE = "normal";//短信类型
    
    public static final String TEMPLATE_VERIFYCODE = "SMS_47510068";//发送验证码
    
    //-------------------------大鱼短信end----------------------------------//
}
