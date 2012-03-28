/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 搜索主题
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs.admin.action;


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
import bbs.admin.form.TopicManagerForm;
/** 
* 主题查找
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

		TopicManagerForm topicManagerForm = (TopicManagerForm) form;
		String title = topicManagerForm.getTitle();
		if(title==null) title="";
		String[] titles=null;
		if(title!=null) titles=title.split(" ");
		HttpSession session = request.getSession();
		Vector<Topic> topicVector = new Vector<Topic>();

		DB db = new DB();

		topicVector = Topic.search(db, titles);

		session.setAttribute(Constants.SEARCH_LIST, topicVector);
		session.setAttribute(Constants.SEARCH_TOPIC_KEY, titles);
		db.close();
		return (mapping.findForward("ToTopicManager"));
	}
}