/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 查看主题及其回复列表操作
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
import bbs.Message;
import bbs.Response;
import bbs.Topic;
import bbs.user.form.TopicOfResponseForm;

/** 
* 显示主题及其回复操作
* @author keguolin
* @version 1.0.0
*/ 
public final class ResponseListAction extends Action {
	/**
     * Description : 显示主题及其回复
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TopicOfResponseForm topicForm = (TopicOfResponseForm) form;

		int topicid = topicForm.getTopicid();
		HttpSession session = request.getSession();
		Vector<Topic> v = new Vector<Topic>();
		
		DB db = new DB();
		Topic.updateViewNum(db, String.valueOf(topicid));
		int forumid=Topic.getForumidbyID(db, topicid);
		String forumname=Topic.getForumnamebyID(db, topicid);
		v = Response.search(db, topicid);
		session.setAttribute(Constants.RESPONSE_LIST_KEY, v);
		session.setAttribute(Constants.CUR_TOPICID_KEY, new Integer(topicid));
		
		session.setAttribute(Constants.CUR_FORUMID_KEY, forumid);
		session.setAttribute(Constants.CUR_FORUMNAME_KEY, forumname);
		int id=(Integer)session.getAttribute(Constants.USERIDKEY);
		int count=Message.getUnreadCount(db, id);
		session.setAttribute(Constants.MESSAGE_Count, count);
		db.close();
		return (mapping.findForward("ToResponseList"));
	}
}
