package Shopping.model.vo;
import java.sql.Timestamp;
public class Orders {
	private int OrderID;
	private int UserID;
	private Timestamp OrderDate;
	private int Statue;
	private float Totalprice;
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public Timestamp getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}
	public int getStatue() {
		return Statue;
	}
	public void setStatue(int statue) {
		Statue = statue;
	}
	public float getTotalprice() {
		return Totalprice;
	}
	public void setTotalprice(float totalprice) {
		Totalprice = totalprice;
	}
	
}
