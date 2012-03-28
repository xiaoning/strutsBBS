/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 短消息类定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/
package bbs;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
/** 
* 短消息类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class Message {
	/**
     * Description : 从数据库删除短消息
     * @param db 数据库
     * @param id 短消息编号
     * @return true操作成功 false操作失败
     */
	public static boolean  delete(DB db,int id) throws Exception
	{
		String strSql;
		strSql = "delete from message where messageid=" + id;
		if (db.ExecSql(strSql) == 0){
			return false;
		}
		return true;
	}
	/**
     * Description : 删除接收的消息
      * @param db 数据库
     * @param id 消息编号
     * @return true 操作成功 false 操作失败
     */
	public static boolean deleteRecieveMessage(DB db,int id) throws Exception
	{
		String strSql;
		strSql = "update message set isdeletebyto=" +1+
				" where messageid=" + id;
		if (db.ExecSql(strSql) == 0){
			return false;
		}
		strSql = "select isdeletebyfrom from message where messageid=" + id;
		ResultSet rs = db.OpenSql(strSql);
		if(rs.next())
		{
			int a=rs.getInt(1);
			if(a!=0)
			{
				delete(db,id);
			}
		}
		return true;
	}
	/**
     * Description : 删除发送的消息
     * @param db 数据库
     * @param id 消息编号
     * @return true 操作成功 false 操作失败
     */
	public static boolean deleteSendMessage(DB db,int id) throws Exception
	{
		String strSql;
		strSql = "update message set isdeletebyfrom=" +1+
				" where messageid=" + id;
		if (db.ExecSql(strSql) == 0){
			return false;
		}
		strSql = "select isdeletebyto from message where messageid=" + id;
		ResultSet rs = db.OpenSql(strSql);
		if(rs.next())
		{
			int a=rs.getInt(1);
			if(a!=0)
			{
				delete(db,id);
			}
		}
		return true;
	}
	/**
     * Description :取得接收的短消息
     * @param db 数据库
     * @param id 接收者编 号
     * @return Contents Vector<Message> 消息数组
     */
	public static Vector<Message> getRecieve(DB db,int toid) throws Exception
	{
		Vector<Message> Contents = new Vector<Message>();
		ResultSet rs;
        String strSql=null;
		strSql = "select * from message where toid=" + toid +" order by messageid desc";
		rs = db.OpenSql(strSql);
		while(rs.next()){
			Message msg=new Message();
			msg.setContent(rs.getString("content"));
			msg.setTitle(rs.getString("title"));
			msg.setFromid(rs.getInt("fromid"));
			msg.setFromname(rs.getString("fromname"));
			msg.setToid(rs.getInt("toid"));
			msg.setToname(rs.getString("toname"));
			msg.setSubmittime(rs.getString("submittime"));
			msg.setId(rs.getInt("messageid"));
			msg.setIsread(rs.getInt("isread"));
			read(db,msg.getId());
			int isdelete=rs.getInt("isdeletebyto");
			if(isdelete==0){
				Contents.add(msg);
			}
		}
		return Contents;
	}
	/**
     * Description :取得发送的短消息
     * @param db 数据库
     * @param id 发送者编 号
     * @return Contents Vector<Message> 消息数组
     */
	public static Vector<Message> getSend(DB db,int fromid) throws Exception
	{
		Vector<Message> Contents = new Vector<Message>();
		ResultSet rs;
        String strSql=null;
		strSql = "select * from message where fromid=" + fromid +" order by messageid desc";
		rs = db.OpenSql(strSql);
		while(rs.next()){
			Message msg=new Message();
			msg.setContent(rs.getString("content"));
			msg.setTitle(rs.getString("title"));
			msg.setFromid(rs.getInt("fromid"));
			msg.setFromname(rs.getString("fromname"));
			msg.setToid(rs.getInt("toid"));
			msg.setToname(rs.getString("toname"));
			msg.setSubmittime(rs.getString("submittime"));
			msg.setId(rs.getInt("messageid"));
			msg.setIsread(rs.getInt("isread"));
			int isdelete=rs.getInt("isdeletebyfrom");
			if(isdelete==0){
				Contents.add(msg);
			}
		}
		return Contents;
	}
	/**
     * Description : 取得未读消息数目
     * @param db 数据库
     * @param toid 接收者编号
     * @return 
     */
	public static int getUnreadCount(DB db,int toid) throws Exception
	{
		ResultSet rs;
        String strSql=null;
        int count=0;
		strSql = "select isread from message where toid=" + toid;
		rs = db.OpenSql(strSql);
		while(rs.next())
		{
			int r=rs.getInt(1);
			if(r==0) count++;
		}
		return count;
	}
	/**
     * Description : 将消息标记成已读
     * @param db 数据库 
     * @param id 消息编号
     * @return true操作成功 false操作失败
     */
	public static boolean read(DB db,int id) throws Exception
	{
		String strSql;
		strSql = "update message set isread=" +1+
				" where messageid=" + id;
		if (db.ExecSql(strSql) == 0){
			return false;
		}
		return true;
	}
	/**
	 * 消息编号
	 */
	private int id;
	/**
	 * 消息作者编号
	 */
	private int fromid;
	/**
	 * 笑着接收者编号
	 */
	private int toid;
	/**
	 * 消息是否已读
	 */
	private int isread;
	/**
	 * 消息发送者名字
	 */
	private String fromname;
	/**
	 * 消息接收者名字
	 */
	private String toname;
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 消息提交时间
	 */
	private String submittime;
	/**
	 * 是否被发送者删除
	 */
	private int isdeletebyfrom;
	/**
	 * 是否被接收者删除
	 */
	private int isdeletebyto;
	/**
     * Description : 得到内容
     * @param void
     * @return  content 短消息内容
     */
	public String getContent() {
		return content;
	}
	/**
     * Description : 取得发送者编号
     * @param void
     * @return  fromid 发送者编号
     */
	public int getFromid() {
		return fromid;
	}
	/**
     * Description : 得到发送名字
     * @param void
     * @return  发送者名字
     */
	public String getFromname() {
		return fromname;
	}
	/**
     * Description : 得到短消息编号
     * @param void
     * @return id int 短消息编号
     */
	public int getId() {
		return id;
	}
	/**
     * Description :得到是否删除发送的消息
     * @param void
     * @return isdeletebyfrom 0为未删除，1为已删除
     */
	public int getIsdeletebyfrom() {
		return isdeletebyfrom;
	}
	/**
     * Description :得到是否删除接收的消息
     * @param void
     * @return isdeletebyto 0为未删除，1为已删除
     */
	public int getIsdeletebyto() {
		return isdeletebyto;
	}
	/**
     * Description : 得到是否已读
     * @param void
     * @return isread 0为未读，1为已读
     */
	public int getIsread() {
		return isread;
	}
	/**
     * Description :得到发送时间
     * @param void
     * @return submittime 发送时间
     */
	public String getSubmittime() {
		return submittime;
	}
	/**
     * Description : 得到标题
     * @param void
     * @return 标题
     */
	public String getTitle() {
		return title;
	}
	/**
     * Description : 得到接收者编号
     * @param void
     * @return  toid 接收者编号
     */
	public int getToid() {
		return toid;
	}
	/**
     * Description : 设置接收者名字
     * @param void
     * @return 接收者名字
     */
	public String getToname() {
		return toname;
	}
	/**
     * Description : 发送短消息
     * @param db 数据库
     * @return true操作成功 false操作失败
     */
	public boolean sendMessage(DB db) throws Exception
	{
		String strSql;
		ResultSet rs;
		int iMaxId;
		strSql = "Select max(messageid) From message";
		rs = db.OpenSql(strSql);
		if (rs.next()) {
			iMaxId = rs.getInt(1) + 1;
		} else {
			iMaxId = 1;
		}
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		submittime=tempDate.format(new java.util.Date());
		strSql = "insert into message " +
				"(messageid,title,content,fromname,submittime," +
				"toname,fromid,toid,isread,isdeletebyfrom,isdeletebyto) "+
				"values(" + iMaxId + ",'" + title + "','"
				+ content + "','" + fromname + "','" + submittime + "','" + toname
				+ "',"+fromid+","+toid+","+"0,0,0)";
		if (db.ExecSql(strSql) == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
     * Description : 设置内容
     * @param content 短消息内容
     * @return void
     */
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * Description : 设置发送者id
     * @param id 发送者id
     * @return void
     */
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	/**
     * Description : 设置发送者名字
     * @param fromname 发送者名字
     * @return void
     */
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	/**
     * Description :设置短消息编号
     * @param id 短消息编号
     * @return void
     */
	public void setId(int id) {
		this.id = id;
	}
	/**
     * Description : 设置是否删除发送的消息
     * @param isdeletebyfrom 0为未删除，1为已删除
     * @return void
     */
	public void setIsdeletebyfrom(int isdeletebyfrom) {
		this.isdeletebyfrom = isdeletebyfrom;
	}

	/**
     * Description :设置是否删除接收的消息
     * @param isdeletebyto 0为未删除，1为已删除
     * @return void
     */
	public void setIsdeletebyto(int isdeletebyto) {
		this.isdeletebyto = isdeletebyto;
	}
	/**
     * Description : 设置是否已读
     * @param isread 0为未读，1为已读
     * @return void
     */
	public void setIsread(int isread) {
		this.isread = isread;
	}
	/**
     * Description : 设置发送时间
     * @param submittime 发送时间
     * @return void
     */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	/**
     * Description : 设置标题
     * @param  title 标题
     * @return  void
     */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Description : 设置接收者编号
     * @param toid 接收者编号
     * @return void
     */
	public void setToid(int toid) {
		this.toid = toid;
	}
	/**
     * Description : 设置接收者名字
     * @param toname 接收者名字
     * @return void
     */
	public void setToname(String toname) {
		this.toname = toname;
	}
	
	

}
