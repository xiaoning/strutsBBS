/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ����Ϣ��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* ��Ϣ��
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
	 * ����Ϣ���
	 */
	private int id;
	/**
	 * ������id
	 */
	private int fromid;
	/**
	 * ������id
	 */
	private int toid;
	/**
	 * �Ƿ��Ķ�
	 */
	private int isread;
	/**
	 * ������
	 */
	private String fromname;
	/**
	 * ������
	 */
	private String toname;
	/**
	 * ����
	 */
	private String title;
	/**
	 * ����
	 */
	private String content;
	/**
	 * ����ʱ��
	 */
	private String submittime;
	/**
	 * ����
	 */
	private String type;
	/**����id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**�õ�id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**���÷�����id
	 * @param fromid ������id
	 */
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	/**�õ�������id
	 * @return ������id
	 */
	public int getFromid() {
		return fromid;
	}
	/**���ý�����id
	 * @param toid ������id
	 */
	public void setToid(int toid) {
		this.toid = toid;
	}
	/**���ý�����id
	 * @return ������id
	 */
	public int getToid() {
		return toid;
	}
	/**�����Ƿ��Ķ�
	 * @param isread 0Ϊδ�� 1Ϊ�Ѷ�
	 */
	public void setIsread(int isread) {
		this.isread = isread;
	}
	/**�õ��Ƿ��Ķ�
	 * @return 0Ϊδ�� 1Ϊ�Ѷ�
	 */
	public int getIsread() {
		return isread;
	}
	/** ���÷���������
	 * @param fromname ����������
	 */
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	/** �õ�����������
	 * @return ����������
	 */
	public String getFromname() {
		return fromname;
	}
	/**���ý���������
	 * @param toname ����������
	 */
	public void setToname(String toname) {
		this.toname = toname;
	}
	/**�õ�����������
	 * @return ����������
	 */
	public String getToname() {
		return toname;
	}
	/**���ñ���
	 * @param title ����
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**�õ�����
	 * @return ����
	 */
	public String getTitle() {
		return title;
	}
	/**��������
	 * @param content ����
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**�õ�����
	 * @return ����
	 */
	public String getContent() {
		return content;
	}
	/** ���÷���ʱ��
	 * @param submittime ����ʱ��
	 */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	/**�õ�����ʱ��
	 * @return ����ʱ��
	 */
	public String getSubmittime() {
		return submittime;
	}
	/** ��������
	 * @param type ����
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**�õ�����
	 * @return ����
 	 */
	public String getType() {
		return type;
	}

}
