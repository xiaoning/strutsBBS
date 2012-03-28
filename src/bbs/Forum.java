/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 版块类定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs;

import java.sql.ResultSet;
import java.util.Vector;
/** 
* 版块类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class Forum {

	/**
     * Description : 在数据库删除版块信息，并且将其相关的文章和回复删除
     * @param db 数据库
     * @param forumid 论坛编号
     * @return true操作成功  false操作失败
     */
	public static boolean delete(DB db, String forumid) throws Exception {
		String strSql;
		strSql = "delete from forum where forumid=" + forumid;
		if (db.ExecSql(strSql) == 0) {
			return false;
		}
		strSql = "delete from topic where forumid=" + forumid;
		if (db.ExecSql(strSql) == 0) {
			return false;
		}
		strSql = "delete from response where forumid=" + forumid;
		if (db.ExecSql(strSql) == 0) {
			return false;
		}
		return true;
	}

	/**
     * Description : 更新论坛的管理员
     * @param db 数据库
     * @param forumid 论坛id
     * @param forumname 论坛名称
     * @param manager 管理员名字
     * @return true操作成功  false操作失败
     */
	public static boolean edit(DB db, String forumid, String forumname,
			String manager) throws Exception {
		String strSql;
		strSql = "update forum set forumname='" + forumname + "',manager='"
				+ manager + "'where forumid=" + forumid;
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
     * Description : 在数据库插入新的版块信息
     * @param db 数据库
     * @param forumname 版块名称
     * @return true 插入成功 false 插入失败
     */
	public static boolean insert(DB db, String forumname) throws Exception {
		String strSql;
		ResultSet rs;
		int iMaxId;
		strSql = "select max(forumid) from forum";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}

		strSql = "insert into forum " +"(forumid,forumname,manager) "+
				"values('" + iMaxId + "','" + forumname
				+ "','')";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
     * Description : 找出所有版块的详细信息
     * @param db DB 数据库类
     * @return forumVector Vector<Forum>保存版块信息的数组
     */
	public static Vector<Forum> search(DB db) throws Exception {
		int topicNum = 0;
		int lastTopicId;
		String lastTopicTitle;
		String lastTopicAuthor;
		String lastTopicTime;

		Vector<Forum> forumVector = new Vector<Forum>();
		ResultSet rs, rsTopic;
		String strSql;
		int forumid;

		strSql = "select * from forum";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			lastTopicId = rs.getInt("lastTopicId");
			lastTopicTitle = rs.getString("lastTopicTitle");
			lastTopicAuthor = rs.getString("lastTopicAuthor");
			lastTopicTime = rs.getString("lastTopicTime");
			forumid = rs.getInt("forumid");
			strSql = "select count(*) from topic where forumid=" + forumid;
			rsTopic = db.OpenSql(strSql);
			if (rsTopic.next()) {
				topicNum = rsTopic.getInt(1);
			}
			forumVector.add(new Forum(forumid, rs.getString("forumname"), rs
					.getString("manager"), topicNum, lastTopicId,
					lastTopicTitle,lastTopicAuthor, lastTopicTime));

		}
		return forumVector;
	}

	/**
     * Description : 找出所有的版块的简单信息
     * @param db DB 数据库类
     * @return forumVector Vector<Forum>保存版块信息的数组
     */
	public static Vector<Forum> searchAllForums(DB db) throws Exception {
		int forumid = 0;
		String forumname = null;
		String manager = null;

		Vector<Forum> forumVector = new Vector<Forum>();
		ResultSet rs;
		String strSql;

		strSql = "select * from forum";
		rs = db.OpenSql(strSql);

		while (rs.next()) {
			forumid = rs.getInt("forumid");
			forumname = rs.getString("forumname");
			manager = rs.getString("manager");

			forumVector.add(new Forum(forumid, forumname, manager));

		}
		return forumVector;
	}

	/**
	 * 版块编号
	 */
	private int id;//版块编号

	/**
	 * 版块名称
	 */
	private String forumname;//版块名称

	/**
	 * 版块管理员
	 */
	private String manager;//版块管理员

	/**
	 * 版块文章数量
	 */
	private int topicNum;//版块文章数量
	/**
	 * 版块最后一篇文章编号
	 */
	private int lastTopicId;//版块最后一篇文章的编号
	
	/**
	 * 版块最后一篇文章标题
	 */
	private String lastTopicTitle ;//版块最后一篇文章的标题
	/**
	 * 版块最后一篇文章作者
	 */
	private String lastTopicAuthor ;//版块最后一篇文章的作者
	/**
	 * 版块最后一篇文章时间
	 */
	private String lastTopicTime ;//版块最后一篇文章的时间
	
	/**
     * Description :构造函数1
     * @param id 版块编号
     * @param forumname 版块名称
     * @param manager 管理员
     */
	public Forum(int id, String forumname, String manager) {
		this.id = id;
		this.forumname = forumname;
		this.manager = manager;
	}

	/**
     * Description :构造函数2
     * @param id 版块编号
     * @param forumname 版块名称
     * @param manager 版块管理员
     * @param topicNum 版块主题数量
     * @param lastTopicId 版块最后一篇文章编号
     * @param lastTopicTitle 版块最后一篇文章标题
     * @param lastTopicAuthor 版块最后一篇文章作者
     * @param lastTopicTime 版块最后一篇文章时间
     * @return 
     */
	
	public Forum(int id, String forumname, String manager, int topicNum,
			int lastTopicId, String lastTopicTitle, String lastTopicAuthor,
			String lastTopicTime) {
		this.id = id;
		this.forumname = forumname;
		this.manager = manager;
		this.topicNum = topicNum;
		this.lastTopicId = lastTopicId;
		this.lastTopicTitle = lastTopicTitle;
		this.lastTopicAuthor = lastTopicAuthor;
		this.lastTopicTime = lastTopicTime;

	}
	/**
     * Description :获得版块名字
     * @param 无
     * @return 版块名字 String
     */
	public String getForumname() {
		return forumname;
	}
	/**
     * Description : 得到版块编号
     * @param void
     * @return id int 版块编号
     */
	public int getId() {
		return id;
	}
	/**
     * Description : 获得最后一个文章的作者名字
     * @param void
     * @return lastTopicAuthor String 最后一个文章的作者名字
     */
	public String getLastTopicAuthor() {
		return lastTopicAuthor;
	}
	/**
     * Description : 得到最后一个文章的编号
     * @param void
     * @return lastTopicId 最后一个文章的编号
     */
	public int getLastTopicId() {
		return lastTopicId;
	}
	/**
     * Description :得到最后一个文章的发表时间
     * @param void
     * @return lastTopicTime 最后一个文章的发表时间
     */
	public String getLastTopicTime() {
		return lastTopicTime;
	}
	/**
     * Description : 得到最后一篇文章的标题
     * @param void
     * @return lastTopicTitle String 最后一篇文章的标题
     */
	public String getLastTopicTitle() {
		return lastTopicTitle;
	}
	/**
     * Description :得到版主名字
     * @param void
     * @return manager String 版主名字
     */
	public String getManager() {
		return manager;
	}
	/**
     * Description : 得到主题数量
     * @param void
     * @return topicNum int 主题数量
     */
	public int getTopicNum() {
		return topicNum;
	}
	/**
     * Description : 设置版块名字
     * @param forumname String 版块名字
     * @return void
     */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	/**
     * Description : 设置版块编号
     * @param id int 版块编号
     * @return void
     */
	public void setId(int id) {
		this.id = id;
	}
	/**
     * Description : 设置最后一个文章的作者名字
     * @param lastTopicAuthor String 最后一个文章的作者名字
     * @return void
     */
	public void setLastTopicAuthor(String lastTopicAuthor) {
		this.lastTopicAuthor = lastTopicAuthor;
	}
	
	/**
     * Description :设置最后一个文章的编号
     * @param  lastTopicId 文章编号
     * @return void
     */
	public void setLastTopicId(int lastTopicId) {
		this.lastTopicId = lastTopicId;
	}
	/**
     * Description : 设置最后一个文章的发表时间
     * @param lastTopicTime String
     * @return void
     */
	public void setLastTopicTime(String lastTopicTime) {
		this.lastTopicTime = lastTopicTime;
	}
	/**
     * Description :设置最后一篇文章的标题
     * @param  lastTopicTitle String 最后一篇文章的标题
     * @return void
     */
	public void setLastTopicTitle(String lastTopicTitle) {
		this.lastTopicTitle = lastTopicTitle;
	}
	/**
     * Description : 设置版主名字
     * @param manager String 版主名字
     * @return void
     */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
     * Description : 设置主题数量
     * @param topicNum int 主题数量
     * @return void
     */
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}


}