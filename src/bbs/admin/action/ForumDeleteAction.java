/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ÂÛÌ³É¾³ý
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
import bbs.admin.form.ForumidForm;
/** 
* ÂÛÌ³É¾³ýÊÂ¼þ
* @author keguolin
* @version 1.0.0
*/ 
public final class ForumDeleteAction extends Action {
	/**
     * Description :°æ¿éÉ¾³ý²Ù×÷
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumidForm forumidForm = (ForumidForm) form;
		String forumid = forumidForm.getForumid();

		HttpSession session = request.getSession();
		Vector<Forum> sorts = new Vector<Forum>();

		DB db = new DB();

		String pageForward;
		if (Forum.delete(db, forumid)) {

			sorts = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, sorts);
			pageForward = "ToForumManager";
		} else {
			session.setAttribute(Constants.ERROR_MSG, "É¾³ýÊ§°Ü");
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));

	}
}