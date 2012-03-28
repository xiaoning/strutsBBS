/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 论坛管理操作
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
import bbs.Forum;

/** 
* 论坛管理事件
* @author keguolin
* @version 1.0.0
*/ 
public final class ForumManagerAction extends Action {
	/**
     * Description :版块管理
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		Vector<Forum> forumVector = new Vector<Forum>();

		DB db = new DB();

		String usergrade = (String) session
				.getAttribute(Constants.LOGIN_USERGRADE_KEY);
		String pageForward;
		if ("admin".equals(usergrade)) {
			forumVector = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, forumVector);
			pageForward = "ToForumManager";
		} else {
			session.setAttribute(Constants.ERROR_MSG, "您没有权限");
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}