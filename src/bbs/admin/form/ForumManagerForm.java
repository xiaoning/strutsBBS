/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ��̳�����
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/ 
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* ��̳�����
* @author keguolin
* @version 1.0.0
*/ 
public class ForumManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �����
	 */
	private String forumid = null;

	/**
	 * �������
	 */
	private String forumname = null;

	/**
	 * ����Ա
	 */
	private String manager = null;

	/**�õ������
	 * @return ��� ���
	 */
	public String getForumid() {
		return forumid;
	}

	/**���ð����
	 * @param forumid �����
	 */
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	/** �õ��������
	 * @return �������
	 */
	public String getForumname() {
		return forumname;
	}

	/** ���ð������
	 * @param forumname �������
	 */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	/** �õ�������Ա
	 * @return ������Ա
	 */
	public String getManager() {
		return manager;
	}

	/** ���ð�����Ա
	 * @param manager ������Ա
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

}