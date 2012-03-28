/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 删除主题
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
import bbs.Message;
import bbs.Response;
import bbs.Topic;
import bbs.User;
import bbs.admin.form.TopicManagerForm;

/** 
* 主题删除事件
* @author keguolin
* @version 1.0.0
*/ 
public final class TopicDeleteAction extends Action {
	/**
     * Description :主题删除操作
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
		String topicid = topicManagerForm.getTopicid();
		int type=topicManagerForm.getType();
		HttpSession session = request.getSession();
		Vector<Topic> topics = new Vector<Topic>();

		DB db = new DB();

		String pageForward;
		String title=Response.getTitleById(db, topicid, type);
		int authorid=Response.getAuthoridById(db, topicid, type);
		String toname=Response.getAuthorById(db, topicid, type);
		if (Topic.delete(db, topicid,type)) {
			User.updateTopicNum(db, authorid, -1);
			String[] searchtopic = (String []) session
					.getAttribute(Constants.SEARCH_TOPIC_KEY);
			topics = Topic.search(db, searchtopic);
			session.setAttribute(Constants.SEARCH_LIST, topics);
			Message msg=new Message();
			msg.setContent("您的帖子:“"+title+"”已被删除，如有异议，与管理员联系");
			msg.setFromid(-1);
			msg.setFromname("");
			msg.setTitle("系统消息");
			msg.setToid(authorid);
			msg.setToname(toname);
			msg.sendMessage(db);
			pageForward = "ToTopicManager";
		} else {
			session.setAttribute(Constants.ERROR_MSG, "删除失败");
			pageForward = "ToErrorPage";
		}
		db.close();
		return (mapping.findForward(pageForward));
	}
}