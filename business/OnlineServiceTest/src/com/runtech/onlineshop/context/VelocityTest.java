package com.runtech.onlineshop.context;


import java.io.StringWriter;
import java.math.BigDecimal;

import junit.framework.Assert;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Before;
import org.junit.Test;

import com.runtech.onlineshop.helper.CommodityOrderItemHelper;

public class VelocityTest {

	private VelocityContext context;
	private StringWriter stringWriter;
	private double discount;
	private BigDecimal decimal;

	@Before
	public void setUp() throws Exception {
		context = new VelocityContext();
		stringWriter = new StringWriter();
	}
	
	@Test
	public void testIf() throws Exception {
		String template="#if(${orderHelper.getInt()}==1)testIf#end";
		CommodityOrderItemHelper orderHelper = new CommodityOrderItemHelper();
		context.put("orderHelper", this);
		boolean evaluate = Velocity.evaluate(context, stringWriter, "user", template);
		Assert.assertEquals("testIf" , stringWriter.toString());
	}
	
	@Test
	public void testNumberExpression() throws Exception {
		String template="${orderHelper.setDiscountValue('1')}";
		context.put("orderHelper", this);
		boolean evaluate = Velocity.evaluate(context, stringWriter, "user", template);
		Assert.assertEquals(1.0 , this.getDiscount());
	}

	@Test
	public void testMultiply() throws Exception {
		this.setDecimal("10");
		String template = "#set($result=$value.decimal.intValue()*0.03) $value.setDecimal(\"$!{result}\")";
		context.put("value", this);
		Velocity.evaluate(context, stringWriter, "user", template);
//		Assert.assertEquals("5", stringWriter.toString());
		Assert.assertEquals(new BigDecimal("0.3"), this.getDecimal());
	}
	
	public void setDecimal(String decimal){
		this.decimal = new BigDecimal(decimal);
	}
	
	public BigDecimal getDecimal() {
		return decimal;
	}

	public void setDiscountValue(String value){
		this.discount = Double.parseDouble(value);
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getInt(){
		return 1;
	}
}
