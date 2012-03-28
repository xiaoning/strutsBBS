/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 短消息创建预操作
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
* 创建短消息操作
* @author keguolin
* @version 1.0.0
*/ 
public class MessageCreatAction  extends Action
{
	/**
     * Description :消息创建操作
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
		if(rs.next())
		{
			session.setAttribute(Constants.USERNAME, rs.getString("username"));
			session.setAttribute(Constants.USERID, id);
			pageForward="ToSendMessage";
		}
		else
		{
			session.setAttribute(Constants.ERROR_MSG, "用户不存在");
			pageForward = "ToErrorPage";
			return mapping.findForward(pageForward);
		}
		db.close();
		return mapping.findForward(pageForward);
	}

}
