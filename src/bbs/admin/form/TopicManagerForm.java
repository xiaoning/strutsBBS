/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ��������
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/ 
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* ��������
* @author keguolin
* @version 1.0.0
*/ 
public class TopicManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	private String topicid = null;

	/**
	 * �������
	 */
	private String title = null;
	
	/**
	 * ��������
	 */
	private int type;

	/**�õ����±���
	 * @return ���±���
	 */
	public String getTitle() {
		return title;
	}

	/** �������±���
	 * @param title ���±���
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**�õ����±��
	 * @return ���±��
	 */
	public String getTopicid() {
		return topicid;
	}

	/** �������±��
	 * @param topicid ���±��
	 */
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	/** ������������
	 * @param type ��������
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**�õ���������
	 * @return ��������
	 */
	public int getType() {
		return type;
	}

}