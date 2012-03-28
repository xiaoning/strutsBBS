/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 用户注册操作
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
* 注册操作
* @author keguolin
* @version 1.0.0
*/ 
public final class RegistrationAction extends Action {
	/**
     * Description :注册操作
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
		String username = rgtForm.getUsername();
		String password = rgtForm.getPassword();
		String sex = rgtForm.getSex();
		String email = rgtForm.getEmail();
		String icq = rgtForm.getIcq();
		String grade = rgtForm.getGrade();
		String signature=rgtForm.getSignature();
		HttpSession session = request.getSession(true);
		DB db = new DB();
		if(username==""||password==""||sex==""||email=="")
		{
			session.setAttribute(Constants.ERROR_MSG, "请将信息填写完整");
			String pageForward = "ToErrorPage";
			return mapping.findForward(pageForward);
		}
		else if(User.checkUser(db, username))
		{
			session.setAttribute(Constants.ERROR_MSG, "用户已存在");
			String pageForward = "ToErrorPage";
			return mapping.findForward(pageForward);
		}
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setEmail(email);
		user.setQq(icq);
		user.setGrade(grade);
		user.setSignature(signature);
		String pageForward;

		if (!username.equals("guest") && user.insert(db)) {
			pageForward = "ToUserLogin";
		} else {
			session.setAttribute(Constants.ERROR_MSG, "注册失败");
			pageForward = "ToErrorPage";
		}

		db.close();
		return mapping.findForward(pageForward);
	}
}