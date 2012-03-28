/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ÂÛÌ³±àºÅ±íµ¥
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/   
package bbs.admin.form;

import org.apache.struts.action.ActionForm;

/** 
* ÂÛÌ³±àºÅ±íµ¥
* @author keguolin
* @version 1.0.0
*/ 
public class ForumidForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * °æ¿é±àºÅ
	 */
	private String forumid = null;

	/**µÃµ½°æ¿é±àºÅ
	 * @return °æ¿é±àºÅ
	 */
	public String getForumid() {
		return forumid;
	}

	/** ÉèÖÃ°æ¿é±àºÅ
	 * @param forumid °æ¿é±àºÅ
	 */
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

}