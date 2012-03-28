/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 主题展示定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs;

import java.sql.ResultSet;
import java.util.Vector;
/** 
* 主题展示及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class TopicDisp extends Topic {


	/**
	 * 主题的最后回复时间
	 */
	private String lastTalk;

	/**
	 * 构造函数
	 */
	public TopicDisp() {
	}


	/**得到主题的最后回复时间
	 * @return 主题的最后回复时间
	 */
	public String getLastTalk() {

		return lastTalk;
	}

	/**得到xx版块的主题数量
	 * @param db 数据库
	 * @param forumid  版块编号
	 * @return 主题数量
	 * @throws Exception
	 */
	public int getTopicCount(DB db, int forumid) throws Exception {
		ResultSet rs;
		String strSql = null;
		int iRecordCount = 0;

		strSql = "select count(*) from topic where forumid=" + forumid;
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iRecordCount = rs.getInt(1);
		}
		return iRecordCount;
	}

	/**得到当前版块的所有主题
	 * @param db 数据库
	 * @param pageid 页面
	 * @return 主题数组
	 * @throws Exception
	 */
	public Vector<Topic> search(DB db, int pageid) throws Exception {
		Vector<Topic> Topics = new Vector<Topic>();
		ResultSet rs,rsNest;
		String strSql = null;
		int iCurRecord = 0;

		strSql = "select * from topic where forumid=" + forumid
				+ " order by topicid desc";
		rs = db.OpenSql(strSql);

		int iCount = 0;
		iCurRecord = pageid * Constants.TOPIC_PAGE_SIZE + 1;
		rs.absolute(iCurRecord);
		do {
			TopicDisp topic = new TopicDisp();

			topic.setTopicid(rs.getInt("topicid"));
			topic.setTitle(rs.getString("title"));
			topic.setContent(rs.getString("content"));
			topic.setAuthor(rs.getString("author"));
			topic.setSubmittime(rs.getString("submittime"));
			topic.setForumid(forumid);
			topic.setLastTalk(rs.getString("submittime"));
			topic.setViewNum(rs.getInt("viewNum"));
			topic.setResponseNum(rs.getInt("responseNum"));
			topic.setAuthorid(rs.getInt("authorid"));
			strSql = "select * from response where topicid=" +topic.getTopicid()
					+ " order by responseid desc";
			rsNest = db.OpenSql(strSql);
			if (rsNest.next()) {
				topic.setLastTalk(rsNest.getString("submittime"));
			}

			Topics.add(topic);
			iCount++;
			if (iCount >= Constants.TOPIC_PAGE_SIZE) {
				break;
			}
		} while (rs.next());
		return Topics;
	}


	/**设置最后回复时间
	 * @param lastTalk 最后回复时间
	 */
	public void setLastTalk(String lastTalk) {

		this.lastTalk = lastTalk;
	}
	
}