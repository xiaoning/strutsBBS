/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 主题管理表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/ 
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* 主题管理表单
* @author keguolin
* @version 1.0.0
*/ 
public class TopicManagerForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主题编号
	 */
	private String topicid = null;

	/**
	 * 主题标题
	 */
	private String title = null;
	
	/**
	 * 主题类型
	 */
	private int type;

	/**得到文章标题
	 * @return 文章标题
	 */
	public String getTitle() {
		return title;
	}

	/** 设置文章标题
	 * @param title 文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**得到文章编号
	 * @return 文章编号
	 */
	public String getTopicid() {
		return topicid;
	}

	/** 设置文章编号
	 * @param topicid 文章编号
	 */
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	/** 设置文章类型
	 * @param type 文章类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**得到文章类型
	 * @return 文章类型
	 */
	public int getType() {
		return type;
	}

}