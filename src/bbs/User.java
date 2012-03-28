/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 用户类定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/   
package bbs;

import java.sql.ResultSet;
import java.util.Vector;
/** 
* 用户类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class User {

	/**检查用户是否存在
	 * @param db 数据库
	 * @param username 用户名
	 * @return true已存在 false不存在
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


	/**检查用户名和密码是否匹配
	 * @param db 数据库
	 * @param username 用户名 
	 * @param password 密码
	 * @return true 匹配 false不匹配
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

	/** 删除某个用户
	 * @param db 数据库
	 * @param username 用户名
	 * @return true操作成功 false操作失败
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


	/** 编辑用户等级
	 * @param db  数据库
	 * @param username 用户名
	 * @param grade 级别
	 * @param forumid 版块ID
	 * @return true成功 false失败
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


	/**用用户名得到用户的id
	 * @param db 数据库
	 * @param username 用户名
	 * @return 用户id （-1为不存在）
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
	

	/**用用户id得到用户的名字
	 * @param db 数据库
	 * @param id 用户id
	 * @return 用户名字
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
	
	/**用用户名得到用户等级
	 * @param db 数据库
	 * @param username 用户名
	 * @return 用户等级
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
	

	
	/** 用用户名搜索用户
	 * @param db 数据库
	 * @param username 用户名关键字
	 * @return 搜索结果数组
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
	
	/**找出所用用户信息
	 * @param db 数据库 
	 * @return 用户信息数组
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
	
	/** 更新email信息
	 * @param db 数据库
	 * @param id 用户id
	 * @param email 新的email信息
	 * @return true成功 false失败
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

	/** 更新用户密码
	 * @param db 数据库
	 * @param id 用户id
	 * @param password 新密码
	 * @return true成功 false失败
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

	/** 更新用户qq
	 * @param db 数据库
	 * @param id 用户id
	 * @param qq 新qq
	 * @return true成功 false失败
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

	/** 更新用户性别
	 * @param db 数据库
	 * @param id 用户id
	 * @param sex 用户性别
	 * @return true成功 fasle 失败
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

	/** 更新用户签名
	 * @param db 数据库
	 * @param id 用户名id
	 * @param signature 签名
	 * @return true成功 false失败
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

	/**更新用户主题数量
	 * @param db 数据库
	 * @param id 用户id
	 * @return true 成功 false 失败
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
	/**更新用户主题数量
	 * @param db 数据库
	 * @param id id
	 * @param inc 增量
	 * @return true成功 false失败
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
	 * 用户名
	 */
	private String username = null;
	/**
	 * 密码
	 */
	private String password = null;
	/**
	 * 性别
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
	 * 用户签名
	 */
	private String signature=null;
	/**
	 * 用户id
	 */
	private int id;
	/**
	 * 用户等级
	 */
	private String grade = null;

	/**
	 * 构造函数
	 */
	public User() {
	}

	/**得到email
	 * @return email地址
	 */
	public String getEmail() {
		return email;
	}

	/**得到用户级别 
	 * @return 用户级别
	 */
	public String getGrade() {
		return grade;
	}

	/**得到用户密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**得到用户qq
	 * @return 用户qq
	 */
	public String getQq() {
		return qq;
	}

	/** 得到用户性别
	 * @return 用户性别
	 */
	public String getSex() {
		return sex;
	}

	/**得到用户签名
	 * @return 用户签名
	 */
	public String getSignature() {
		return signature;
	}

	/**得到用户名
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**在数据库插入新的用户
	 * @param db 数据库
	 * @return true成功 false失败
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

	/**设置email地址
	 * @param email email地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**设置级别
	 * @param grade 级别
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**设置密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**设置QQ
	 * @param qq qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**设置性别
	 * @param sex 性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**设置签名
	 * @param signature 签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**设置用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}