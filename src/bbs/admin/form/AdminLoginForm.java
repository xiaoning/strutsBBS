/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 管理员登录表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* 管理员登录表单
* @author keguolin
* @version 1.0.0
*/ 
public class AdminLoginForm extends ActionForm {
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
	 * 构造函数
	 */
	public AdminLoginForm() {
	}

	/**设置用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**得到用户名
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**设置密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**得到密码
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

}