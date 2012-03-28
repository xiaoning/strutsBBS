/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 用户登录操作
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/      
package bbs.user.action;



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
import bbs.Message;
import bbs.User;
import bbs.user.form.UserLoginForm;


/** 
* 用户登录操作
* @author keguolin
* @version 1.0.0
*/ 
public final class UserLoginAction extends Action {
	/**
     * Description :用户登录操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserLoginForm userLoginForm = (UserLoginForm) form;
		String username = userLoginForm.getUsername();
		String password = userLoginForm.getPassword();

		DB db = new DB();

		HttpSession session = request.getSession();
	//	session.setMaxInactiveInterval(365 * 24 * 3600);
		
		String pageForward;

		if ((!username.equals("guest"))
				&& (!User.checkUser(db, username, password))) {
			session.setAttribute(Constants.ERROR_MSG, "用户名或密码错误");
			pageForward = "ToErrorPage";
		} else {
			int id=User.getId(db, username);
			session.setAttribute(Constants.FORUM_LIST_KEY, Forum.search(db));
			session.setAttribute(Constants.USERNAME_KEY, username);
			session.setAttribute(Constants.USERIDKEY, id);
			int count=Message.getUnreadCount(db, id);
			session.setAttribute(Constants.MESSAGE_Count, count);
			pageForward = "UserLoginSucceed";
		}

		db.close();
		return (mapping.findForward(pageForward));
	}

}
