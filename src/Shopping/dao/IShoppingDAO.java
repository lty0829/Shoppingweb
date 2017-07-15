package Shopping.dao;

import java.util.List;

import Shopping.model.vo.*;

public interface IShoppingDAO {
	//查找全部用户
	public List<User> getAllUser();
	//查找全部商品
	public List<Commodity> getAllCom();
	//登陆
	public User login(String name,String password);
	//根据userid找用户
	public User finUserByid(int Userid);
	//注册
	public int save(User user);
	//修改信息
	public int updateUser(User user);
	//根据类型找商品
	public List<Commodity> getComByClssify(int type);
	//限制找商品
	public List<Commodity> getLimitCom(int begin,int end);
	//得到所有类型
	public List<Classify> getAllCsf();
	//限制找分类
	public List<Classify> getLimitCsf(int begin,int end);
	//根据id找类型
	public Classify findCsfById(int id);
	//根据id删除类型
	public int dropCsfById(int id);
	//增加类型
	public int insertCsf(Classify csf);
	//修改类型
	public int updateCsf(Classify csf);
	//根据id找商品
	public Commodity findComById(int id);
	//不同类型限制找商品
	public List<Commodity> getLimitComByType(int begin,int end,int type);
	//找到所有购物车根据id
	public List<Cast> getAllcastByid(int id);
	//根据castid删除购物车
	public int dropCastById(int id);
	//根据userid删除购物车
	public int dropCastByUserid(int userid);
	//添加购物车
	public int insertCast(Cast cast);
	//更改购物车数量
	public int updateCastQue(int id,int index);
	//插入follow
	public int insertFollow(Follow follow);
	//查找所有订单通过userid
	public List<Orders> findAllOrderByid(int userid);
	//查找所有订单
	public List<Orders> findAllOrder();
	//有限制查找订单
	public List<Orders> findLimitOrder(int begin,int end);
	//有限制根据userid查找订单
	public List<Orders> findLimitOrderByid(int userid,int begin,int end); 
	//根据日期范围查找
	public List<Orders> findOrderByBirthday(String begin,String end);
	//根据状态查找订单
	public List<Orders> findOrderByStatue(int statue);
	//插入订单
	public int insertOrders(Orders order);
	//根据orderid找follow
	public List<Follow> findFlwById(int orderid);
	//修改订单的状态
	public int updateOrderstate(int state,int orderid);
	//根据名字搜索商品
	public List<Commodity> searchComByName(String name);
	//根据comid删商品
	public int dropComById(int comid);
	//修改商品id
	public int updateCommodity(Commodity com);
	//添加商品
	public int insertCommodity(Commodity com);
	//查找所有评论
	public List<Word> findAllLeaveword();
	//根据用户id找评论
	public List<Word> findLedByUserid(int Userid);
	//根据商品id找评论
	public List<Word> findLedByComid(int Comid);
	//根据状态找评论
	public List<Word> findLedByStatus(int Status);
	//减少商品的数量
	public int reduceComQuantity(int num,int comid);
	//提交评论
	public int insertLed(Word word);
	//删除评论
	public int dropLed(int Ledid);
	//回复
	public int updateReply(String reply,int Ledid);
	//根据id找评论
	public Word findWordByid(int Ledid);
	//根据id找订单
	public Orders findorderByid(int orderid);
}
