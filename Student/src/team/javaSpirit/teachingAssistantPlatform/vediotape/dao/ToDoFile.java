package team.javaSpirit.teachingAssistantPlatform.vediotape.dao;

import java.io.File;

public class ToDoFile {

	public ToDoFile() {
		// TODO Auto-generated constructor stub
	}
	public void deleteFiles() {
		String relativelyPath=System.getProperty("user.dir");
		String  filePath1  =  relativelyPath+"//picture//";
		String filePath2= relativelyPath+"//voice//";
		String filePath3= relativelyPath+"//pictureToAVI//";
		File picture=new File(filePath1);
		File voice=new File(filePath2);
		File avi=new File(filePath3);
		 if (picture.isDirectory()) {
           
             File[] files = picture.listFiles();
             for (File file : files) {
              file.delete();
             }
             System.out.println("delete picture");
          }
		 if (voice.isDirectory()) {
	           
             File[] files = voice.listFiles();
             for (File file : files) {
              file.delete();
              System.out.println("delete voice");
             }
          }
		 if (avi.isDirectory()) {
	           
             File[] files = avi.listFiles();
             for (File file : files) {
              file.delete();
              System.out.println("delete avi");
             }
          }
	}
}
