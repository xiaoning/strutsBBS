/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 文章搜索操作
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.action;


import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bbs.Constants;
import bbs.DB;
import bbs.Topic;
import bbs.user.form.*;

/** 
* 主题搜索操作
* @author keguolin
* @version 1.0.0
*/ 
public final class TopicSearchAction extends Action {
	/**
     * Description :主体搜索操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchForm searchform = (SearchForm) form;
		String key = searchform.getKey();
		String[] keys=null;
		if(key!=null) {
			keys=key.split(" ");
		}
		String type= searchform.getType();
		int authorid=searchform.getAuthorid();
		HttpSession session = request.getSession();
		Vector<Topic> topicVector = new Vector<Topic>();
		DB db = new DB();
		if(type.equals("bytitle")){
			topicVector = Topic.search(db, keys);
		}
		else if(type.equals("byauthor"))
		{
			topicVector=Topic.searchByAuthor(db, keys);
		}
		else if(type.equals("bycontent"))
		{
			topicVector=Topic.searchByContent(db, keys);
		}
		else if(type.equals("byauthorid"))
		{
			topicVector=Topic.searchByAuthorId(db, authorid);
		}
		session.setAttribute(Constants.SEARCH_LIST, topicVector);

		db.close();

		return (mapping.findForward("ToSearch"));
	}
}