/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �����ඨ��
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
* �����༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class Topic {

	/** ����������е�����ͻظ���ʱ�䵹����кϲ�
	 * @param rs �����������
	 * @param rs2 �ظ��������
	 * @return ���ܵĽ��
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

	/** ɾ�������ظ�
	 * @param db ���ݿ�
	 * @param id ���
	 * @param type ����
	 * @return true�ɹ� falseʧ��
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

	/** �ñ�ŵõ���������
	 * @param db ���ݿ�
	 * @param id ���
	 * @return ��������
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

	/**�����±�ŵõ������
	 * @param db ���ݿ�
	 * @param id ���±��
	 * @return �����
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

	/**�����±�ŵõ��������
	 * @param db ���ݿ�
	 * @param id ���±��
	 * @return �������
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

	/** ��id�õ�����ı���
	 * @param db ���ݿ�
	 * @param id ������
	 * @return �������
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
	
	/**�ñ����������⼰�ظ�
	 * @param db ���ݿ�
	 * @param title ����
	 * @return �������������
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
	
	/**��������������ͻظ�
	 * @param db ���ݿ�
	 * @param author ������
	 * @return �������������
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
	
	/**�����߱����������
	 * @param db ���ݿ�
	 * @param authorid ���߱��
	 * @return �������������
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
	
	/** ������������
	 * @param db ���ݿ�
	 * @param content ����
	 * @return �������
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
	
	/** ��������
	 * @param db ���ݿ�
	 * @param title �±���
	 * @param newContent ������
	 * @param topicid ������
	 * @return true�ɹ� falseʧ��
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
	
	/**���»ظ�����
	 * @param db ���ݿ�
	 * @param id ������
	 * @return true�ɹ� falseʧ��
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
	/**���»ظ�����
	 * @param db ���ݿ�
	 * @param id ������
	 * @param inc ���µ�����
	 * @return true�ɹ� falseʧ��
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
	/** ���������
	 * @param db ���ݿ�
	 * @param id ������
	 * @return true�ɹ� falseʧ��
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
	 * ������
	 */
	protected int topicid;

	/**
	 * �������
	 */
	protected String title;

	/**
	 * ��������
	 */
	protected String content;

	/**
	 * ��������
	 */
	protected String author;

	/**
	 * �����ύʱ��
	 */
	protected String submittime;

	/**
	 * ����������̳���
	 */
	protected int forumid;

	/**
	 * ����鿴����
	 */
	protected int viewNum;

	/**
	 * ����ظ�����
	 */
	protected int responseNum;

	/**
	 * �����޸�ʱ��
	 */
	protected String modifytime;

	/**
	 * ��������id
	 */
	protected int authorid;

	/**
	 *����������̳���� 
	 */
	protected String forumname;

	/**
	 *���캯�� 
	 */
	public Topic() {
	}
	
	/**�õ���������
	 * @return ��������
	 */
	public String getAuthor() {
		return author;
	}

	/**�õ����߱��
	 * @return ���߱��
	 */
	public int getAuthorid()
	{
		return this.authorid;
	}

	/** �õ���������
	 * @return ��������
	 */
	public String getContent() {
		return content;
	}

	/** �õ������
	 * @return  �����
	 */
	public int getForumid() {
		return forumid;
	}

	/** �õ��������
	 * @return �������
	 */
	public String getForumname() {
		return forumname;
	}

	/**�õ���������޸�ʱ��
	 * @return �����޸�ʱ��
	 */
	public String getModifytime() {
		return modifytime;
	}

	/**�õ��ظ�����
	 * @return �ظ�����
	 */
	public int getResponseNum()
	{
		return this.responseNum;
	}

	/** �õ����·���ʱ��
	 * @return ���·���ʱ��
	 */
	public String getSubmittime() {
		return submittime;
	}

	/**�õ����±���
	 * @return ���±���
	 */
	public String getTitle() {
		return title;
	}

	/**�õ����±��
	 * @return ���±��
	 */
	public int getTopicid() {
		return topicid;
	}

	/**�õ����ʴ���
	 * @return ���ʴ���
	 */
	public int getViewNum()
	{
		return this.viewNum;
	}

	/** �����ݿ����һ���µ�����
	 * @param db ���ݿ�
	 * @return true�ɹ���falseʧ��
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

	/** ������������
	 * @param author ��������
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**�������߱��
	 * @param id  ���߱��
	 */
	public void setAuthorid(int id)
	{
		this.authorid=id;
	}
	/**������������
	 * @param content ��������
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**���ð����
	 * @param forumid  �����
	 */
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	/** ���ð������
	 * @param fourmname
	 */
	public void setForumname(String fourmname) {
		this.forumname = fourmname;
	}

	/** ������������޸�ʱ��
	 * @param modifytime ��������޸�ʱ��
	 */
	public void setModifytime(String modifytime) {
		this.modifytime=modifytime;
	}

	/**���ûظ�����
	 * @param rn �ظ�����
	 */
	public void setResponseNum(int rn)
	{
		this.responseNum=rn;
	}

	/** �������·���ʱ��
	 * @param submittime ���·���ʱ��
	 */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	/**�������±���
	 * @param title ���±���
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**�������±��
	 * @param id ���±��
	 */
	public void setTopicid(int id) {
		this.topicid = id;
	}

	/** ���÷��ʴ���
	 * @param vn ���ʴ���
	 */
	public void setViewNum(int vn)
	{
		this.viewNum=vn;
	}
	

}

