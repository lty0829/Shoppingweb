package Shopping.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shopping.factory.DAOFactory;
import Shopping.model.vo.Commodity;

import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

public class ManageCom extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageCom() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = utf-8");
		String deleteid = request.getParameter("deleteid");
		if(deleteid!=null){
			int int_delete = Integer.parseInt(deleteid);
			int result = DAOFactory.getIShoppingDAOInstance().dropComById(int_delete);
			if(result!=0){
				response.sendRedirect("product.jsp");
			}
			else{
				request.setAttribute("message", "失败");
				String str = "/manage/product.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(str);
				rd.forward(request,response);
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	private int picsize=300;
	private int smallpicsize=54;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        String isalter = request.getParameter("isalter");
        //1、创建一个DiskFileItemFactory工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //2、创建一个文件上传解析器  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //解决上传文件名的中文乱码  
        upload.setHeaderEncoding("UTF-8");   
        factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K
        String s = this.getServletContext().getRealPath("images\\product");   
        File linshi = new File(s);//当超过500K的时候，存到一个临时文件夹中  
        factory.setRepository(linshi);  
        upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M
        
        String comName="";
        String comDesc="";
        String Price="";
        String comid="";
        String type="";
        String Number="";
        String productor="";
        Commodity com =new Commodity();
        
        try {  
            // 1. 得到 FileItem 的集合 items  
            List<FileItem> /* FileItem */items = upload.parseRequest(request);  
  
            // 2. 遍历 items:  
            for (FileItem item : items) {  
                // 若是一个一般的表单域, 打印信息  
                if (item.isFormField()) {  
                    String name = item.getFieldName();  
                    String value = item.getString("utf-8");  
                    if(name.equals("productName")) {
                    	comName = value;
                    	com.setCommodityName(comName);
                    }
                    if(isalter!=null&&name.equals("productid")) {
                    	comid = value;
                    	com.setCommodityID(Integer.parseInt(comid));
                    }
                    if(name.equals("productDetail")){
                    	comDesc = value;
                    	com.setDescription(comDesc);
                    }
                    if(name.equals("productGet")){
                    	productor = value;
                    	com.setProductor(productor);
                    }
                    if(name.equals("parentId")){
                    	type = value;
                    	 com.setType(Integer.parseInt(type));
                    }
                    if(name.equals("productPrice")){
                    	Price = value;
                    	com.setCommodityPrice(Float.parseFloat(Price));
                    }
                    if(name.equals("productNumber")){
                    	Number = value;;
                    	com.setQuantity(Integer.parseInt(Number));
                    }
                                   
                }  
                // 若是文件域则把文件保存到 e:\\files 目录下.  
                else {  
                    String fileName = item.getName();  
                    long sizeInBytes = item.getSize();  
                    String extension = "";
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        extension = fileName.substring(i+1);
                    }
                    if(sizeInBytes!=0){
	                    if(extension.equals("jpg")||extension.equals("png")){
	                    	 
	                             InputStream in = item.getInputStream();  
	
	                             byte[] buffer = new byte[1024];  
	                             int len = 0;  
	                             if(isalter!=null){
	                            	 DAOFactory.getIShoppingDAOInstance().updateCommodity(com);
		                             Image srcImg = ImageIO.read(in);  
		                             BufferedImage buffImg = null;  
		                             buffImg = new BufferedImage(picsize, picsize, BufferedImage.TYPE_INT_RGB);  
		                             buffImg.getGraphics().drawImage(  
		                                     srcImg.getScaledInstance(picsize, picsize, Image.SCALE_SMOOTH), 0,  
		                                     0, null);   
		                             ImageIO.write(buffImg, "JPG", new File(s+"\\"+comid+".jpg"));
		                             
//		                             buffImg = new BufferedImage(picsize, picsize, BufferedImage.TYPE_INT_RGB);  
//		                             buffImg.getGraphics().drawImage(  
//		                                     srcImg.getScaledInstance(picsize, picsize, Image.SCALE_SMOOTH), 0,  
//		                                     0, null);   
//		                             ImageIO.write(buffImg, "JPG", new File(s1+"\\"+comid+".jpg"));
		                             
		                             
		                             
		                             buffImg = new BufferedImage(smallpicsize, smallpicsize, BufferedImage.TYPE_INT_RGB);  
		                             buffImg.getGraphics().drawImage(  
		                                     srcImg.getScaledInstance(smallpicsize, smallpicsize, Image.SCALE_SMOOTH), 0,  
		                                     0, null);   
		                             ImageIO.write(buffImg, "JPG", new File(s+"\\"+comid+"_small.jpg"));
		                             
//		                             buffImg = new BufferedImage(smallpicsize, smallpicsize, BufferedImage.TYPE_INT_RGB);  
//		                             buffImg.getGraphics().drawImage(  
//		                                     srcImg.getScaledInstance(smallpicsize, smallpicsize, Image.SCALE_SMOOTH), 0,  
//		                                     0, null);   
//		                             ImageIO.write(buffImg, "JPG", new File(s1+"\\"+comid+"_small.jpg"));
		                             response.sendRedirect("manage-result.jsp");
	                             }
	                             else{
	                            	 int result = DAOFactory.getIShoppingDAOInstance().insertCommodity(com);
	                            	 
	                            	  Image srcImg = ImageIO.read(in);  
			                             BufferedImage buffImg = null;  
			                             buffImg = new BufferedImage(picsize, picsize, BufferedImage.TYPE_INT_RGB);  
			                             buffImg.getGraphics().drawImage(  
			                                     srcImg.getScaledInstance(picsize, picsize, Image.SCALE_SMOOTH), 0,  
			                                     0, null);   
			                             ImageIO.write(buffImg, "JPG", new File(s+"\\"+result+".jpg"));
			                             
//			                             buffImg = new BufferedImage(picsize, picsize, BufferedImage.TYPE_INT_RGB);  
//			                             buffImg.getGraphics().drawImage(  
//			                                     srcImg.getScaledInstance(picsize, picsize, Image.SCALE_SMOOTH), 0,  
//			                                     0, null);   
//			                             ImageIO.write(buffImg, "JPG", new File(s1+"\\"+result+".jpg"));
			                             
			                             
			                             
			                             buffImg = new BufferedImage(smallpicsize, smallpicsize, BufferedImage.TYPE_INT_RGB);  
			                             buffImg.getGraphics().drawImage(  
			                                     srcImg.getScaledInstance(smallpicsize, smallpicsize, Image.SCALE_SMOOTH), 0,  
			                                     0, null);   
			                             ImageIO.write(buffImg, "JPG", new File(s+"\\"+result+"_small.jpg"));
			                             
//			                             buffImg = new BufferedImage(smallpicsize, smallpicsize, BufferedImage.TYPE_INT_RGB);  
//			                             buffImg.getGraphics().drawImage(  
//			                                     srcImg.getScaledInstance(smallpicsize, smallpicsize, Image.SCALE_SMOOTH), 0,  
//			                                     0, null);   
//			                             ImageIO.write(buffImg, "JPG", new File(s1+"\\"+result+"_small.jpg"));
			                             response.sendRedirect("manage-result.jsp");
	                            	 
	                             }
	                             in.close();          
	                    }
	                    else{
	                    	request.setAttribute("message", "不是图片");
	                    	response.sendRedirect("product.jsp");
	                    }
                    }
                    else if(sizeInBytes==0){
                    	if(isalter!=null){
                    		DAOFactory.getIShoppingDAOInstance().updateCommodity(com);
                    		response.sendRedirect("manage-result.jsp");
                    	}
                    }
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
