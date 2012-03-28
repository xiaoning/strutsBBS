/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 主题类定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.ResultSet;
/** 
* 主题类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class Topic {

	/** 将搜索结果中的主题和回复按时间倒序进行合并
	 * @param rs 主题搜索结果
	 * @param rs2 回复搜索结果
	 * @return 汇总的结果
	 * @throws Exception
	 */
	public static Vector<Topic> addContent(ResultSet rs,ResultSet rs2) throws Exception
	{
		Vector<Topic> Topics = new Vector<Topic>();
		Response topic=null;
		Response response=null;
		int flag=0;
		if(rs2.next()){
			response=new Response();
			response.setModifytime(rs2.getString("modifytime"));
			response.setResponseid(rs2.getInt("responseid"));
			response.setTopicid(rs2.getInt("topicid"));
			response.setTitle ( rs2.getString("title"));
			response.setAuthor ( rs2.getString("author"));
			response.setSubmittime( rs2.getString("submittime"));
			response.setAuthorid(rs2.getInt("authorid"));
			response.setForumid(rs2.getInt("forumid"));
			response.setForumname(rs2.getString("forumname"));
			response.setType(1);
		}
		boolean b1=true;
		boolean b2=true;
		boolean bb=true;
		do{
			if(flag==0){
				if(rs.next()){
					topic=new Response();
					topic.setTopicid(rs.getInt("topicid"));
					topic.setTitle(rs.getString("title"));
					topic.setAuthor(rs.getString("author"));
					topic.setViewNum(rs.getInt("viewNum"));
					topic.setResponseNum(rs.getInt("responseNum"));
					topic.setAuthorid(rs.getInt("authorid"));
					topic.setForumname(rs.getString("forumname"));
					topic.setForumid(rs.getInt("forumid"));	
					topic.setModifytime(rs.getString("modifytime"));
					topic.setType(0);
				}
				else
				{
					b1=false;
					break;
				}
			}
			else if(flag==1)
			{
				if(rs2.next()){
					response=new Response();
					response.setModifytime(rs2.getString("modifytime"));
					response.setResponseid(rs2.getInt("responseid"));
					response.setTopicid(rs2.getInt("topicid"));
					response.setTitle ( rs2.getString("title"));
					response.setAuthor ( rs2.getString("author"));
					response.setSubmittime( rs2.getString("submittime"));
					response.setAuthorid(rs2.getInt("authorid"));
					response.setForumid(rs2.getInt("forumid"));
					response.setForumname(rs2.getString("forumname"));
					response.setType(1);
				}
				else
				{
					b2=false;
					break;
				}
			}
			if(b1&&(response==null||topic.getModifytime().compareTo(response.getModifytime())>=0))
			{
				if(bb)
				{
					if(topic!=null)Topics.add(topic);
					if(response!=null)Topics.add(response);
					
				}
				if(!bb&&flag==0&&!Topics.contains(topic))Topics.add(topic);
				flag=0;
				bb=false;
				
			}
			else if(b2)
			{
				if(bb)
				{
					if(response!=null)Topics.add(response);
					if(response!=null)Topics.add(topic);
					
				}
				if(!bb&&flag==1&&!Topics.contains(response))Topics.add(response);
				flag=1;
				bb=false;

			}
			else
			{
				break;
			}
			
		}while(true);
		if(b2)
		{
			if(response!=null&&!Topics.contains(response))
				Topics.add(response);
			while(rs2.next()){
				response=new Response();
				response.setModifytime(rs2.getString("modifytime"));
				response.setResponseid(rs2.getInt("responseid"));
				response.setTopicid(rs2.getInt("topicid"));
				response.setTitle ( rs2.getString("title"));
				response.setAuthor ( rs2.getString("author"));
				response.setSubmittime( rs2.getString("submittime"));
				response.setAuthorid(rs2.getInt("authorid"));
				response.setForumid(rs2.getInt("forumid"));
				response.setForumname(rs2.getString("forumname"));
				response.setType(1);
				Topics.add(response);
			}
			
		}
		else if(b1)
		{
			if(topic!=null&&!Topics.contains(topic))
				Topics.add(topic);
			if(rs.next()){
				topic=new Response();
				topic.setTopicid(rs.getInt("topicid"));
				topic.setTitle(rs.getString("title"));
				topic.setAuthor(rs.getString("author"));
				topic.setViewNum(rs.getInt("viewNum"));
				topic.setResponseNum(rs.getInt("responseNum"));
				topic.setAuthorid(rs.getInt("authorid"));
				topic.setForumname(rs.getString("forumname"));
				topic.setForumid(rs.getInt("forumid"));	
				topic.setModifytime(rs.getString("modifytime"));
				topic.setType(0);
				Topics.add(topic);
			}
		}
		return Topics;
	}

	/** 删除主题或回复
	 * @param db 数据库
	 * @param id 编号
	 * @param type 类型
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean delete(DB db, String id,int type) throws Exception {
		String strSql;
		
		if(type==0){

			strSql = "delete from topic where topicid=" + id;
			if (db.ExecSql(strSql) == 0) {
				
				return false;
			}
			strSql = "delete from response where topicid=" + id;
			db.ExecSql(strSql); 
			
		}
		else
		{
			int topicid=0;
			strSql = "select topicid from response where responseid="+id;
			ResultSet rs = db.OpenSql(strSql);
			if(rs.next()){
				topicid=rs.getInt("topicid");
				if(topicid!=0)Topic.updateResponseNum(db,String.valueOf(topicid), -1);
			}
			strSql = "delete from response where responseid=" + id;
			if (db.ExecSql(strSql) == 0) {
				return false;
			}
			
		}
		return true;
		
	}

	/** 用编号得到主题内容
	 * @param db 数据库
	 * @param id 编号
	 * @return 主题内容
	 * @throws Exception
	 */
	public static String getContentByID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		String s="";
		strSql = "select * from topic where topicid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("content");
		}
		return s;
	}

	/**用文章编号得到版块编号
	 * @param db 数据库
	 * @param id 文章编号
	 * @return 版块编号
	 * @throws Exception
	 */
	public static int getForumidbyID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		int s=-1;
		strSql = "select * from topic where topicid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getInt("forumid");
		}
		return s;
	}

	/**用文章编号得到版块名字
	 * @param db 数据库
	 * @param id 文章编号
	 * @return 版块名字
	 * @throws Exception
	 */
	public static String getForumnamebyID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		String s=null;
		strSql = "select * from topic where topicid="+id;
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("forumname");
		}
		return s;
	}

	/** 用id得到主题的标题
	 * @param db 数据库
	 * @param id 主题编号
	 * @return 主题标题
	 * @throws Exception
	 */
	public static String getTitleByID(DB db, int id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		String s="";
		strSql = "select * from topic where topicid="+id;
		
		rs = db.OpenSql(strSql);
		if(rs.next()){
			s=rs.getString("title");
		}
		return s;
	}
	
	/**用标题搜索主题及回复
	 * @param db 数据库
	 * @param title 标题
	 * @return 搜索结果的数组
	 * @throws Exception
	 */
	public static Vector<Topic> search(DB db, String[] title) throws Exception {
		ResultSet rs;
		ResultSet rs2;
		String strSql = null;
		strSql = "select * from topic where ";
		for(int i=0;i<title.length;i++)
		{
			if(i==title.length-1) strSql+=" title like '%" + title[i]+ "%' order by topicid desc";
			else strSql+=" title like '%" + title[i]+ "%' or ";
		}
		rs = db.OpenSql(strSql);
		strSql = "select * from response where ";
		for(int i=0;i<title.length;i++)
		{
			if(i==title.length-1) strSql+=" title like '%" + title[i]+ "%' order by responseid desc";
			else strSql+=" title like '%" + title[i]+ "%' or ";
		}
		rs2=db.OpenSql(strSql);
		return addContent(rs,rs2);
		
	}
	
	/**用作者搜索主题和回复
	 * @param db 数据库
	 * @param author 作者名
	 * @return 搜索结果的数组
	 * @throws Exception
	 */
	public static Vector<Topic> searchByAuthor(DB db, String[] author) throws Exception {
		ResultSet rs;
		ResultSet rs2;
		String strSql = null;
		strSql = "select * from topic where ";
		for(int i=0;i<author.length;i++)
		{
			if(i==author.length-1) strSql+=" author like '%" + author[i]+ "%' order by topicid desc";
			else strSql+=" author like '%" + author[i]+ "%' or ";
		}
		rs = db.OpenSql(strSql);
		strSql = "select * from response where ";
		for(int i=0;i<author.length;i++)
		{
			if(i==author.length-1) strSql+=" author like '%" + author[i]+ "%' order by responseid desc";
			else strSql+=" author like '%" + author[i]+ "%' or ";
		}
		rs2=db.OpenSql(strSql);
		return addContent(rs,rs2);
	}
	
	/**用作者编号搜索文章
	 * @param db 数据库
	 * @param authorid 作者编号
	 * @return 搜索结果的数组
	 * @throws Exception
	 */
	public static Vector<Topic> searchByAuthorId(DB db, int authorid) throws Exception {
		ResultSet rs;
		String strSql = null;
		ResultSet rs2;
		strSql = "select * from topic where authorid=" + authorid;
		rs = db.OpenSql(strSql);
		strSql = "select * from response where authorid=" + authorid;
		rs2 = db.OpenSql(strSql);
		return addContent(rs,rs2);
	}
	
	/** 搜索文章内容
	 * @param db 数据库
	 * @param content 内容
	 * @return 搜索结果
	 * @throws Exception
	 */
	public static Vector<Topic> searchByContent(DB db, String[] content) throws Exception {
		ResultSet rs;
		ResultSet rs2;
		String strSql = null;
		strSql = "select * from topic where ";
		for(int i=0;i<content.length;i++)
		{
			if(i==content.length-1) strSql+=" content like '%" + content[i]+ "%' order by topicid desc";
			else strSql+=" content like '%" + content[i]+ "%' or ";
		}
		rs = db.OpenSql(strSql);
		strSql = "select * from response where ";
		for(int i=0;i<content.length;i++)
		{
			if(i==content.length-1) strSql+=" content like '%" + content[i]+ "%' order by responseid desc";
			else strSql+=" content like '%" + content[i]+ "%' or ";
		}
		rs2=db.OpenSql(strSql);
		return addContent(rs,rs2);
	}
	
	/** 更新内容
	 * @param db 数据库
	 * @param title 新标题
	 * @param newContent 新内容
	 * @param topicid 标题编号
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean updateContent(DB db, String title,String newContent,int topicid) throws Exception {
		String strSql = null;
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifytime=tempDate.format(new java.util.Date());	
		strSql = "update topic set content='" + newContent +
		"',title='"+title+ "',modifytime='"+modifytime+"' where topicid="
		+topicid;
		if(db.ExecSql(strSql)!=0) return true;
		return false;
	}
	
	/**更新回复数量
	 * @param db 数据库
	 * @param id 主题编号
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean updateResponseNum(DB db, String id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		strSql="select responseNum from topic where topicid="+id;
		rs=db.OpenSql(strSql);
		int rn;
		if(rs.next()){
			rn=rs.getInt(1)+1;
			strSql = "update topic set responseNum=" + rn + " where topicid="+ id;
		}
		if(db.ExecSql(strSql)!=0) return updateViewNum(db,id);
		return false;
	}
	/**更新回复数量
	 * @param db 数据库
	 * @param id 主题编号
	 * @param inc 更新的增量
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean updateResponseNum(DB db, String id,int inc)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		strSql="select responseNum from topic where topicid="+id;
		rs=db.OpenSql(strSql);
		int rn;
		if(rs.next()){
			rn=rs.getInt(1)+inc;
			strSql = "update topic set responseNum=" + rn + " where topicid="+ id;
		}
		if(db.ExecSql(strSql)!=0) return updateViewNum(db,id);
		return false;
	}
	/** 更新浏览量
	 * @param db 数据库
	 * @param id 主题编号
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public static boolean updateViewNum(DB db, String id)throws Exception
	{
		ResultSet rs;
		String strSql = null;
		strSql="select viewNum from topic where topicid="+id;
		rs=db.OpenSql(strSql);
		int rn=0;
		if(rs.next()){
			rn=rs.getInt(1)+1;
			strSql = "update topic set viewNum=" + rn + " where topicid="+ id;
			if(db.ExecSql(strSql)!=0) return true;
			return false;
		}
		return false;
	}

	/**
	 * 主题编号
	 */
	protected int topicid;

	/**
	 * 主题标题
	 */
	protected String title;

	/**
	 * 主题内容
	 */
	protected String content;

	/**
	 * 主题作者
	 */
	protected String author;

	/**
	 * 主题提交时间
	 */
	protected String submittime;

	/**
	 * 主题所在论坛编号
	 */
	protected int forumid;

	/**
	 * 主题查看次数
	 */
	protected int viewNum;

	/**
	 * 主题回复次数
	 */
	protected int responseNum;

	/**
	 * 主题修改时间
	 */
	protected String modifytime;

	/**
	 * 主题作者id
	 */
	protected int authorid;

	/**
	 *主题所在论坛名称 
	 */
	protected String forumname;

	/**
	 *构造函数 
	 */
	public Topic() {
	}
	
	/**得到作者名字
	 * @return 作者名字
	 */
	public String getAuthor() {
		return author;
	}

	/**得到作者编号
	 * @return 作者编号
	 */
	public int getAuthorid()
	{
		return this.authorid;
	}

	/** 得到文章内容
	 * @return 文章内容
	 */
	public String getContent() {
		return content;
	}

	/** 得到版块编号
	 * @return  版块编号
	 */
	public int getForumid() {
		return forumid;
	}

	/** 得到版块名字
	 * @return 版块名字
	 */
	public String getForumname() {
		return forumname;
	}

	/**得到文章最后修改时间
	 * @return 文章修改时间
	 */
	public String getModifytime() {
		return modifytime;
	}

	/**得到回复次数
	 * @return 回复次数
	 */
	public int getResponseNum()
	{
		return this.responseNum;
	}

	/** 得到文章发表时间
	 * @return 文章发表时间
	 */
	public String getSubmittime() {
		return submittime;
	}

	/**得到文章标题
	 * @return 文章标题
	 */
	public String getTitle() {
		return title;
	}

	/**得到文章编号
	 * @return 文章编号
	 */
	public int getTopicid() {
		return topicid;
	}

	/**得到访问次数
	 * @return 访问次数
	 */
	public int getViewNum()
	{
		return this.viewNum;
	}

	/** 在数据库插入一个新的主题
	 * @param db 数据库
	 * @return true成功，false失败
	 * @throws Exception
	 */
	public boolean insert(DB db) throws Exception {
		String strSql;
		ResultSet rs;
		int iMaxId;
		strSql = "Select max(topicid) From topic";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.getTime().toString();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		submittime=tempDate.format(new java.util.Date());
		modifytime=submittime;
		strSql = "insert into topic " +"(topicid,title,content,author,submittime," +
				"forumid,modifytime,viewNum,responseNum,authorid,forumname) "+
				"values(" + iMaxId + ",'" + title + "','"
				+ content + "','" + author + "','" + submittime + "'," + forumid
				+ ",'"+modifytime+"',"+0+","+0+","+authorid+",'"+forumname+"' )";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			strSql = "update forum set lastTopicTitle='" + title + "',lastTopicAuthor='" +
					author+"',lastTopicId="+iMaxId+",lastTopicTime='"+submittime+"' "+
					" where forumid="+ forumid;
			db.ExecSql(strSql);
			return true;
		}

	}

	/** 设置作者名字
	 * @param author 作者名字
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**设置作者编号
	 * @param id  作者编号
	 */
	public void setAuthorid(int id)
	{
		this.authorid=id;
	}
	/**设置文章内容
	 * @param content 文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**设置版块编号
	 * @param forumid  版块编号
	 */
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	/** 设置版块名字
	 * @param fourmname
	 */
	public void setForumname(String fourmname) {
		this.forumname = fourmname;
	}

	/** 设置文章最后修改时间
	 * @param modifytime 文章最后修改时间
	 */
	public void setModifytime(String modifytime) {
		this.modifytime=modifytime;
	}

	/**设置回复次数
	 * @param rn 回复次数
	 */
	public void setResponseNum(int rn)
	{
		this.responseNum=rn;
	}

	/** 设置文章发表时间
	 * @param submittime 文章发表时间
	 */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	/**设置文章标题
	 * @param title 文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**设置文章编号
	 * @param id 文章编号
	 */
	public void setTopicid(int id) {
		this.topicid = id;
	}

	/** 设置访问次数
	 * @param vn 访问次数
	 */
	public void setViewNum(int vn)
	{
		this.viewNum=vn;
	}
	

}

