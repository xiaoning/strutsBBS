/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ɾ���û�
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
import bbs.admin.form.UserManagerForm;


/** 
* �û�ɾ���¼�
* @author keguolin
* @version 1.0.0
*/ 
public final class UserDeleteAction extends Action {
	/**
     * Description :�û�ɾ������
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserManagerForm userManagerForm = (UserManagerForm) form;
		String username = (String) userManagerForm.getUsername();

		HttpSession session = request.getSession();
		Vector<User> users = new Vector<User>();

		DB db = new DB();

		String pageForward;
		if (User.delete(db, username)) {
			users = User.search(db, "");
			session.setAttribute(Constants.USER_LIST_KEY, users);
			pageForward = "ToUserManager";
		} else {
			session.setAttribute(Constants.ERROR_MSG, "ɾ��ʧ��");
			pageForward = "ToErrorPage";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}
}