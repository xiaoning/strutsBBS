/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 回复类定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/  
package bbs;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
/** 
* 回复类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class Response extends Topic
{
	

	/** 删除回复
	 * @param db 数据库
	 * @param id 回复编号
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean delete(DB db, int id) throws Exception {
		String strSql;
		strSql = "delete from response where responseid=" + id;
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**得到回复的作者
	 * @param db 数据库
	 * @param id 编号
	 * @param type 类型
	 * @return 作者名字
	 * @throws Exception
	 */
	public static String getAuthorById(DB db,String id,int type) throws Exception
	{
		ResultSet rs;
		String strSql = null;
		String s="";
		if(type==0)
			strSql = "select * from topic where topicid="+id;
		else 
			strSql = "select * from response where responseid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("author");
		}
		return s;
	}
	/** 用id得到作者id
	 * @param db 数据库
	 * @param id 编号
	 * @param type 类型
	 * @return 作者编号
	 * @throws Exception
	 */
	public static int getAuthoridById(DB db,String id,int type) throws Exception
	{
		ResultSet rs;
		String strSql = null;
		int s=0;
		if(type==0)
			strSql = "select * from topic where topicid="+id;
		else 
			strSql = "select * from response where responseid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getInt("authorid");
		}
		return s;
	}
	
	/** 从编号得到内容
	 * @param db 数据库
	 * @param id 编号
	 * @return 内容
	 * @throws Exception
	 */
	public static String getContentByID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		strSql = "select * from response where responseid="+id;
		String s="";
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("content");
		}
		return s;
	}
	
	


	/** 用id得到标题
	 * @param db 数据库
	 * @param id 编号
	 * @param type 类型
	 * @return 回复标题
	 * @throws Exception
	 */
	public static String getTitleById(DB db,String id,int type) throws Exception
	{
		ResultSet rs;
		String strSql = null;
		String s="";
		if(type==0)
			strSql = "select * from topic where topicid="+id;
		else 
			strSql = "select * from response where responseid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("title");
		}
		return s;
	}



	/**从编号得到标题
	 * @param db 数据库
	 * @param id 编号
	 * @return 回复标题
	 * @throws Exception
	 */
	public static String getTitleByID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;

		strSql = "select * from response where responseid="+id;
		String s="";
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("title");
		}
		return s;
	}
	
	
	/**得到某一个主题的全部回复
	 * @param db 数据库
	 * @param topicid 主题编号
	 * @return 回复数组
	 * @throws Exception
	 */
	public static Vector<Topic> search(DB db ,int topicid) throws Exception{
		Vector<Topic> Contents = new Vector<Topic>();
		ResultSet rs,rsNest;
        String strSql=null;
		
        strSql = "select * from topic where topicid=" + topicid;
		rs = db.OpenSql(strSql);
		
		if (rs.next()){
			Response response = new Response();
			
			response.setTopicid(rs.getInt("topicid"));
			response.setTitle ( rs.getString("title"));
			response.setContent ( rs.getString("content"));
			response.setAuthor( rs.getString("author"));
			response.setSubmittime( rs.getString("submittime"));
			response.setTopicid ( topicid);
			response.setModifytime(rs.getString("modifytime"));
			response.setAuthorid(rs.getInt("authorid"));
			response.setForumid(rs.getInt("forumid"));
			response.setForumname(rs.getString("forumname"));
        	strSql = "select * from user where username='" + response.getAuthor() + "'";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()){
				response.setGrade(rsNest.getString("grade"));
			}
			
			Contents.add(response);
		}
					
        strSql = "select * from response where topicid=" + topicid + " order by submittime";
		rs = db.OpenSql(strSql);

		while(rs.next()){
		Response	response = new Response();
			
			response.setResponseid(rs.getInt("responseid"));
			response.setTitle ( rs.getString("title"));
			response.setContent ( rs.getString("content"));
			response.setAuthor ( rs.getString("author"));
			response.setSubmittime( rs.getString("submittime"));
			response.setTopicid ( topicid);
			response.setModifytime(rs.getString("modifytime"));
			response.setAuthorid(rs.getInt("authorid"));
			response.setForumid(rs.getInt("forumid"));
			response.setForumname(rs.getString("forumname"));
        	strSql = "select * from user where username='" + response.getAuthor() + "'";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()){
				response.setGrade(rsNest.getString("grade"));
			}
			
			Contents.add(response);
		}			
		return Contents;
	}


	/**更新回复内容
	 * @param db 数据库
	 * @param title 新标题
	 * @param newContent 新内容
	 * @param id 回复编号
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean updateContent(DB db, String title,String newContent,int id) throws Exception {
		String strSql = null;
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifytime=tempDate.format(new java.util.Date());
		strSql = "update response set content='" +
		newContent +"',title='"+title+
		"',modifytime='"+modifytime+
		"' where responseid="
		+id;
		if(db.ExecSql(strSql)!=0) return true;
		return false;
	}


	private int responseid ;
	private String grade;

	private int type;

	/**
	 * 构造函数
	 */
	public Response(){}

	/**
     * Description :返回等级
     * @param 
     * @return 等级
     */
	public String getGrade() {
		return grade;
	}





	/**得到回复编号
	 * @return responseid 回复编号
	 */
	public int getResponseid() {
		return responseid;
	}

	/**
	 * @return  0为主题 1为回复
	 */
	public int getType() {
		return type;
	}
	
	/**
     * Description : 在数据库增加新回复
     * @param db 数据库
     * @return true操作成功 false操作失败
     */
	public boolean insert(DB db) throws Exception{
        String strSql;
		ResultSet rs;
		int iMaxId;
        strSql = "select max(responseid) from response";
		rs = db.OpenSql(strSql);  
		if ( rs.next()) {
			iMaxId=rs.getInt(1)+1;
		}
		else{
			iMaxId=1;
		}
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		submittime=tempDate.format(new java.util.Date());
		modifytime=submittime;
        strSql = "insert into response " +" (responseid,title,content,author,submittime," +
        		"topicid,modifytime,authorid,forumid,forumname) "+
        		"values(" 
        		+ iMaxId 	+",'"
				+ title 	+"','"
				+ content 	+"','"
				+ author 	+"','"
				+ submittime+"',"
				+ topicid	+",'"
				+ modifytime+"',"
				+ authorid+","
				+forumid+",'"
				+forumname+"'"
				+")";
		if ( db.ExecSql(strSql)==0) {
			return false;
		}
		else{
			return true;
		}
	
	}
	/**设置等级
	 * @param grade 级别
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/** 设置回复编号
	 * @param responseid 回复编号
	 */
	public void setResponseid(int responseid) {
		this.responseid = responseid;
	}
	/** 设置类型 0为主题 1为回复
	 * @param type  0为主题 1为回复
	 */
	public void setType(int type) {
		this.type = type;
	}

}