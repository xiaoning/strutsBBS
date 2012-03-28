/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 注册信息表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/     
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* 注册信息表单
* @author keguolin
* @version 1.0.0
*/ 
public class RegistrationForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String username = null;

	/**
	 * 密码
	 */
	private String password = null;

	/**
	 * 第二次密码
	 */
	private String password2 = null;

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
	private String icq = null;

	/**
	 * 签名
	 */
	private String signature = null;

	/**
	 * 等级
	 */
	private String grade = null;

	/**
	 * id
	 */
	private int id;
	
	/**
	 * 旧密码
	 */
	private String oldpassword;
	/**
	 * 构造函数
	 */
	public RegistrationForm() {
	}

	/**得到email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**设置email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**得到等级
	 * @return 等级
	 */
	public String getGrade() {
		return grade;
	}

	/**设置等级
	 * @param grade 等级
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**得到qq
	 * @return qq
	 */
	public String getIcq() {
		return icq;
	}

	/**设置qq
	 * @param icq qq
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/** 得到密码
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**设置密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**得到第二次密码
	 * @return 第二次密码
	 */
	public String getPassword2() {
		return password2;
	}

	/**设置第二次密码
	 * @param password2 第二次密码
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**得到性别
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	/**设置性别
	 * @param sex 性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**得到签名
	 * @return 签名
	 */
	public String getSignature() {
		return signature;
	}

	/** 设置签名
	 * @param signature 签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**得到用户名
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**设置用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 设置id
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**得到id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/** 设置旧密码
	 * @param oldpassword
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	/**得到新密码
	 * @return 新密码
	 */
	public String getOldpassword() {
		return oldpassword;
	}

}