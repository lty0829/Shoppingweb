package Shopping.model.vo;

public class Commodity {
	private int CommodityID;
	private String CommodityName;
	private float CommodityPrice;
	private String Description;
	private String Productor;
	private int ShopID;
	private int Quantity;
	private int Type;
	public int getCommodityID() {
		return CommodityID;
	}
	public void setCommodityID(int commodityID) {
		CommodityID = commodityID;
	}
	public String getCommodityName() {
		return CommodityName;
	}
	public void setCommodityName(String commodityName) {
		CommodityName = commodityName;
	}
	public float getCommodityPrice() {
		return CommodityPrice;
	}
	public void setCommodityPrice(float commodityPrice) {
		CommodityPrice = commodityPrice;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getProductor() {
		return Productor;
	}
	public void setProductor(String productor) {
		Productor = productor;
	}
	public int getShopID() {
		return ShopID;
	}
	public void setShopID(int shopID) {
		ShopID = shopID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	
}
