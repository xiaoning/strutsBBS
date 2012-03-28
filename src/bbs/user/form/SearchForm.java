/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 搜索信息表单
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.form;

import org.apache.struts.action.ActionForm;

/** 
* 搜索关键字表单
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
	 * 关键字
	 */
	private String key;
	/**
	 * 搜索类型
	 */
	private String type;
	/**
	 * 作者id
	 */
	private int authorid;
	/**
	 * 构造函数
	 */
	public SearchForm()
	{
		
	}
	/**设置搜索关键字
	 * @param key 搜索关键字
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**得到搜索关键字
	 * @return 搜索关键字
	 */
	public String getKey() {
		return key;
	}
	/**设置搜索关键字
	 * @param type 搜索关键字
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
	/**设置用户id
	 * @param authorid 用户id
	 */
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	/**得到用户id
	 * @return 用户id
	 */
	public int getAuthorid() {
		return authorid;
	}
	
}
