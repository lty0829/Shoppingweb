package Shopping.dao;

import java.util.List;

import Shopping.model.vo.*;

public interface IShoppingDAO {
	//����ȫ���û�
	public List<User> getAllUser();
	//����ȫ����Ʒ
	public List<Commodity> getAllCom();
	//��½
	public User login(String name,String password);
	//����userid���û�
	public User finUserByid(int Userid);
	//ע��
	public int save(User user);
	//�޸���Ϣ
	public int updateUser(User user);
	//������������Ʒ
	public List<Commodity> getComByClssify(int type);
	//��������Ʒ
	public List<Commodity> getLimitCom(int begin,int end);
	//�õ���������
	public List<Classify> getAllCsf();
	//�����ҷ���
	public List<Classify> getLimitCsf(int begin,int end);
	//����id������
	public Classify findCsfById(int id);
	//����idɾ������
	public int dropCsfById(int id);
	//��������
	public int insertCsf(Classify csf);
	//�޸�����
	public int updateCsf(Classify csf);
	//����id����Ʒ
	public Commodity findComById(int id);
	//��ͬ������������Ʒ
	public List<Commodity> getLimitComByType(int begin,int end,int type);
	//�ҵ����й��ﳵ����id
	public List<Cast> getAllcastByid(int id);
	//����castidɾ�����ﳵ
	public int dropCastById(int id);
	//����useridɾ�����ﳵ
	public int dropCastByUserid(int userid);
	//��ӹ��ﳵ
	public int insertCast(Cast cast);
	//���Ĺ��ﳵ����
	public int updateCastQue(int id,int index);
	//����follow
	public int insertFollow(Follow follow);
	//�������ж���ͨ��userid
	public List<Orders> findAllOrderByid(int userid);
	//�������ж���
	public List<Orders> findAllOrder();
	//�����Ʋ��Ҷ���
	public List<Orders> findLimitOrder(int begin,int end);
	//�����Ƹ���userid���Ҷ���
	public List<Orders> findLimitOrderByid(int userid,int begin,int end); 
	//�������ڷ�Χ����
	public List<Orders> findOrderByBirthday(String begin,String end);
	//����״̬���Ҷ���
	public List<Orders> findOrderByStatue(int statue);
	//���붩��
	public int insertOrders(Orders order);
	//����orderid��follow
	public List<Follow> findFlwById(int orderid);
	//�޸Ķ�����״̬
	public int updateOrderstate(int state,int orderid);
	//��������������Ʒ
	public List<Commodity> searchComByName(String name);
	//����comidɾ��Ʒ
	public int dropComById(int comid);
	//�޸���Ʒid
	public int updateCommodity(Commodity com);
	//�����Ʒ
	public int insertCommodity(Commodity com);
	//������������
	public List<Word> findAllLeaveword();
	//�����û�id������
	public List<Word> findLedByUserid(int Userid);
	//������Ʒid������
	public List<Word> findLedByComid(int Comid);
	//����״̬������
	public List<Word> findLedByStatus(int Status);
	//������Ʒ������
	public int reduceComQuantity(int num,int comid);
	//�ύ����
	public int insertLed(Word word);
	//ɾ������
	public int dropLed(int Ledid);
	//�ظ�
	public int updateReply(String reply,int Ledid);
	//����id������
	public Word findWordByid(int Ledid);
}
