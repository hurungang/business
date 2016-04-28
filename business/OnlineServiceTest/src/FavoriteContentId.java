// default package
// Generated Jul 9, 2013 11:44:45 AM by Hibernate Tools 3.4.0.CR1

/**
 * FavoriteContentId generated by hbm2java
 */
public class FavoriteContentId implements java.io.Serializable {

	private Integer userId;
	private Integer contentId;

	public FavoriteContentId() {
	}

	public FavoriteContentId(Integer userId, Integer contentId) {
		this.userId = userId;
		this.contentId = contentId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FavoriteContentId))
			return false;
		FavoriteContentId castOther = (FavoriteContentId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getContentId() == castOther.getContentId()) || (this
						.getContentId() != null
						&& castOther.getContentId() != null && this
						.getContentId().equals(castOther.getContentId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getContentId() == null ? 0 : this.getContentId().hashCode());
		return result;
	}

}
