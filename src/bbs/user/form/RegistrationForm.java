/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ע����Ϣ��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/     
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* ע����Ϣ��
* @author keguolin
* @version 1.0.0
*/ 
public class RegistrationForm extends ActionForm {
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
	 * �ڶ�������
	 */
	private String password2 = null;

	/**
	 * �Ա�
	 */
	private String sex = null;

	/**
	 * email
	 */
	private String email = null;

	/**
	 * qq
	 */
	private String icq = null;

	/**
	 * ǩ��
	 */
	private String signature = null;

	/**
	 * �ȼ�
	 */
	private String grade = null;

	/**
	 * id
	 */
	private int id;
	
	/**
	 * ������
	 */
	private String oldpassword;
	/**
	 * ���캯��
	 */
	public RegistrationForm() {
	}

	/**�õ�email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**����email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**�õ��ȼ�
	 * @return �ȼ�
	 */
	public String getGrade() {
		return grade;
	}

	/**���õȼ�
	 * @param grade �ȼ�
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**�õ�qq
	 * @return qq
	 */
	public String getIcq() {
		return icq;
	}

	/**����qq
	 * @param icq qq
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/** �õ�����
	 * @return ����
	 */
	public String getPassword() {
		return password;
	}

	/**��������
	 * @param password ����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**�õ��ڶ�������
	 * @return �ڶ�������
	 */
	public String getPassword2() {
		return password2;
	}

	/**���õڶ�������
	 * @param password2 �ڶ�������
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**�õ��Ա�
	 * @return �Ա�
	 */
	public String getSex() {
		return sex;
	}

	/**�����Ա�
	 * @param sex �Ա�
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**�õ�ǩ��
	 * @return ǩ��
	 */
	public String getSignature() {
		return signature;
	}

	/** ����ǩ��
	 * @param signature ǩ��
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**�õ��û���
	 * @return �û���
	 */
	public String getUsername() {
		return username;
	}

	/**�����û���
	 * @param username �û���
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** ����id
	 * @param id id
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

	/** ���þ�����
	 * @param oldpassword
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	/**�õ�������
	 * @return ������
	 */
	public String getOldpassword() {
		return oldpassword;
	}

}