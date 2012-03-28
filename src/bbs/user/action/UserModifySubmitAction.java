/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 用户信息修改提交操作
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
import bbs.User;
import bbs.user.form.RegistrationForm;


/** 
* 用户登录操作
* @author keguolin
* @version 1.0.0
*/ 
public final class UserModifySubmitAction extends Action
{
	/**
     * Description :用户修改提交操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RegistrationForm rgtForm = (RegistrationForm) form;
		int id=rgtForm.getId();
		String username=rgtForm.getUsername();
		String password = rgtForm.getPassword();
		String oldpassword=rgtForm.getOldpassword();
		String sex = rgtForm.getSex();
		String email = rgtForm.getEmail();
		String icq = rgtForm.getIcq();
		String signature=rgtForm.getSignature();
		HttpSession session = request.getSession(true);
		DB db = new DB();
		String pageForward=null;
		
		if(User.updateEmail(db, id, email)&&
				User.updateQq(db, id, icq)&&
				User.updateSex(db, id, sex)&&
				User.updateSignature(db, id, signature))
		{
			pageForward="UserLoginSucceed";
		}
		else
		{
			session.setAttribute(Constants.ERROR_MSG, "修改失败");
			pageForward = "ToErrorPage";
		}
		if ((password!=null && password!="" &&oldpassword!=null && oldpassword!="" && !username.equals("guest"))
				&& (!User.checkUser(db, username, oldpassword))) 
		{
			session.setAttribute(Constants.ERROR_MSG, "原密码错误");
			pageForward = "ToErrorPage";
			return mapping.findForward(pageForward);
		}		
		else if(password!=null && password!=""  && !username.equals("guest"))
		{
			if(User.updatePassword(db, id, password))
				pageForward="ToUserLogin";
			else
			{
				session.setAttribute(Constants.ERROR_MSG, "密码修改失败");
				pageForward = "ToErrorPage";
			}
		}
		db.close();
		return mapping.findForward(pageForward);
	}
}
