package com.runtech.web.model;

import java.math.BigDecimal;


public interface CartItem {

	String getStatus();

	BigDecimal getPrice();

	BigDecimal getRealPrice();

	Integer getPoint();

	void setCommodityNumber(Integer commodityNumber);

	void calcPrice();

	Object getCommodity();

	Integer getCommodityId();

}
