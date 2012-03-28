/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �û���¼��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/        
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* �û���¼��Ϣ��
* @author keguolin
* @version 1.0.0
*/ 
public final class UserLoginForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �û���
	 */
	private String username = null; 

	/**
	 * ����
	 */
	private String password = null; 

	/**
	 * ���캯��
	 */
	public UserLoginForm() {
	}

	/**�����û���
	 * @param username �û���
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** �õ��û���
	 * @return �û���
	 */
	public String getUsername() {
		return username;
	}

	/**��������
	 * @param password ����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**�õ�����
	 * @return ����
	 */
	public String getPassword() {
		return password;
	}
}