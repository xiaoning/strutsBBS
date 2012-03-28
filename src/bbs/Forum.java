/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ����ඨ��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs;

import java.sql.ResultSet;
import java.util.Vector;
/** 
* ����༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class Forum {

	/**
     * Description : �����ݿ�ɾ�������Ϣ�����ҽ�����ص����ºͻظ�ɾ��
     * @param db ���ݿ�
     * @param forumid ��̳���
     * @return true�����ɹ�  false����ʧ��
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
     * Description : ������̳�Ĺ���Ա
     * @param db ���ݿ�
     * @param forumid ��̳id
     * @param forumname ��̳����
     * @param manager ����Ա����
     * @return true�����ɹ�  false����ʧ��
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
     * Description : �����ݿ�����µİ����Ϣ
     * @param db ���ݿ�
     * @param forumname �������
     * @return true ����ɹ� false ����ʧ��
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
     * Description : �ҳ����а�����ϸ��Ϣ
     * @param db DB ���ݿ���
     * @return forumVector Vector<Forum>��������Ϣ������
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
     * Description : �ҳ����еİ��ļ���Ϣ
     * @param db DB ���ݿ���
     * @return forumVector Vector<Forum>��������Ϣ������
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
	 * �����
	 */
	private int id;//�����

	/**
	 * �������
	 */
	private String forumname;//�������

	/**
	 * ������Ա
	 */
	private String manager;//������Ա

	/**
	 * �����������
	 */
	private int topicNum;//�����������
	/**
	 * ������һƪ���±��
	 */
	private int lastTopicId;//������һƪ���µı��
	
	/**
	 * ������һƪ���±���
	 */
	private String lastTopicTitle ;//������һƪ���µı���
	/**
	 * ������һƪ��������
	 */
	private String lastTopicAuthor ;//������һƪ���µ�����
	/**
	 * ������һƪ����ʱ��
	 */
	private String lastTopicTime ;//������һƪ���µ�ʱ��
	
	/**
     * Description :���캯��1
     * @param id �����
     * @param forumname �������
     * @param manager ����Ա
     */
	public Forum(int id, String forumname, String manager) {
		this.id = id;
		this.forumname = forumname;
		this.manager = manager;
	}

	/**
     * Description :���캯��2
     * @param id �����
     * @param forumname �������
     * @param manager ������Ա
     * @param topicNum �����������
     * @param lastTopicId ������һƪ���±��
     * @param lastTopicTitle ������һƪ���±���
     * @param lastTopicAuthor ������һƪ��������
     * @param lastTopicTime ������һƪ����ʱ��
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
     * Description :��ð������
     * @param ��
     * @return ������� String
     */
	public String getForumname() {
		return forumname;
	}
	/**
     * Description : �õ������
     * @param void
     * @return id int �����
     */
	public int getId() {
		return id;
	}
	/**
     * Description : ������һ�����µ���������
     * @param void
     * @return lastTopicAuthor String ���һ�����µ���������
     */
	public String getLastTopicAuthor() {
		return lastTopicAuthor;
	}
	/**
     * Description : �õ����һ�����µı��
     * @param void
     * @return lastTopicId ���һ�����µı��
     */
	public int getLastTopicId() {
		return lastTopicId;
	}
	/**
     * Description :�õ����һ�����µķ���ʱ��
     * @param void
     * @return lastTopicTime ���һ�����µķ���ʱ��
     */
	public String getLastTopicTime() {
		return lastTopicTime;
	}
	/**
     * Description : �õ����һƪ���µı���
     * @param void
     * @return lastTopicTitle String ���һƪ���µı���
     */
	public String getLastTopicTitle() {
		return lastTopicTitle;
	}
	/**
     * Description :�õ���������
     * @param void
     * @return manager String ��������
     */
	public String getManager() {
		return manager;
	}
	/**
     * Description : �õ���������
     * @param void
     * @return topicNum int ��������
     */
	public int getTopicNum() {
		return topicNum;
	}
	/**
     * Description : ���ð������
     * @param forumname String �������
     * @return void
     */
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	/**
     * Description : ���ð����
     * @param id int �����
     * @return void
     */
	public void setId(int id) {
		this.id = id;
	}
	/**
     * Description : �������һ�����µ���������
     * @param lastTopicAuthor String ���һ�����µ���������
     * @return void
     */
	public void setLastTopicAuthor(String lastTopicAuthor) {
		this.lastTopicAuthor = lastTopicAuthor;
	}
	
	/**
     * Description :�������һ�����µı��
     * @param  lastTopicId ���±��
     * @return void
     */
	public void setLastTopicId(int lastTopicId) {
		this.lastTopicId = lastTopicId;
	}
	/**
     * Description : �������һ�����µķ���ʱ��
     * @param lastTopicTime String
     * @return void
     */
	public void setLastTopicTime(String lastTopicTime) {
		this.lastTopicTime = lastTopicTime;
	}
	/**
     * Description :�������һƪ���µı���
     * @param  lastTopicTitle String ���һƪ���µı���
     * @return void
     */
	public void setLastTopicTitle(String lastTopicTitle) {
		this.lastTopicTitle = lastTopicTitle;
	}
	/**
     * Description : ���ð�������
     * @param manager String ��������
     * @return void
     */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
     * Description : ������������
     * @param topicNum int ��������
     * @return void
     */
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}


}