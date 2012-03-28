/**
  * Copy Right Information  : ICanon
  * Project                 : j2eebbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 文章创建预操作
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
import bbs.Response;
import bbs.Topic;
import bbs.user.form.NewArticleForm;

/** 
* 文章创建预操作
* @author keguolin
* @version 1.0.0
*/ 
public final class ArticleCreatAction extends Action {
	/**
     * Description :文章创建操作
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		NewArticleForm newform = (NewArticleForm) form;
		String username = (String) session.getAttribute(Constants.USERNAME_KEY);
		String PageForward=null;
		DB db = new DB();
		if(newform!=null)
		{
			int tempid=newform.getId();
			String temptype=newform.getModifytype();
			String temptitle=null;
			String tempcontent=null;
			if(temptype.equals("response"))
			{
				tempcontent=Response.getContentByID(db, tempid);
				temptitle=Response.getTitleByID(db, tempid);
			}
			else if(temptype.equals("topic"))
			{
				tempcontent=Topic.getContentByID(db, tempid);
				temptitle=Topic.getTitleByID(db, tempid);
			}
			db.close();
			session.setAttribute(Constants.OLD_ID, tempid);
			session.setAttribute(Constants.OLD_TYPE,temptype);
			session.setAttribute(Constants.OLD_TITLE, temptitle);
			session.setAttribute(Constants.OLD_CONTEND, tempcontent);
			PageForward="ModifyArticle";
			
		}
		else if (username == null||username.equals("guest")) 
		{
			session.setAttribute(Constants.ERROR_MSG, "登录后方可发言");
			PageForward = "ToUserLogin";

		}
		else {
			
			PageForward = "NewArticle";
		}
		return (mapping.findForward(PageForward));
	}

}
