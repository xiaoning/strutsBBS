/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 文章表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* 文章表单
* @author keguolin
* @version 1.0.0
*/ 
public final class NewArticleForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文章标题
	 */
	private String title;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * id
     */
    private int id;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 修改类型
     */
    private String modifytype;
    
    /** 返回文章内容
     * @return 文章内容
     */
    public String getContent() {
        return content;
    }
    /**设置文章内容
     * @param 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**返回标题
     * @return 标题
     */
    public String getTitle() {
        return title;
    }
    /**设置标题
     * @param 设置标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
	/** 设置id
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**返回id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/** 设置类型
	 * @param type 类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/** 得到类型
	 * @return 类型
	 */
	public String getType() {
		return type;
	}
	/** 设置修改类型
	 * @param modifytype 修改类型
	 */
	public void setModifytype(String modifytype) {
		this.modifytype = modifytype;
	}
	/**得到修改类型
	 * @return 修改类型
	 */
	public String getModifytype() {
		return modifytype;
	}
}