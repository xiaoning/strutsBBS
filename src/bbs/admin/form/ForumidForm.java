/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ��̳��ű�
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/   
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* ��̳��ű�
* @author keguolin
* @version 1.0.0
*/ 
public class ForumidForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �����
	 */
	private String forumid = null;

	/**�õ������
	 * @return �����
	 */
	public String getForumid() {
		return forumid;
	}

	/** ���ð����
	 * @param forumid �����
	 */
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

}