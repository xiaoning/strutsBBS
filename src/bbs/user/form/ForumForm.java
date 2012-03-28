/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ����
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/      
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* ����
* @author keguolin
* @version 1.0.0
*/ 
public class ForumForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �����
	 */
	private int forumid = 0;

	/**
	 * �������
	 */
	private String forumname = null;

	/**
	 * ҳ��
	 */
	private int pageid = 0;

	/**
	 * ���캯��
	 */
	public ForumForm() {
	}

	/**�õ����id
	 * @return ���id
	 */
	public int getForumid() {
		return forumid;
	}

	/**���ð��id
	 * @param forumid ���id
	 */
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	/**�õ��������
	 * @return �������
	 */
	public String getForumname() {
		return forumname;
	}

	/**���ð������
	 * @param forumname �������
	 */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	/**�õ�ҳ��
	 * @return ҳ��
	 */
	public int getPageid() {
		return pageid;
	}

	/**����ҳ��
	 * @param pageid ҳ��
	 */
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

}
