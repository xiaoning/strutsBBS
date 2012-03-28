/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 文章提交操作
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

import bbs.*;
import bbs.user.form.NewArticleForm;

/** 
* 文章提交操作
* @author keguolin
* @version 1.0.0
*/ 
public final class ArticleSubmitAction extends Action {
	/**
     * Description :文章创作提交操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewArticleForm newform = (NewArticleForm) form;
		String title = (String) newform.getTitle();
		String content = (String) newform.getContent();

		DB db = new DB();

		HttpSession session = request.getSession(true);
		String talkType = newform.getType();
		String username = (String) session.getAttribute(Constants.USERNAME_KEY);
		int authorid=(Integer)session.getAttribute(Constants.USERIDKEY);
		String modifytype=(String) session.getAttribute(Constants.OLD_TYPE);
		Integer oldid=(Integer)session.getAttribute(Constants.OLD_ID);
		Vector<Topic> topicVector = new Vector<Topic>();
		String pageForward=null;
		if(talkType.equals("modify"))
		{
			int topicid = ((Integer) session
					.getAttribute(Constants.CUR_TOPICID_KEY)).intValue();
			if(modifytype.equals("topic"))
			{
				if(Topic.updateContent(db, title, content, oldid))
				{
					topicVector = Response.search(db, topicid);
					session.setAttribute(Constants.RESPONSE_LIST_KEY, topicVector);
					pageForward = "ToResponseList";
				}
				else
				{
					session.setAttribute(Constants.ERROR_MSG, "修改失败");
				}
			}
			else if(modifytype.equals("response"))
			{
				if(Response.updateContent(db, title, content, oldid)){
					topicVector = Response.search(db, topicid);
					session.setAttribute(Constants.RESPONSE_LIST_KEY, topicVector);
					pageForward = "ToResponseList";
				}
				else
				{
					session.setAttribute(Constants.ERROR_MSG, "修改失败");
				}
			}
			else
			{
				session.setAttribute(Constants.ERROR_MSG, "unknown error");
			}
			
		}
		else if (talkType.equals("topic")) {
			int forumid = ((Integer) session
					.getAttribute(Constants.CUR_FORUMID_KEY)).intValue();
			String fourmname=(String)session.getAttribute(Constants.CUR_FORUMNAME_KEY);
			Topic topic = new Topic();
			topic.setTitle(title);
			String newContent=StandarlizeContent.Standarlize(content);
			topic.setContent(newContent);
			topic.setForumid(forumid);
			topic.setForumname(fourmname);
			topic.setAuthor(username);
			topic.setAuthorid(authorid);
			if (topic.insert(db)) {
				TopicDisp topicdisp = new TopicDisp();
				topicdisp.setForumid(forumid);
				User.updateTopicNum(db, authorid);
				topicVector = topicdisp.search(db, 0);
				session.setAttribute(Constants.TOPIC_LIST_KEY, topicVector);
				session.setAttribute(Constants.FORUM_LIST_KEY, Forum.search(db));
				pageForward = "ToTopicList";
			} else {
				session.setAttribute(Constants.ERROR_MSG, "发表失败");
				pageForward = "ToErrorPage";
			}
		} else {
			int topicid = ((Integer) session.getAttribute(Constants.CUR_TOPICID_KEY)).intValue();
			int forumid = Topic.getForumidbyID(db, topicid);
			String fourmname=Topic.getForumnamebyID(db, topicid);
			Response respon = new Response();
			respon.setTitle(title);
			String newContent=StandarlizeContent.Standarlize(content);
			respon.setContent(newContent);
			respon.setTopicid(topicid);
			respon.setAuthor(username);
			respon.setAuthorid(authorid);
			respon.setForumid(forumid);
			respon.setForumname(fourmname);
			if (respon.insert(db)) {
				respon.setTopicid(topicid);
				Topic.updateResponseNum(db,String.valueOf(topicid));
				User.updateTopicNum(db, authorid);
				topicVector = Response.search(db, topicid);
				session.setAttribute(Constants.RESPONSE_LIST_KEY, topicVector);
				pageForward = "ToResponseList";
			} else {
				session.setAttribute(Constants.ERROR_MSG, "回复失败");
				pageForward = "ToErrorPage";
			}
		}
		db.close();
		return (mapping.findForward(pageForward));
	}

}
