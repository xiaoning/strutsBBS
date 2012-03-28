/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 用户管理表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* 用户管理表单
* @author keguolin
* @version 1.0.0
*/ 
public class UserManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String username = null;
	
	/**
	 * 等级
	 */
	private String grade = null;

	/**
	 * 版块
	 */
	private String forum = null;

	/**得到版块
	 * @return 版块
	 */
	public String getForum() {
		return forum;
	}

	/**设置版块
	 * @param forum 版块
	 */
	public void setForum(String forum) {
		this.forum = forum;
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


}