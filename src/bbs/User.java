/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �û��ඨ��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/   
package bbs;

import java.sql.ResultSet;
import java.util.Vector;
/** 
* �û��༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class User {

	/**����û��Ƿ����
	 * @param db ���ݿ�
	 * @param username �û���
	 * @return true�Ѵ��� false������
	 * @throws Exception
	 */
	public static boolean checkUser(DB db, String username)  throws Exception
	{
		String strSql;
		ResultSet rs;
		strSql = "select * from user where username='" + username
				+ "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}


	/**����û����������Ƿ�ƥ��
	 * @param db ���ݿ�
	 * @param username �û��� 
	 * @param password ����
	 * @return true ƥ�� false��ƥ��
	 * @throws Exception
	 */
	public static boolean checkUser(DB db, String username, String password)
			throws Exception {
		String strSql;
		ResultSet rs;
		strSql = "select * from user where username='" + username
				+ "' and password='" + password + "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}

	}

	/** ɾ��ĳ���û�
	 * @param db ���ݿ�
	 * @param username �û���
	 * @return true�����ɹ� false����ʧ��
	 * @throws Exception
	 */
	public static boolean delete(DB db, String username) throws Exception {
		String strSql;
		strSql = "delete from user where username='" + username + "'";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}


	/** �༭�û��ȼ�
	 * @param db  ���ݿ�
	 * @param username �û���
	 * @param grade ����
	 * @param forumid ���ID
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean edit(DB db, String username, String grade, String forumid)
			throws Exception {
		String strSql;
		strSql = "update user set grade='" + grade + "' where username='"
				+ username + "'";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			if (!grade.equals("manager")) {
				strSql = "update forum set manager='" + username + "' where id=" + forumid;
				db.ExecSql(strSql);
			}
			return true;
		}
	}


	/**���û����õ��û���id
	 * @param db ���ݿ�
	 * @param username �û���
	 * @return �û�id ��-1Ϊ�����ڣ�
	 * @throws Exception
	 */
	public static int getId(DB db,String username) throws Exception
	{
		String strSql;
		strSql="select id from user where username='"+username+"'";
		ResultSet rs;
		rs = db.OpenSql(strSql);
		if(rs.next()) {
			int tid=rs.getInt(1);
			return tid;
		}
		
		return -1;
	}
	

	/**���û�id�õ��û�������
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @return �û�����
	 * @throws Exception
	 */
	public static String getNamebyID(DB db,int  id) throws Exception
	{
		String strSql;
		strSql="select username from user where id="+id;
		ResultSet rs;
		rs = db.OpenSql(strSql);
		if(rs.next()) {
			String tname=rs.getString(1);
			return tname;
		}
		
		return "";
	}
	
	/**���û����õ��û��ȼ�
	 * @param db ���ݿ�
	 * @param username �û���
	 * @return �û��ȼ�
	 * @throws Exception
	 */
	public static String getUserGrade(DB db, String username) throws Exception {
		String strSql;
		ResultSet rs;
		strSql = "select * from user where username='" + username + "'";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			return rs.getString("grade");
		} else {
			return null;
		}

	}
	

	
	/** ���û��������û�
	 * @param db ���ݿ�
	 * @param username �û����ؼ���
	 * @return �����������
	 * @throws Exception
	 */
	public static Vector<User> search(DB db, String username) throws Exception {
		Vector<User> Users = new Vector<User>();
		ResultSet rs;
		String strSql = null;

		strSql = "select * from user where username like '%" + username + "%'";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setGrade(rs.getString("grade"));

			Users.add(user);
		}
		return Users;
	}
	
	/**�ҳ������û���Ϣ
	 * @param db ���ݿ� 
	 * @return �û���Ϣ����
	 * @throws Exception
	 */
	public static Vector<User> searchUsers(DB db) throws Exception {
		Vector<User> userVector = new Vector<User>();
		ResultSet rs;
		String strSql = null;

		strSql = "select * from user";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setGrade(rs.getString("grade"));

			userVector.add(user);
		}

		return userVector;
	}
	
	/** ����email��Ϣ
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @param email �µ�email��Ϣ
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean updateEmail(DB db,int id,String email) throws Exception
	{
		String strSql;
		strSql="update user set email='"+email+"' where id="+id;
		if(db.ExecSql(strSql)!=0)
		{
			return true;
		}
		return false;
	}

	/** �����û�����
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @param password ������
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean updatePassword(DB db,int id,String password) throws Exception
	{
		String strSql;
		strSql="update user set password='"+password+"' where id="+id;
		if(db.ExecSql(strSql)!=0)
		{
			return true;
		}
		return false;
	}

	/** �����û�qq
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @param qq ��qq
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean updateQq(DB db,int id,String qq) throws Exception
	{
		String strSql;
		strSql="update user set qq='"+qq+"' where id="+id;
		if(db.ExecSql(strSql)!=0)
		{
			return true;
		}
		return false;
	}

	/** �����û��Ա�
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @param sex �û��Ա�
	 * @return true�ɹ� fasle ʧ��
	 * @throws Exception
	 */
	public static boolean updateSex(DB db,int id,String sex) throws Exception
	{
		String strSql;
		strSql="update user set sex='"+sex+"' where id="+id;
		if(db.ExecSql(strSql)!=0)
		{
			return true;
		}
		return false;
	}

	/** �����û�ǩ��
	 * @param db ���ݿ�
	 * @param id �û���id
	 * @param signature ǩ��
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean updateSignature(DB db,int id,String signature) throws Exception
	{
		String strSql;
		strSql="update user set signature='"+signature+"' where id="+id;
		if(db.ExecSql(strSql)!=0)
		{
			return true;
		}
		return false;
	}

	/**�����û���������
	 * @param db ���ݿ�
	 * @param id �û�id
	 * @return true �ɹ� false ʧ��
	 * @throws Exception
	 */
	public static boolean updateTopicNum(DB db,int id)throws Exception
	{
		String strSql;
		strSql="select topicNum from user where id="+id;
		ResultSet rs;
		rs = db.OpenSql(strSql);
		if(rs.next()) {
			int tn=rs.getInt(1)+1;
			strSql="update user set topicNum="+tn+" where id="+id;
			db.ExecSql(strSql);
			return true;
		}
		
		return false;
	}
	/**�����û���������
	 * @param db ���ݿ�
	 * @param id id
	 * @param inc ����
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public static boolean updateTopicNum(DB db,int id,int inc)throws Exception
	{
		String strSql;
		strSql="select topicNum from user where id="+id;
		ResultSet rs;
		rs = db.OpenSql(strSql);
		if(rs.next()) {
			int tn=rs.getInt(1)+inc;
			strSql="update user set topicNum="+tn+" where id="+id;
			db.ExecSql(strSql);
			return true;
		}
		
		return false;
	}
	/**
	 * �û���
	 */
	private String username = null;
	/**
	 * ����
	 */
	private String password = null;
	/**
	 * �Ա�
	 */
	private String sex = null;
	/**
	 * email
	 */
	private String email = null;


	/**
	 * qq
	 */
	private String qq = null;
	/**
	 * �û�ǩ��
	 */
	private String signature=null;
	/**
	 * �û�id
	 */
	private int id;
	/**
	 * �û��ȼ�
	 */
	private String grade = null;

	/**
	 * ���캯��
	 */
	public User() {
	}

	/**�õ�email
	 * @return email��ַ
	 */
	public String getEmail() {
		return email;
	}

	/**�õ��û����� 
	 * @return �û�����
	 */
	public String getGrade() {
		return grade;
	}

	/**�õ��û�����
	 * @return �û�����
	 */
	public String getPassword() {
		return password;
	}

	/**�õ��û�qq
	 * @return �û�qq
	 */
	public String getQq() {
		return qq;
	}

	/** �õ��û��Ա�
	 * @return �û��Ա�
	 */
	public String getSex() {
		return sex;
	}

	/**�õ��û�ǩ��
	 * @return �û�ǩ��
	 */
	public String getSignature() {
		return signature;
	}

	/**�õ��û���
	 * @return �û���
	 */
	public String getUsername() {
		return username;
	}

	/**�����ݿ�����µ��û�
	 * @param db ���ݿ�
	 * @return true�ɹ� falseʧ��
	 * @throws Exception
	 */
	public boolean insert(DB db) throws Exception {
		String strSql;
		strSql = "select max(id) from user";
		ResultSet rs;
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			id = rs.getInt(1) + 1;
		} else {
			id = 1;
		}
		strSql = "insert into user " +"(id,username,password,sex,email,qq,signature,grade)"+
				"values("+id+",'" + username + "','" + password
				+ "','" + sex + "','" + email + "','" + qq + "','" +signature+ "','user')";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**����email��ַ
	 * @param email email��ַ
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**���ü���
	 * @param grade ����
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**��������
	 * @param password ����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**����QQ
	 * @param qq qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**�����Ա�
	 * @param sex �Ա�
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**����ǩ��
	 * @param signature ǩ��
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**�����û���
	 * @param username �û���
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}