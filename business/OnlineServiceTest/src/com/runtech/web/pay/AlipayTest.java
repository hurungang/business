package com.runtech.web.pay;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.alipay.util.Payment;
import com.runtech.web.runtime.RuntechProperties;

public class AlipayTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSign() throws Exception {
		String paygateway =	"https://www.alipay.com/cooperate/gateway.do";
		String service = "create_partner_trade_by_buyer";
		String signType = "MD5";
		String inputCharset = "utf-8";       
		String partnerId = "2088002021644599"; 
		String key = "uoo8kx0anu3204d1yuyu5cx5q63vohf5"; 
		String sellerEmail = "freegoo@unirnd.com";
		
		String orderId		= "1000000095_17";	
		
		String subject			= "购物车结算";			 //商品名称
		String body			= "绿心猕猴桃"; //商品描述，推荐格式：商品名称（订单编号：订单编号）
		String price			= "60.00";				 //订单总价
		String quantity = "1";
		String show_url        =   "http://localhost:8080/Onlineshop/page/general/orderFinalize.do?id=1000000095_31";
		String payment_type    =   "1";
		String discount     =  "0";
		//******物流信息和支付宝通知，一般商城不需要通知，请删除此参数，并且在payment.java里面相应删除参数********
		String logistics_type = "EXPRESS";
		String logistics_fee  = "0.00";
		String logistics_payment = "BUYER_PAY";
		String receive_name = "胡润刚";
		String receive_address = "温江";
		String receive_phone = "13334565123";
		
		String notifyUrl		= "http://localhost:8080/Onlineshop/page/general/alipayNotify.do";	//通知接收URL
		String returnUrl		= "http://localhost:8080/Onlineshop/page/general/alipayReturn.do";	//支付完成后跳转返回的网址URL

		String alipayUrl = Payment.createUrl(paygateway,service,signType,orderId,inputCharset,partnerId,key,sellerEmail,body,subject,price,quantity,show_url,payment_type,discount,logistics_type,logistics_fee,logistics_payment,returnUrl,notifyUrl, receive_name, receive_address, receive_phone);
		Assert.assertEquals("https://www.alipay.com/cooperate/gateway.do?show_url=http%3A%2F%2Flocalhost%3A8080%2FOnlineshop%2Fpage%2Fgeneral%2ForderFinalize.do%3Fid%3D1000000095_31&payment_type=1&logistics_payment=BUYER_PAY&seller_email=freegoo%40unirnd.com&partner=2088002021644599&quantity=1&service=create_partner_trade_by_buyer&price=60.00&_input_charset=utf-8&discount=0&return_url=http%3A%2F%2Flocalhost%3A8080%2FOnlineshop%2Fpage%2Fgeneral%2FalipayReturn.do&out_trade_no=1000000095_17&receive_phone=13334565123&receive_name=%E8%83%A1%E6%B6%A6%E5%88%9A&notify_url=http%3A%2F%2Flocalhost%3A8080%2FOnlineshop%2Fpage%2Fgeneral%2FalipayNotify.do&receive_address=%E6%B8%A9%E6%B1%9F&subject=%E8%B4%AD%E7%89%A9%E8%BD%A6%E7%BB%93%E7%AE%97&logistics_fee=0.00&body=%E7%BB%BF%E5%BF%83%E7%8C%95%E7%8C%B4%E6%A1%83&logistics_type=EXPRESS&sign=b7bb001e9707630c44c0b7508bd04975&sign_type=MD5", alipayUrl);
	}
}
