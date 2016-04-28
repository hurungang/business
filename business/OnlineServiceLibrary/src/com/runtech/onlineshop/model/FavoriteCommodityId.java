package com.runtech.onlineshop.model;

// Generated Apr 18, 2010 5:12:23 PM by Hibernate Tools 3.2.4.GA

/**
 * FavoriteCommodityId generated by hbm2java
 */
public class FavoriteCommodityId implements java.io.Serializable {

	private Integer userId;
	private Integer commodityId;

	public FavoriteCommodityId() {
	}

	public FavoriteCommodityId(Integer userId, Integer commodityId) {
		this.userId = userId;
		this.commodityId = commodityId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FavoriteCommodityId))
			return false;
		FavoriteCommodityId castOther = (FavoriteCommodityId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getCommodityId() == castOther.getCommodityId()) || (this
						.getCommodityId() != null
						&& castOther.getCommodityId() != null && this
						.getCommodityId().equals(castOther.getCommodityId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getCommodityId() == null ? 0 : this.getCommodityId()
						.hashCode());
		return result;
	}

}
