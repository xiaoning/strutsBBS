/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 显示短消息操作
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
import bbs.Message;
import bbs.user.form.*;

import java.util.*;
/** 
* 显示短消息操作
* @author keguolin
* @version 1.0.0
*/ 
public class MessageShowAction extends Action
{
	/**
     * Description :显示短消息操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		MessageForm newform=(MessageForm) form;
		String pageForward = "ToErrorPage";
		DB db=new DB();
		HttpSession session = request.getSession(true);
		String fromname = (String) session.getAttribute(Constants.USERNAME_KEY);
		int id=(Integer)session.getAttribute(Constants.USERIDKEY);
		String type=newform.getType();
		session.setAttribute(Constants.MESSAGE_SHOWTYPE, type);
		if(!fromname.equals("guest"))
		{
			
				if(type.equals("send")){
					pageForward="ToMessageIndex";
					Vector<Message> msgvector=Message.getSend(db, id);
					session.setAttribute(Constants.MESSAGE_LIST, msgvector);
					return mapping.findForward(pageForward);
				}
				else if(type.equals("recieve")){
					pageForward="ToMessageIndex";
					Vector<Message> msgvector=Message.getRecieve(db, id);
					session.setAttribute(Constants.MESSAGE_Count, 0);
					session.setAttribute(Constants.MESSAGE_LIST, msgvector);
					return mapping.findForward(pageForward);
				}

		}
		else session.setAttribute(Constants.ERROR_MSG, "非登录用户无法查看消息");
		db.close();
		return mapping.findForward(pageForward);
	}
}
