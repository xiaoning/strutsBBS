/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ÂÛÌ³±à¼­
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
import bbs.User;
import bbs.admin.form.ForumManagerForm;
/** 
* ÂÛÌ³±à¼­ÊÂ¼þ
* @author keguolin
* @version 1.0.0
*/ 
public final class ForumEditAction extends Action {
	/**
     * Description :°æ¿é±à¼­²Ù×÷
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumManagerForm forumEditForm = (ForumManagerForm) form;
		String forumid = forumEditForm.getForumid();
		String forumname = forumEditForm.getForumname();

		HttpSession session = request.getSession();
		Vector<User> managerUserVector = new Vector<User>();

		DB db = new DB();

		managerUserVector = User.searchUsers(db);

		session
				.setAttribute(Constants.MANAGER_CANDIDATE_KEY,
						managerUserVector);
		session.setAttribute(Constants.EDIT_FORUMID_KEY, forumid);
		session.setAttribute(Constants.EDIT_FORUMNAME_KEY, forumname);

		db.close();
		return (mapping.findForward("ToForumEdit"));
	}
}