/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ������Ϣ��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* �����ؼ��ֱ�
* @author keguolin
* @version 1.0.0
*/ 
public class SearchForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �ؼ���
	 */
	private String key;
	/**
	 * ��������
	 */
	private String type;
	/**
	 * ����id
	 */
	private int authorid;
	/**
	 * ���캯��
	 */
	public SearchForm()
	{
		
	}
	/**���������ؼ���
	 * @param key �����ؼ���
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**�õ������ؼ���
	 * @return �����ؼ���
	 */
	public String getKey() {
		return key;
	}
	/**���������ؼ���
	 * @param type �����ؼ���
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
	/**�����û�id
	 * @param authorid �û�id
	 */
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	/**�õ��û�id
	 * @return �û�id
	 */
	public int getAuthorid() {
		return authorid;
	}
	
}
