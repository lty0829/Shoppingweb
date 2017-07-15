package Shopping.model.vo;

public class Cast {
	int castid;
	int commodityid;
	int quantity;
	int userid;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCastid() {
		return castid;
	}
	public void setCastid(int castid) {
		this.castid = castid;
	}
	public int getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(int commodityid) {
		this.commodityid = commodityid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
