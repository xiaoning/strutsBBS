/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ���±�
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* ���±�
* @author keguolin
* @version 1.0.0
*/ 
public final class NewArticleForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���±���
	 */
	private String title;
    
    /**
     * ����
     */
    private String content;
    
    /**
     * id
     */
    private int id;
    
    /**
     * ����
     */
    private String type;
    
    /**
     * �޸�����
     */
    private String modifytype;
    
    /** ������������
     * @return ��������
     */
    public String getContent() {
        return content;
    }
    /**������������
     * @param ��������
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**���ر���
     * @return ����
     */
    public String getTitle() {
        return title;
    }
    /**���ñ���
     * @param ���ñ���
     */
    public void setTitle(String title) {
        this.title = title;
    }
	/** ����id
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**����id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/** ��������
	 * @param type ����
	 */
	public void setType(String type) {
		this.type = type;
	}
	/** �õ�����
	 * @return ����
	 */
	public String getType() {
		return type;
	}
	/** �����޸�����
	 * @param modifytype �޸�����
	 */
	public void setModifytype(String modifytype) {
		this.modifytype = modifytype;
	}
	/**�õ��޸�����
	 * @return �޸�����
	 */
	public String getModifytype() {
		return modifytype;
	}
}