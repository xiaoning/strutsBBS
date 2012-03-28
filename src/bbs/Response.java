/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �ظ��ඨ��
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
* �ظ��༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class Response extends Topic
{
	

	/** ɾ���ظ�
	 * @param db ���ݿ�
	 * @param id �ظ����
	 * @return true�ɹ� falseʧ��
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
	/**�õ��ظ�������
	 * @param db ���ݿ�
	 * @param id ���
	 * @param type ����
	 * @return ��������
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
	/** ��id�õ�����id
	 * @param db ���ݿ�
	 * @param id ���
	 * @param type ����
	 * @return ���߱��
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
	
	/** �ӱ�ŵõ�����
	 * @param db ���ݿ�
	 * @param id ���
	 * @return ����
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
	
	


	/** ��id�õ�����
	 * @param db ���ݿ�
	 * @param id ���
	 * @param type ����
	 * @return �ظ�����
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



	/**�ӱ�ŵõ�����
	 * @param db ���ݿ�
	 * @param id ���
	 * @return �ظ�����
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
	
	
	/**�õ�ĳһ�������ȫ���ظ�
	 * @param db ���ݿ�
	 * @param topicid ������
	 * @return �ظ�����
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


	/**���»ظ�����
	 * @param db ���ݿ�
	 * @param title �±���
	 * @param newContent ������
	 * @param id �ظ����
	 * @return true�ɹ� falseʧ��
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
	 * ���캯��
	 */
	public Response(){}

	/**
     * Description :���صȼ�
     * @param 
     * @return �ȼ�
     */
	public String getGrade() {
		return grade;
	}





	/**�õ��ظ����
	 * @return responseid �ظ����
	 */
	public int getResponseid() {
		return responseid;
	}

	/**
	 * @return  0Ϊ���� 1Ϊ�ظ�
	 */
	public int getType() {
		return type;
	}
	
	/**
     * Description : �����ݿ������»ظ�
     * @param db ���ݿ�
     * @return true�����ɹ� false����ʧ��
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
	/**���õȼ�
	 * @param grade ����
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/** ���ûظ����
	 * @param responseid �ظ����
	 */
	public void setResponseid(int responseid) {
		this.responseid = responseid;
	}
	/** �������� 0Ϊ���� 1Ϊ�ظ�
	 * @param type  0Ϊ���� 1Ϊ�ظ�
	 */
	public void setType(int type) {
		this.type = type;
	}

}