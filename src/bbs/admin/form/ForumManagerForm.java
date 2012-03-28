/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 论坛管理表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/ 
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* 论坛管理表单
* @author keguolin
* @version 1.0.0
*/ 
public class ForumManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 版块编号
	 */
	private String forumid = null;

	/**
	 * 版块名称
	 */
	private String forumname = null;

	/**
	 * 管理员
	 */
	private String manager = null;

	/**得到版块编号
	 * @return 版块 编号
	 */
	public String getForumid() {
		return forumid;
	}

	/**设置版块编号
	 * @param forumid 版块编号
	 */
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	/** 得到版块名称
	 * @return 版块名称
	 */
	public String getForumname() {
		return forumname;
	}

	/** 设置版块名称
	 * @param forumname 版块名称
	 */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	/** 得到版块管理员
	 * @return 版块管理员
	 */
	public String getManager() {
		return manager;
	}

	/** 设置版块管理员
	 * @param manager 版块管理员
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

}