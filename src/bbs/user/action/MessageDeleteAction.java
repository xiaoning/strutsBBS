/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ¶ÌÏûÏ¢É¾³ý²Ù×÷
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/    
package bbs.user.action;


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
import bbs.Message;
import bbs.user.form.MessageForm;
/** 
* É¾³ý¶ÌÏûÏ¢²Ù×÷
* @author keguolin
* @version 1.0.0
*/ 
public class MessageDeleteAction extends Action
{
	/**
     * Description :ÏûÏ¢É¾³ý²Ù×÷
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
		int id=newform.getId();
		String type=newform.getType();
		int userid=(Integer)session.getAttribute(Constants.USERIDKEY);
		if(type.equals("send"))
		{
			Message.deleteSendMessage(db, id);
			pageForward="ToMessageIndex";
			Vector<Message> msgvector=Message.getSend(db, userid);
			session.setAttribute(Constants.MESSAGE_LIST, msgvector);
			return mapping.findForward(pageForward);
			
		}
		else if(type.equals("recieve"))
		{
			Message.deleteRecieveMessage(db, id);
			pageForward="ToMessageIndex";
			Vector<Message> msgvector=Message.getRecieve(db, userid);
			session.setAttribute(Constants.MESSAGE_LIST, msgvector);
			return mapping.findForward(pageForward);
		}
		else session.setAttribute(Constants.ERROR_MSG, "É¾³ýÊ§°Ü");
		db.close();
		return mapping.findForward(pageForward);
	}
}
