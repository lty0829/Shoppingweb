package Shopping.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import javax.smartcardio.CommandAPDU;


import Shopping.dao.IShoppingDAO;
import Shopping.dao.impl.ShoppingDAOImpl;
import Shopping.model.dbc.DatabaseConnection;
import Shopping.model.vo.Cast;
import Shopping.model.vo.Classify;
import Shopping.model.vo.Follow;
import Shopping.model.vo.Orders;
import Shopping.model.vo.User;
import Shopping.model.vo.Commodity;
import Shopping.model.vo.Word;

public class ShoppingDAOProxy implements IShoppingDAO{
	private DatabaseConnection dbc = null ;
    private IShoppingDAO dao = null ;
    public ShoppingDAOProxy(){
        try{
            this.dbc = new DatabaseConnection() ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        this.dao = new ShoppingDAOImpl(dbc.getConnection()) ;
    }
    
    
    
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> list = null;
		list = this.dao.getAllUser();
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Commodity> getAllCom() {
		// TODO Auto-generated method stub
		List<Commodity> list = null;
		list = this.dao.getAllCom();
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		User user = null;
		user = this.dao.login(name, password);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}



	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.save(user);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public List<Commodity> getComByClssify(int type) {
		// TODO Auto-generated method stub
		List<Commodity> list = null;
		list = this.dao.getComByClssify(type);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Commodity> getLimitCom(int begin, int end) {
		// TODO Auto-generated method stub
		List<Commodity> list = null;
		list = this.dao.getLimitCom(begin, end);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Classify> getAllCsf() {
		// TODO Auto-generated method stub
		List<Classify> list = null;
		list = this.dao.getAllCsf();
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public Classify findCsfById(int id) {
		// TODO Auto-generated method stub
		Classify csf = null;
		csf = this.dao.findCsfById(id);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csf;
	}



	@Override
	public Commodity findComById(int id) {
		// TODO Auto-generated method stub
		Commodity com = null;
		com = this.dao.findComById(id);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return com;
	}



	@Override
	public List<Commodity> getLimitComByType(int begin,int end,int type) {
		// TODO Auto-generated method stub
		List<Commodity> list = null;
		list = this.dao.getLimitComByType(begin,end,type);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int dropCastById(int id) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.dropCastById(id);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public List<Cast> getAllcastByid(int id) {
		// TODO Auto-generated method stub
		List<Cast> list = null;
		list = this.dao.getAllcastByid(id);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int insertCast(Cast cast) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.insertCast(cast);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateCastQue(int id,int index) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.updateCastQue(id,index);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public List<Orders> findAllOrderByid(int userid) {
		// TODO Auto-generated method stub
		List<Orders> list = null;
		list = this.dao.findAllOrderByid(userid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Follow> findFlwById(int orderid) {
		// TODO Auto-generated method stub
		List<Follow> list = null;
		list = this.dao.findFlwById(orderid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int insertFollow(Follow follow) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.insertFollow(follow);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int insertOrders(Orders order) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.insertOrders(order);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateOrderstate(int state, int orderid) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.updateOrderstate(state, orderid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int dropCastByUserid(int userid) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.dropCastByUserid(userid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.updateUser(user);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public List<Commodity> searchComByName(String name) {
		// TODO Auto-generated method stub
		List<Commodity> list = null;
		list = this.dao.searchComByName(name);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Orders> findAllOrder() {
		// TODO Auto-generated method stub
		List<Orders> list = null;
		list = this.dao.findAllOrder();
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Orders> findLimitOrder(int begin, int end) {
		// TODO Auto-generated method stub
		List<Orders> list = null;
		list = this.dao.findLimitOrder(begin, end);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Orders> findLimitOrderByid(int userid, int begin, int end) {
		// TODO Auto-generated method stub
		List<Orders> list = null;
		list = this.dao.findLimitOrderByid(userid, begin, end);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public User finUserByid(int Userid) {
		// TODO Auto-generated method stub
		User user = null;
		user = this.dao.finUserByid(Userid); 
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}



	@Override
	public List<Classify> getLimitCsf(int begin,int end) {
		// TODO Auto-generated method stub
		List<Classify> list = null;
		list = this.dao.getLimitCsf(begin,end);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int dropCsfById(int id) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.dropCsfById(id);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int insertCsf(Classify csf) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.insertCsf(csf);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateCsf(Classify csf) {
		// TODO Auto-generated method stub
		int result=0;
		result = this.dao.updateCsf(csf);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int dropComById(int comid) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.dropComById(comid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int insertCommodity(Commodity com) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.insertCommodity(com);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateCommodity(Commodity com) {
		// TODO Auto-generated method stub
		int result =0;
		result = this.dao.updateCommodity(com);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public List<Orders> findOrderByStatue(int statue) {
		// TODO Auto-generated method stub
		List<Orders> list =null;
		list = this.dao.findOrderByStatue(statue);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Orders> findOrderByBirthday(String begin, String end) {
		// TODO Auto-generated method stub
		List<Orders> list = null;
		list = this.dao.findOrderByBirthday(begin, end);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Word> findAllLeaveword() {
		// TODO Auto-generated method stub
		List<Word> list = null;
		list = this.dao.findAllLeaveword();
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Word> findLedByComid(int Comid) {
		// TODO Auto-generated method stub
		List<Word> list = null;
		list = this.dao.findLedByComid(Comid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Word> findLedByStatus(int Status) {
		// TODO Auto-generated method stub
		List<Word> list = null;
		list = this.dao.findLedByStatus(Status);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Word> findLedByUserid(int Userid) {
		// TODO Auto-generated method stub
		List<Word> list = null;
		list = this.dao.findLedByUserid(Userid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int reduceComQuantity(int num,int comid) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.reduceComQuantity(num,comid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertLed(Word word) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.insertLed(word);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int dropLed(int Ledid) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.dropLed(Ledid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateReply(String reply, int Ledid) {
		// TODO Auto-generated method stub
		int result = 0;
		result = this.dao.updateReply(reply, Ledid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public Word findWordByid(int Ledid) {
		// TODO Auto-generated method stub
		Word word  = null;
		word = this.dao.findWordByid(Ledid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return word;
	}



	@Override
	public Orders findorderByid(int orderid) {
		// TODO Auto-generated method stub
		Orders order = null;
		order = this.dao.findorderByid(orderid);
		try {
			this.dbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
}
