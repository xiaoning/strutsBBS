/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 管理员登录事件
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/   
package bbs.admin.action;



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
import bbs.User;
import bbs.admin.form.AdminLoginForm;


/** 
* 管理员登录事件
* @author keguolin
* @version 1.0.0
*/ 
public final class AdminLoginAction extends Action 
{
	/**
     * Description : 管理员登陆操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AdminLoginForm userform = (AdminLoginForm) form;
		String username = userform.getUsername();
		String password=userform.getPassword();

		DB db = new DB();
		HttpSession session = request.getSession(true);

		String PageForward="";
		if(User.checkUser(db, username, password)){
			int id=User.getId(db, username);
			session.setAttribute(Constants.USERNAME_KEY, username);
			session.setAttribute(Constants.USERIDKEY, id);
			if ("admin".equals(User.getUserGrade(db, username))) {
				session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "admin");
				PageForward = "ToAdminIndex";
			} else if ("banzhu".equals(User.getUserGrade(db, username))) {
				session.setAttribute(Constants.LOGIN_USERGRADE_KEY, "manager");
				PageForward = "ToAdminIndex";
			} 
			else
			{
				session.setAttribute(Constants.ERROR_MSG, "非管理员登录");
				PageForward = "ToErrorPage";
			}
		}
		else {
			session.setAttribute(Constants.ERROR_MSG, "用户名或密码错误");
			PageForward = "ToErrorPage";
		}

		db.close();
		return mapping.findForward(PageForward);
	}

}
