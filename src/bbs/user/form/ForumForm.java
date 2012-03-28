/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : °æ¿é±íµ¥
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/      
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* °æ¿é±íµ¥
* @author keguolin
* @version 1.0.0
*/ 
public class ForumForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * °æ¿é±àºÅ
	 */
	private int forumid = 0;

	/**
	 * °æ¿éÃû³Æ
	 */
	private String forumname = null;

	/**
	 * Ò³Âë
	 */
	private int pageid = 0;

	/**
	 * ¹¹Ôìº¯Êı
	 */
	public ForumForm() {
	}

	/**µÃµ½°æ¿éid
	 * @return °æ¿éid
	 */
	public int getForumid() {
		return forumid;
	}

	/**ÉèÖÃ°æ¿éid
	 * @param forumid °æ¿éid
	 */
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	/**µÃµ½°æ¿éÃû³Æ
	 * @return °æ¿éÃû³Æ
	 */
	public String getForumname() {
		return forumname;
	}

	/**ÉèÖÃ°æ¿éÃû³Æ
	 * @param forumname °æ¿éÃû³Æ
	 */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	/**µÃµ½Ò³Âë
	 * @return Ò³Âë
	 */
	public int getPageid() {
		return pageid;
	}

	/**ÉèÖÃÒ³Âë
	 * @param pageid Ò³Âë
	 */
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

}
