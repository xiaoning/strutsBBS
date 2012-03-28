/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ����ظ���
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/      
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* ����ظ���
* @author keguolin
* @version 1.0.0
*/ 
public class TopicOfResponseForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ����id
	 */
	private int topicid = 0;

	/**
	 * ���캯��
	 */
	public TopicOfResponseForm() {
	}

	/**��������id
	 * @param topicid ����id
	 */
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	/**�õ�����id
	 * @return ����id
	 */
	public int getTopicid() {
		return topicid;
	}
}