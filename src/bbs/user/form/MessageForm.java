/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 短消息表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* 信息表单
* @author keguolin
* @version 1.0.0
*/ 
public class MessageForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2449745587053877578L;
	/**
	 * 短消息编号
	 */
	private int id;
	/**
	 * 发送者id
	 */
	private int fromid;
	/**
	 * 接受者id
	 */
	private int toid;
	/**
	 * 是否阅读
	 */
	private int isread;
	/**
	 * 发送者
	 */
	private String fromname;
	/**
	 * 接受者
	 */
	private String toname;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发送时间
	 */
	private String submittime;
	/**
	 * 类型
	 */
	private String type;
	/**设置id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**得到id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**设置发送者id
	 * @param fromid 发送者id
	 */
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	/**得到发送者id
	 * @return 发送者id
	 */
	public int getFromid() {
		return fromid;
	}
	/**设置接收者id
	 * @param toid 接收者id
	 */
	public void setToid(int toid) {
		this.toid = toid;
	}
	/**设置接收者id
	 * @return 接收者id
	 */
	public int getToid() {
		return toid;
	}
	/**设置是否阅读
	 * @param isread 0为未读 1为已读
	 */
	public void setIsread(int isread) {
		this.isread = isread;
	}
	/**得到是否阅读
	 * @return 0为未读 1为已读
	 */
	public int getIsread() {
		return isread;
	}
	/** 设置发送者名字
	 * @param fromname 发送者名字
	 */
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	/** 得到发送者名字
	 * @return 发送者名字
	 */
	public String getFromname() {
		return fromname;
	}
	/**设置接受者名字
	 * @param toname 接收者名字
	 */
	public void setToname(String toname) {
		this.toname = toname;
	}
	/**得到接收者名字
	 * @return 接收者名字
	 */
	public String getToname() {
		return toname;
	}
	/**设置标题
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**得到标题
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}
	/**设置内容
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**得到内容
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}
	/** 设置发送时间
	 * @param submittime 发送时间
	 */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	/**得到发送时间
	 * @return 发送时间
	 */
	public String getSubmittime() {
		return submittime;
	}
	/** 设置类型
	 * @param type 类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**得到类型
	 * @return 类型
 	 */
	public String getType() {
		return type;
	}

}
