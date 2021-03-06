package com.gem.xianzhi.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.gem.xianzhi.Biz.VideoBiz;
import com.gem.xianzhi.pojo.User;
import com.gem.xianzhi.pojo.Video;
import com.opensymphony.xwork2.ActionContext;

public class uploadVideoAction {	
	 private File picture;//图片
	 private String pictureFileName;//图片名称
	 private String pictureContentType;//图片类型
	 
	 private File file;  //视频文件
	 private String fileFileName;  //视频名称
	 private String fileContentType;  //视频类型

	 private String title;
	 private String detail;
	 private Video video;
	 private VideoBiz videoBiz;
	 
	  
	public String upload(){
		User currUser =(User) ActionContext.getContext().getSession().get("currUser");				
	    File destFile = null;
	    File destPicture = null;

        String savePath =ServletActionContext.getServletContext().getRealPath("/video"); //视频保存路径
        String picturePath = ServletActionContext.getServletContext().getRealPath("/newsPicture");//图片保存路径
        String uuid = UUID.randomUUID().toString();//避免重名
        
        int last = fileFileName.lastIndexOf(".");  
        String head = fileFileName.substring(0,last)+uuid;//视频名称  
        String type = fileFileName.substring(last);//视频类型
        String fileName = head+type;//视频全名
        
        int  last1 = pictureFileName.lastIndexOf(".");
        String head1 = pictureFileName.substring(0,last1)+uuid;
        String type1 = pictureFileName.substring(last1);
        String pictureName = head1+type1;//图片全名
        
        try {
            InputStream is = new FileInputStream(file);
            InputStream is1 = new FileInputStream(picture);
            
            destFile = new File(savePath,fileName);
            destPicture = new File(picturePath, pictureName);
            FileUtils.copyFile(file, destFile);   
            FileUtils.copyFile(picture,destPicture);
            
            OutputStream os = new FileOutputStream(destFile);
            OutputStream os1 = new FileOutputStream(destPicture);
            
            byte[] buffer = new byte[1024];
            int length  = 0 ;
            while((length = is.read(buffer))>0){
                os.write(buffer, 0, length);
            }            
            os.flush();
            is.close();
            os.close();           
            byte []buffer1 = new byte[1024];
            int length1 = 0;
            while((length1 = is1.read(buffer1))>0){
            	os1.write(buffer1,0,length1);
            }
            os1.flush();
            is1.close();
            os1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       video.setAuthor(currUser);
       video.setAudit("未审核");
       video.setClickCount(0);
       video.setDate(new Date());
       video.setDiscussCount(0);
       video.setPicture("newsPicture/"+pictureName);
       video.setSrc("video/"+fileName);
       video.setType(fileContentType);
       int id = videoBiz.uploadVidel(video);
       if(id!=0){
    	   ActionContext.getContext().put("msg", "上传成功");
    	   return "uploadVideoSuccess";
       }else{
    	   ActionContext.getContext().put("msg", "上传失败");
    	   return "uploadVideoFail";
       }
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public VideoBiz getVideoBiz() {
		return videoBiz;
	}

	public void setVideoBiz(VideoBiz videoBiz) {
		this.videoBiz = videoBiz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

}
