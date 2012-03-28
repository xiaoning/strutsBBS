/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �û������
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* �û������
* @author keguolin
* @version 1.0.0
*/ 
public class UserManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �û���
	 */
	private String username = null;
	
	/**
	 * �ȼ�
	 */
	private String grade = null;

	/**
	 * ���
	 */
	private String forum = null;

	/**�õ����
	 * @return ���
	 */
	public String getForum() {
		return forum;
	}

	/**���ð��
	 * @param forum ���
	 */
	public void setForum(String forum) {
		this.forum = forum;
	}

	/**�õ��ȼ�
	 * @return �ȼ�
	 */
	public String getGrade() {
		return grade;
	}

	/**���õȼ�
	 * @param grade �ȼ�
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**�õ��û���
	 * @return �û���
	 */
	public String getUsername() {
		return username;
	}

	/**�����û���
	 * @param username �û���
	 */
	public void setUsername(String username) {
		this.username = username;
	}


}