/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ��̳�༭�Ͻ�����
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
import bbs.admin.form.ForumManagerForm;

/** 
* �ύ��̳����Ϣ�¼�
* @author keguolin
* @version 1.0.0
*/ 
public final class ForumEditSubmitAction extends Action {
	/**
     * Description :���༭�ύ����
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
		String forumname = (String) forumEditForm.getForumname();
		String manager = (String) forumEditForm.getManager();

		HttpSession session = request.getSession();
		Vector<Forum> forumVector = new Vector<Forum>();

		DB db = new DB();

		String PageForward;
		if (Forum.edit(db, forumid, forumname, manager)) {
			forumVector = Forum.search(db);
			session.setAttribute(Constants.FORUM_LIST_KEY, forumVector);
			PageForward = "ToForumManager";
		} else {
			session.setAttribute(Constants.ERROR_MSG,"����ʧ��");
			PageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(PageForward));
	}
}