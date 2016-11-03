package com.runtech.web.dispatcher;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.runtech.util.MailSender;
import com.runtech.web.action.StrutsAction;
import com.runtech.web.form.ModelForm;
import com.runtech.web.model.Merchant;
import com.runtech.web.model.User;
import com.runtech.web.runtime.RuntechProperties;
import com.runtech.web.util.Constant;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Harry
 *
 */
public abstract class RuntechContext {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext servletContext;
	private HttpSession session;
	private String error;
	private String result;
	private String nextLocation;
	private StrutsAction action;
	private User user;
	private Merchant merchant;
	private boolean actionSucceed =false;
	private ModelForm modelForm;
	private Map<String,String> errors = new HashMap<String,String>();
	private MailSender mailSender = new MailSender(RuntechProperties.getProperties());
	private Boolean ajax;

	public boolean isActionSucceed() {
		return actionSucceed;
	}

	public void setActionSucceed(boolean actionSucceed) {
		this.actionSucceed = actionSucceed;
	}

	protected static final Logger LOG = Logger.getLogger(RuntechContext.class);
	private static final String WATERMARK_IMAGE_PATH = RuntechProperties.getWatermarkPath();
	public static final String KEYWORD_CONTEXT = "context";
	public static final String KEYWORD_COMPONENT_RESULT = "result";
	public static final String REGULATION_CODE_DISCOUNT = "DISCOUNT";
	public static final String REGULATION_CODE_FREIGHT = "FREIGHT";
	public static final String REGULATION_CODE_POINT = "POINT";
	public static final String REGULATION_CODE_LEVEL = "LEVEL";
	public static final String REGULATION_CODE_RECOMMEND = "RECOMMEND";
	public static final String REGULATION_CODE_REGISTER = "REGISTER";
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest requestValue) {
		this.request = requestValue;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse responseValue) {
		this.response = responseValue;
	}

	public abstract void destory();

	public void setServletContext(ServletContext servletContextValue) {
		this.servletContext = servletContextValue;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		if(this.session!=null){
			return session;
		}else if(this.request!=null){
			return this.request.getSession(true);
		}
		return null;
	}
	
	public File getFile(String filePath){
		if(filePath!=null){
			ServletContext servletContext = getServletContext();
			String realPath = servletContext.getContext(this.action.getUploadContext()).getRealPath(filePath);
			File file = new File(realPath);
			return file;
		}else{
			return null;
		}

	}
	public String extractAllImages(String content){
		try {
			Document doc = Jsoup.parse(content);
			Elements imgs = doc.select("img");
			for (Element img : imgs) {

	            String imgSrc = img.attr("src");
	            if (imgSrc != null&&imgSrc.toLowerCase().startsWith("http")) {
	                    String downloadImagePath = downloadImage(imgSrc);
	                    img.attr("src", downloadImagePath); // or whatever
	            }
	        }
	        return doc.body().html();
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
			return null;
		}
	}
	
    public String downloadImage(String imgSrc) throws IOException {
        try {
            String fileName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            URL imageUrl = new URL(imgSrc);
            return saveImage(imageUrl, fileName, false);
        } catch (Exception ex) {
			LOG.error(ex.getMessage());
        }
        return null;

    }
	public String saveImage(File file, String fileName, boolean waterMark) {
		ServletContext servletContext = getServletContext();
		try {
			String realPath = servletContext.getContext(this.action.getUploadContext()).getRealPath(this.action.getUploadImagePath());
			long currentTimeMillis = System.currentTimeMillis();
			String surfix = fileName.substring(fileName.lastIndexOf(".")+1);
			
			File newFile = new File(realPath+"/"+currentTimeMillis+"."+surfix);
			FileUtils.copyFile(file, newFile);
			if(waterMark){
				textWatermark(newFile);
				imageWaterMark(newFile);
			}
			return this.action.getUploadContext()+this.action.getUploadImagePath()+"/"+newFile.getName();
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}
	
	public String saveImage(URL url, String fileName, boolean waterMark) {
		ServletContext servletContext = getServletContext();
		try {
			String realPath = servletContext.getContext(this.action.getUploadContext()).getRealPath(this.action.getUploadImagePath());
			long currentTimeMillis = System.currentTimeMillis();
			String surfix = fileName.substring(fileName.lastIndexOf(".")+1);
			
			File newFile = new File(realPath+"/"+currentTimeMillis+"."+surfix);
			FileUtils.copyURLToFile(url, newFile);
			if(waterMark){
				textWatermark(newFile);
				imageWaterMark(newFile);
			}
			return this.action.getUploadContext()+this.action.getUploadImagePath()+"/"+newFile.getName();
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}
    public void zoom(File imageFile, int outputWidth, int outputHeight, String newName, Boolean proportion) throws Exception {  
        try {  

			String realPath = servletContext.getContext(this.action.getUploadContext()).getRealPath(this.action.getUploadImagePath());
            File todir = new File(realPath);  
            if (!todir.exists()) {  
                todir.mkdir();  
            }  
  
            if (!imageFile.isFile())  
                throw new Exception(imageFile + " is not image file error in CreateThumbnail!");  

			Image img = ImageIO.read(imageFile);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
                throw new Exception(imageFile + " is not image file error in CreateThumbnail!"); 
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(realPath
						+"/"+ newName);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
		        param.setQuality(0.9f, false);
				encoder.encode(tag, param);
				out.close();
			}
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new Exception(e);  
        }  
    }  
  
    /** 
     * add text water mark
     *  
     * @return 
     * @throws Exception 
     * @throws Exception 
     */  
    public void textWatermark(File img) throws Exception {  
        try {  
            if (!img.exists()) {  
                throw new IllegalArgumentException("file not found!");  
            }  
  
            // 创建一个FileInputStream对象从源图片获取数据流  
            FileInputStream sFile = new FileInputStream(img);  
  
            // 创建一个Image对象并以源图片数据流填充  
            Image src = ImageIO.read(sFile);  
  
            // 得到源图宽  
            int width = src.getWidth(null);  
            // 得到源图长  
            int height = src.getHeight(null);  
  
            // 创建一个BufferedImage来作为图像操作容器  
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            // 创建一个绘图环境来进行绘制图象  
            Graphics2D g = image.createGraphics();  
            // 将原图像数据流载入这个BufferedImage  
            g.drawImage(src, 0, 0, width, height, null);  
            // 设定文本字体  
            g.setFont(new Font("Arial", Font.BOLD, height/20));  
//            String rand = "www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com www.beingfruit.com ";  
            String rand = RuntechProperties.getWatermarkText();
            // 设定文本颜色  
            g.setColor(Color.white);  
            // 设置透明度  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));  
            // 向BufferedImage写入文本字符,水印在图片上的坐标  
            g.drawString(rand, 0, height/2);  
            // 使更改生效  
            g.dispose();  
            // 创建输出文件流  
            FileOutputStream outi = new FileOutputStream(img);  
            // 创建JPEG编码对象  
            JPEGImageEncoder encodera = JPEGCodec.createJPEGEncoder(outi);  
            // 对这个BufferedImage (image)进行JPEG编码  
            encodera.encode(image);  
            // 关闭输出文件流  
            outi.close();  
            sFile.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new Exception(e);  
        }  
    }  
  
    /** 
     * 添加图片水印 
     *  
     */  
    public void imageWaterMark(File imgFile){  
        try {  
            // 目标文件  
            Image src = ImageIO.read(imgFile);  
            int width = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = image.createGraphics();  
            g.drawImage(src, 0, 0, width, height, null);  
              
            // 水印文件 路径  
            String waterImgPath = this.servletContext.getRealPath(WATERMARK_IMAGE_PATH);  
            File waterFile = new File(waterImgPath);  
            Image waterImg = ImageIO.read(waterFile);  
              
            int zoomWidth = (int) (width * 0.3);
            int zoomHeight = (int) (height * 0.3);
            float srcRate = width/height;
            int w_width = waterImg.getWidth(null);  
            int w_height = waterImg.getHeight(null);  
            float rate = (float)w_width/(float)w_height;
            if(srcRate>rate){
            	zoomWidth = (int) (zoomHeight * rate);
            }else{
            	zoomHeight = (int) (zoomWidth / rate);
            }
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.7f));  
            g.drawImage(waterImg, (width - zoomWidth), (height - zoomHeight), zoomWidth, zoomHeight, null);  
            // 水印文件结束  
              
            g.dispose();  
            FileOutputStream out = new FileOutputStream(imgFile);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            JPEGEncodeParam jpgParam = encoder.getDefaultJPEGEncodeParam(image);
            jpgParam.setQuality(100f, true);
            encoder.encode(image, jpgParam);  
            out.close();  
        } catch (Exception e) {  
            LOG.error(e);
        }  
    }

	public String getParameter(String parameterName) {
		if(this.request!=null){
			return this.request.getParameter(parameterName);
		}else{
			return null;
		}
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
		if(this.action!=null){
			this.action.addActionError(error);
		}
	}
	
	public String getError(String key) {
		if(this.errors!=null){
			return (String) this.errors.get(key);
		}else{
			return null;
		}
	}

	public void setError(String key, String error) {
		if(this.errors!=null){
			this.errors.put(key, error);
		}
		if(this.action!=null){
			this.action.addActionError(error);
		}
	}
	
	public void setErrorByKey(String key) {
		if(this.action!=null){
			this.action.addActionError(this.action.getText(key));
		}
	}
	
	public void saveInSession(String key, Object object) {
		this.getSession().setAttribute(key, object);
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNextLocation() {
		return this.nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}

	public StrutsAction getAction() {
		return this.action;
	}

	public void setAction(StrutsAction action) {
		this.action = action;
	}

	public void setUser(User user) {
		this.user = user;
		session.setAttribute(Constant.SESSION_USER, user);
		session.setAttribute(Constant.SESSION_CHECK_SCRIPT, false);
	}

	public User getUser() {
		this.user = (User) session.getAttribute(Constant.SESSION_USER);
		return user;
	} 
	
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public void setModel(ModelForm modelForm) {
		this.modelForm = modelForm;
	}

	public ModelForm getModel() {
		return modelForm;
	}
	
	public boolean sendMail(String toRecipient,String ccRecipient, String subject, String body, String fileAffix){
		mailSender.setToRecipient(toRecipient);
		mailSender.setCcRecipient(ccRecipient);
		mailSender.setSubject(subject);
		mailSender.setBody(body);
		if(fileAffix!=null){
			mailSender.addFileAffix(fileAffix);
		}
		return mailSender.send();
	}

	public void outputBlob(Blob contractFile, String contractFileName) {
		try {
			HttpServletResponse response = getResponse();
			if(contractFileName==null){
				contractFileName = "untitled";
			}
			response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(contractFileName,"UTF-8"));
			InputStream bs = contractFile.getBinaryStream();
			
			byte[] buf = new byte[1024];
			int len = 0;
			ServletOutputStream outputStream = response.getOutputStream();
			while((len=bs.read(buf))>0){      
				outputStream.write(buf,0,len);
			}
			outputStream.close();
		} catch (SQLException e) {
			setError(e.getMessage());
		} catch (IOException e) {
			setError(e.getMessage());
		}
		setResult(Constant.RESULT_NULL);
	}
	public String[] getParameters(String parameterName) {
		if(this.request!=null){
			return this.request.getParameterValues(parameterName);
		}else{
			return null;
		}
	}

	public void setAjax(Boolean ajax) {
		// TODO Auto-generated method stub
		this.ajax = ajax;
	}

	public Boolean getAjax() {
		// TODO Auto-generated method stub
		return this.ajax;
	}
	
}
