/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 查看主题列表
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
import bbs.Topic;
import bbs.TopicDisp;
import bbs.user.form.ForumForm;


/** 
* 显示主题列表操作
* @author keguolin
* @version 1.0.0
*/ 
public final class TopicListAction extends Action {
	/**
     * Description :显示主题列表
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumForm forumForm = (ForumForm) form;
		int forumid = forumForm.getForumid();
		String forumname = forumForm.getForumname();
		int pageid = forumForm.getPageid();
		if (pageid < 0) {
			pageid = 0;
		}

		HttpSession session = request.getSession(true);
		Vector<Topic> topicVector = new Vector<Topic>();
		TopicDisp topicdisp = new TopicDisp();
		String pageForward;

		DB db = new DB();
		int count=topicdisp.getTopicCount(db, forumid);
		int pagecount=1;
		if(count!=0&&count%Constants.TOPIC_PAGE_SIZE==0)
			pagecount=count/Constants.TOPIC_PAGE_SIZE;
		else
			pagecount=count/Constants.TOPIC_PAGE_SIZE+1;
		session.setAttribute(Constants.CUR_PAGECOUNT_KEY, pagecount);
		
		if (count == 0) {
			session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
			session.setAttribute(Constants.CUR_FORUMID_KEY,new Integer(forumid));
			session.setAttribute(Constants.CUR_FORUMNAME_KEY, forumname);
			session.setAttribute(Constants.CUR_PAGEID_KEY, new Integer(pageid));
			pageForward = "ToTopicList";
		} else if (count < pageid * Constants.TOPIC_PAGE_SIZE + 1) {
			pageid--;
			session.setAttribute(Constants.ERROR_MSG, "no such page");
			pageForward = "ToErrorPage";
		} else {
			topicdisp.setForumid(forumid);
			topicVector = topicdisp.search(db, pageid);
			session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
			session.setAttribute(Constants.CUR_FORUMID_KEY, new Integer(forumid));
			session.setAttribute(Constants.CUR_FORUMNAME_KEY, forumname);
			session.setAttribute(Constants.CUR_PAGEID_KEY, new Integer(pageid));

			pageForward = "ToTopicList";
		}
		int id=(Integer)session.getAttribute(Constants.USERIDKEY);
		count=Message.getUnreadCount(db, id);
		session.setAttribute(Constants.MESSAGE_Count, count);
		db.close();
		return (mapping.findForward(pageForward));
	}
}
