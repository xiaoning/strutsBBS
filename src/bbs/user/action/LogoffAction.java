/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : �û�ע����½����
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
/** 
* ע����½����
* @author keguolin
* @version 1.0.0
*/ 
public class LogoffAction extends Action {
	/**
     * Description :ע����¼
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping map, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		return map.findForward("ToUserLogin");
	}
}