package Shopping.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Shopping.dao.IShoppingDAO;
import Shopping.model.vo.Cast;
import Shopping.model.vo.Classify;
import Shopping.model.vo.Follow;
import Shopping.model.vo.Orders;
import Shopping.model.vo.User;
import Shopping.model.vo.Commodity;
import Shopping.model.vo.Word;

public class ShoppingDAOImpl implements IShoppingDAO{
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public ShoppingDAOImpl(Connection conn){
		this.conn = conn ;
	}
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String sql = "select * from users where Status = 0 ORDER BY userid";
		List<User> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<User>();
				User user = new User();
				user.setUserid(rs.getInt("UserID"));
				user.setPassword(rs.getString("Password"));
				user.setUserName(rs.getString("UserName"));
				user.setPhone(rs.getString("Phone"));
				user.setGender(rs.getInt("Gender"));
				user.setAddress(rs.getString("Address"));
				user.setBirthday(rs.getString("Birthday"));	
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Commodity> getAllCom() {
		// TODO Auto-generated method stub
		String sql = "select * from commodity ORDER BY commodityID";
		List<Commodity> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<Commodity>();
				Commodity com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
				list.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from users where username = ? and password = ?";
		User user = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, name);
			this.pstmt.setString(2,password);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setPrior(rs.getInt("Status"));
				user.setUserid(rs.getInt("UserID"));
				user.setPassword(rs.getString("Password"));
				user.setUserName(rs.getString("UserName"));
				user.setPhone(rs.getString("Phone"));
				user.setGender(rs.getInt("Gender"));
				user.setAddress(rs.getString("Address"));
				user.setBirthday(rs.getString("Birthday"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users(Password,UserName,Phone,Gender,Address,Birthday,Status)" +
				" VALUES(?,?,?,?,?,?,?)";
		int result = 0;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getPassword());
			this.pstmt.setString(2, user.getUserName());
			this.pstmt.setString(3, user.getPhone());
			this.pstmt.setInt(4,user.getGender());
			this.pstmt.setString(5, user.getAddress());
			this.pstmt.setString(6,user.getBirthday());
			this.pstmt.setInt(7, 0);
			result = this.pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public List<Commodity> getComByClssify(int type) {
		// TODO Auto-generated method stub
		String sql = "select * from commodity where type = ?";
		List<Commodity> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, type);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<Commodity>();
				Commodity com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
				list.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Commodity> getLimitCom(int begin, int end) {
		// TODO Auto-generated method stub
		String sql = "select * from commodity ORDER BY commodityID limit ?,?";
		List<Commodity> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, begin);
			this.pstmt.setInt(2, end);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<Commodity>();
				Commodity com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
				list.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Classify> getAllCsf() {
		// TODO Auto-generated method stub
		String sql = "select * from classify ORDER BY classifyid";
		List<Classify> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Classify>();
				Classify csf = new Classify();
				csf.setClassifyid(rs.getInt("classifyid"));
				csf.setName(rs.getString("name"));
				list.add(csf);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public Classify findCsfById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from classify where classifyid =?";
		Classify csf = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()){
				csf = new Classify();
				csf.setClassifyid(rs.getInt("classifyid"));
				csf.setName(rs.getString("name"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return csf;
	}
	@Override
	public Commodity findComById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from commodity where commodityid =?";
		Commodity com = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()){
				com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return com;
	}
	@Override
	public List<Commodity> getLimitComByType(int begin,int end,int type) {
		// TODO Auto-generated method stub
		String sql = "select * from commodity where type = ? limit ?,?";
		List<Commodity> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, type);
			this.pstmt.setInt(2, begin);
			this.pstmt.setInt(3, end);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<Commodity>();
				Commodity com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
				list.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int dropCastById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from cast where castid = ?";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			result=this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public List<Cast> getAllcastByid(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from cast castid where userid = ?";
		List<Cast> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Cast>();
				Cast cast = new Cast();
				cast.setCastid(rs.getInt("castid"));
				cast.setCommodityid(rs.getInt("CommodityID"));
				cast.setQuantity(rs.getInt("quantity"));
				cast.setUserid(rs.getInt("userid"));
				list.add(cast);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int insertCast(Cast cast) {
		// TODO Auto-generated method stub
		String sql1 = "select * from cast where commodityid =? and userid = ?";
		String sql = "insert into cast (CommodityID,quantity,userid) values(?,?,?)";
		int result = 0;
		try{
			this.pstmt = this.conn.prepareStatement(sql1);
			this.pstmt.setInt(1, cast.getCommodityid());
			this.pstmt.setInt(2, cast.getUserid());
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()){
				result = 2;
			}
			else{		
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, cast.getCommodityid());
			this.pstmt.setInt(2, cast.getQuantity());
			this.pstmt.setInt(3, cast.getUserid());
			result = this.pstmt.executeUpdate();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int updateCastQue(int id ,int index) {
		// TODO Auto-generated method stub
		String sql = "update cast set quantity = ? where castid = ?";
		int result =0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, index);
			this.pstmt.setInt(2, id);
			result = this.pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public List<Orders> findAllOrderByid(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` where userid = ? order by Statue";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userid);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Follow> findFlwById(int orderid) {
		// TODO Auto-generated method stub
		String sql="select * from follow where orderid = ?";
		List<Follow> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, orderid);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list= new ArrayList<Follow>();
				Follow follow = new Follow();
				follow.setFollowid(rs.getInt("followid"));
				follow.setFollowid(rs.getInt("orderid"));
				follow.setQuantity(rs.getInt("quantity"));
				follow.setCommodityid(rs.getInt("commodityid"));
				follow.setPrice(rs.getFloat("price"));
				list.add(follow);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int insertFollow(Follow follow) {
		// TODO Auto-generated method stub
		String sql = "insert into follow (orderid,quantity,commodityid,price) values (?,?,?,?)";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, follow.getOrderid());
			this.pstmt.setInt(2, follow.getQuantity());
			this.pstmt.setInt(3, follow.getCommodityid());
			this.pstmt.setFloat(4, follow.getPrice());
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int insertOrders(Orders order) {
		// TODO Auto-generated method stub
		String sql = "insert into orders (UserID,OrderDate,Statue,Totalprice) values(?,?,?,?)";
		int result=0;
		ResultSet rs = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			this.pstmt.setInt(1, order.getUserID());
			this.pstmt.setTimestamp(2, order.getOrderDate());
			this.pstmt.setInt(3, order.getStatue());
			this.pstmt.setFloat(4, order.getTotalprice());
			this.pstmt.executeUpdate();
			rs = this.pstmt.getGeneratedKeys();
            if (rs.next())  
                result = rs.getInt(1); 
            else
            	result =0;
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int updateOrderstate(int state, int orderid) {
		// TODO Auto-generated method stub
		String sql = "update orders set Statue=? where orderid=?";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, state);
			this.pstmt.setInt(2, orderid);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int dropCastByUserid(int userid) {
		// TODO Auto-generated method stub
		String sql = "delete from cast where userid = ?";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userid);
			result=this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "update users set Password = ? , UserName = ?, Phone = ?," +
				"Gender = ?,Address = ?,Birthday = ? where userid = ?";
		int result = 0;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getPassword());
			this.pstmt.setString(2, user.getUserName());
			this.pstmt.setString(3, user.getPhone());
			this.pstmt.setInt(4,user.getGender());
			this.pstmt.setString(5, user.getAddress());
			this.pstmt.setString(6,user.getBirthday());
			this.pstmt.setInt(7,user.getUserid());
			result = this.pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		try {
			this.pstmt.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return result;
	}
	@Override
	public List<Commodity> searchComByName(String name) {
		// TODO Auto-generated method stub
		String sql ="select * from commodity where  CommodityName like ?";
		List<Commodity> list = null;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, "%"+name+"%");
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				if(list==null) list = new ArrayList<Commodity>();
				Commodity com = new Commodity();
				com.setCommodityID(rs.getInt("CommodityID"));
				com.setCommodityName(rs.getString("CommodityName"));
				com.setCommodityPrice(rs.getFloat("CommodityPrice"));
				com.setDescription(rs.getString("Description"));
				com.setProductor(rs.getString("Productor"));
				com.setQuantity(rs.getInt("Quantity"));
				com.setType(rs.getInt("Type"));
				list.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Orders> findAllOrder() {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` order by Statue";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Orders> findLimitOrder(int begin, int end) {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` order by Statue limit ?,?";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, begin);
			this.pstmt.setInt(2, end);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Orders> findLimitOrderByid(int userid, int begin, int end) {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` where userid = ? order by Statue limit ?,?";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userid);
			this.pstmt.setInt(2, begin);
			this.pstmt.setInt(3, end);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public User finUserByid(int Userid) {
		// TODO Auto-generated method stub
		String sql = "select * from users where userid = ?";
		User user = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Userid);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setPrior(0);
				user.setUserid(rs.getInt("UserID"));
				user.setPassword(rs.getString("Password"));
				user.setUserName(rs.getString("UserName"));
				user.setPhone(rs.getString("Phone"));
				user.setGender(rs.getInt("Gender"));
				user.setAddress(rs.getString("Address"));
				user.setBirthday(rs.getString("Birthday"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	@Override
	public List<Classify> getLimitCsf(int begin,int end) {
		// TODO Auto-generated method stub
		String sql = "select * from classify ORDER BY classifyid limit ?,?";
		List<Classify> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, begin);
			this.pstmt.setInt(2, end);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Classify>();
				Classify csf = new Classify();
				csf.setClassifyid(rs.getInt("classifyid"));
				csf.setName(rs.getString("name"));
				list.add(csf);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int dropCsfById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from Classify where classifyid = ?";
		int result =0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			result =this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public int insertCsf(Classify csf) {
		// TODO Auto-generated method stub
		String sql = "insert into Classify(name) value (?)";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, csf.getName());
			result =this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public int updateCsf(Classify csf) {
		// TODO Auto-generated method stub
		String sql = "update Classify set name = ? where classifyid = ?";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, csf.getName());
			this.pstmt.setInt(2, csf.getClassifyid());
			result =this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public int dropComById(int comid) {
		// TODO Auto-generated method stub
		String sql = "delete from commodity where commodityid = ?";
		int result =0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, comid);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int insertCommodity(Commodity com) {
		// TODO Auto-generated method stub
		String sql = "insert into Commodity (CommodityName,CommodityPrice,Description,Productor" +
				",Quantity,Type) values (?,?,?,?,?,?)";
		int result =0;
		ResultSet rs = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			this.pstmt.setString(1, com.getCommodityName());
			this.pstmt.setFloat(2, com.getCommodityPrice());
			this.pstmt.setString(3,com.getDescription());
			this.pstmt.setString(4,com.getProductor());
			this.pstmt.setInt(5, com.getQuantity());
			this.pstmt.setInt(6, com.getType());
			this.pstmt.executeUpdate();
			rs = this.pstmt.getGeneratedKeys();
            if (rs.next())  
                result = rs.getInt(1); 
            else
            	result =0;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int updateCommodity(Commodity com) {
		// TODO Auto-generated method stub
		String sql = "update Commodity set CommodityName = ?,CommodityPrice=?,Description=?," +
				"Productor=?,Quantity=?,Type=? where commodityid=?";
		int result =0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, com.getCommodityName());
			this.pstmt.setFloat(2, com.getCommodityPrice());
			this.pstmt.setString(3,com.getDescription());
			this.pstmt.setString(4,com.getProductor());
			this.pstmt.setInt(5, com.getQuantity());
			this.pstmt.setInt(6, com.getType());
			this.pstmt.setInt(7, com.getCommodityID());
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public List<Orders> findOrderByStatue(int statue) {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` where Statue = ?";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, statue);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Orders> findOrderByBirthday(String begin, String end) {
		// TODO Auto-generated method stub
		String sql = "select * from `orders` where OrderDate > ? and OrderDate < ? order by OrderDate";
		List<Orders> list = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, begin);
			this.pstmt.setString(2, end);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Orders>();
				Orders order = new Orders();
				order.setOrderID(rs.getInt("OrderID"));
				order.setUserID(rs.getInt("UserID"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setStatue(rs.getInt("Statue"));
				order.setTotalprice(rs.getFloat("Totalprice"));
				list.add(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Word> findAllLeaveword() {
		// TODO Auto-generated method stub
		String sql="select * from leaveword order by LeaveDate";
		List<Word> list =null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Word>();
				Word word = new Word();
				word.setLeaveWordID(rs.getInt("LeaveWordID"));
				word.setUserID(rs.getInt("UserID"));
				word.setLeaveDate(rs.getTimestamp("LeaveDate"));
				word.setLeaveWord(rs.getString("LeaveWord"));
				word.setCommodityID(rs.getInt("CommodityID"));
				word.setReply(rs.getString("Reply"));
				word.setStatus(rs.getInt("Status"));
				list.add(word);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Word> findLedByComid(int Comid) {
		// TODO Auto-generated method stub
		String sql = "select * from leaveword where CommodityID = ? order by LeaveDate";
		List<Word>list=null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Comid);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Word>();
				Word word = new Word();
				word.setLeaveWordID(rs.getInt("LeaveWordID"));
				word.setUserID(rs.getInt("UserID"));
				word.setLeaveDate(rs.getTimestamp("LeaveDate"));
				word.setLeaveWord(rs.getString("LeaveWord"));
				word.setCommodityID(rs.getInt("CommodityID"));
				word.setReply(rs.getString("Reply"));
				word.setStatus(rs.getInt("Status"));
				list.add(word);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Word> findLedByStatus(int Status) {
		// TODO Auto-generated method stub
		String sql = "select * from leaveword where Status = ? order by LeaveDate";
		List<Word>list=null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Status);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Word>();
				Word word = new Word();
				word.setLeaveWordID(rs.getInt("LeaveWordID"));
				word.setUserID(rs.getInt("UserID"));
				word.setLeaveDate(rs.getTimestamp("LeaveDate"));
				word.setLeaveWord(rs.getString("LeaveWord"));
				word.setCommodityID(rs.getInt("CommodityID"));
				word.setReply(rs.getString("Reply"));
				word.setStatus(rs.getInt("Status"));
				list.add(word);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<Word> findLedByUserid(int Userid) {
		// TODO Auto-generated method stub
		String sql = "select * from leaveword where UserID = ? order by LeaveDate";
		List<Word>list=null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Userid);
			ResultSet rs = this.pstmt.executeQuery();
			while(rs.next()){
				if(list==null) list = new ArrayList<Word>();
				Word word = new Word();
				word.setLeaveWordID(rs.getInt("LeaveWordID"));
				word.setUserID(rs.getInt("UserID"));
				word.setLeaveDate(rs.getTimestamp("LeaveDate"));
				word.setLeaveWord(rs.getString("LeaveWord"));
				word.setCommodityID(rs.getInt("CommodityID"));
				word.setReply(rs.getString("Reply"));
				word.setStatus(rs.getInt("Status"));
				list.add(word);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int reduceComQuantity(int num,int comid) {
		// TODO Auto-generated method stub
		String sql = "update commodity set Quantity = Quantity - ? where CommodityID = ?";
		int result = 0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, num);
			this.pstmt.setInt(2, comid);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int dropLed(int Ledid) {
		// TODO Auto-generated method stub
		String sql = "delete from leaveword where LeaveWordID = ?";
		int result = 0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Ledid);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int insertLed(Word word) {
		// TODO Auto-generated method stub
		String sql = "insert into leaveword (UserID,LeaveDate,LeaveWord,CommodityID" +
				",Status) values (?,?,?,?,?)";
		int result=0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, word.getUserID());
			this.pstmt.setTimestamp(2, word.getLeaveDate());
			this.pstmt.setString(3, word.getLeaveWord());
			this.pstmt.setInt(4, word.getCommodityID());
			this.pstmt.setInt(5, 0);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int updateReply(String reply, int Ledid) {
		// TODO Auto-generated method stub
		String sql = "update leaveword set Reply = ?,Status=1 where LeaveWordID = ?";
		int result = 0;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1,reply);
			this.pstmt.setInt(2, Ledid);
			result = this.pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Word findWordByid(int Ledid) {
		// TODO Auto-generated method stub
		String sql="select * from leaveword where LeaveWordID = ?";
		Word word = null;
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, Ledid);
			ResultSet rs = this.pstmt.executeQuery();
			if(rs.next()){
				word = new Word();
				word.setLeaveWordID(rs.getInt("LeaveWordID"));
				word.setUserID(rs.getInt("UserID"));
				word.setLeaveDate(rs.getTimestamp("LeaveDate"));
				word.setLeaveWord(rs.getString("LeaveWord"));
				word.setCommodityID(rs.getInt("CommodityID"));
				word.setReply(rs.getString("Reply"));
				word.setStatus(rs.getInt("Status"));
			}
		}catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return word;
	}


}
