package Shopping.factory;

import Shopping.dao.IShoppingDAO;
import Shopping.dao.proxy.ShoppingDAOProxy;



public class DAOFactory {
	public static IShoppingDAO getIShoppingDAOInstance(){
        return new ShoppingDAOProxy() ;
    }
}
