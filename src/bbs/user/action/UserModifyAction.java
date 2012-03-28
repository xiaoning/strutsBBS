/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �û���Ϣ�޸Ĳ���
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.action;

import java.sql.ResultSet;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bbs.Constants;
import bbs.DB;
import bbs.user.form.RegistrationForm;

/** 
* �û���¼����
* @author keguolin
* @version 1.0.0
*/ 
public final class UserModifyAction extends Action
{
	/**
     * Description :�û��޸Ĳ���
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
		int id = rgtForm.getId();
		HttpSession session = request.getSession(true);
		DB db = new DB();
		String pageForward=null;
		String strSql="";
		strSql="select * from user where id="+id;
		
		ResultSet rs;
		rs = db.OpenSql(strSql);
		String username2 = (String) session.getAttribute(Constants.USERNAME_KEY);
		if(rs.next())
		{
			String username=rs.getString("username");
			session.setAttribute(Constants.USERNAME, username);
			session.setAttribute(Constants.USEREMAIL, rs.getString("email"));
			session.setAttribute(Constants.USERQQ, rs.getString("qq"));
			session.setAttribute(Constants.userSig, rs.getString("signature"));
			session.setAttribute(Constants.USERSEX, rs.getString("sex"));
			session.setAttribute(Constants.USERID, id);
			if(username2.equals("guest")||!username2.equals(username))
			{
				session.setAttribute(Constants.ERROR_MSG, "û��Ȩ��");
				pageForward = "ToErrorPage";
				return mapping.findForward(pageForward);
			}
			pageForward="ToModifyUser";
		}
		else
		{
			session.setAttribute(Constants.ERROR_MSG, "�û�������");
			pageForward = "ToErrorPage";
			return mapping.findForward(pageForward);
		}
		db.close();
		return mapping.findForward(pageForward);
	}
}
