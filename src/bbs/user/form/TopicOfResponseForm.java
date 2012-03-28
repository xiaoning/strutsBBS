/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 主题回复表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/      
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* 主题回复表单
* @author keguolin
* @version 1.0.0
*/ 
public class TopicOfResponseForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主题id
	 */
	private int topicid = 0;

	/**
	 * 构造函数
	 */
	public TopicOfResponseForm() {
	}

	/**设置主题id
	 * @param topicid 主题id
	 */
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	/**得到主题id
	 * @return 主题id
	 */
	public int getTopicid() {
		return topicid;
	}
}