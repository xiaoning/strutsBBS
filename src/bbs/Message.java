/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ����Ϣ�ඨ��
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
* ����Ϣ�༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class Message {
	/**
     * Description : �����ݿ�ɾ������Ϣ
     * @param db ���ݿ�
     * @param id ����Ϣ���
     * @return true�����ɹ� false����ʧ��
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
     * Description : ɾ�����յ���Ϣ
      * @param db ���ݿ�
     * @param id ��Ϣ���
     * @return true �����ɹ� false ����ʧ��
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
     * Description : ɾ�����͵���Ϣ
     * @param db ���ݿ�
     * @param id ��Ϣ���
     * @return true �����ɹ� false ����ʧ��
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
     * Description :ȡ�ý��յĶ���Ϣ
     * @param db ���ݿ�
     * @param id �����߱� ��
     * @return Contents Vector<Message> ��Ϣ����
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
     * Description :ȡ�÷��͵Ķ���Ϣ
     * @param db ���ݿ�
     * @param id �����߱� ��
     * @return Contents Vector<Message> ��Ϣ����
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
     * Description : ȡ��δ����Ϣ��Ŀ
     * @param db ���ݿ�
     * @param toid �����߱��
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
     * Description : ����Ϣ��ǳ��Ѷ�
     * @param db ���ݿ� 
     * @param id ��Ϣ���
     * @return true�����ɹ� false����ʧ��
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
	 * ��Ϣ���
	 */
	private int id;
	/**
	 * ��Ϣ���߱��
	 */
	private int fromid;
	/**
	 * Ц�Ž����߱��
	 */
	private int toid;
	/**
	 * ��Ϣ�Ƿ��Ѷ�
	 */
	private int isread;
	/**
	 * ��Ϣ����������
	 */
	private String fromname;
	/**
	 * ��Ϣ����������
	 */
	private String toname;
	/**
	 * ��Ϣ����
	 */
	private String title;
	/**
	 * ��Ϣ����
	 */
	private String content;
	/**
	 * ��Ϣ�ύʱ��
	 */
	private String submittime;
	/**
	 * �Ƿ񱻷�����ɾ��
	 */
	private int isdeletebyfrom;
	/**
	 * �Ƿ񱻽�����ɾ��
	 */
	private int isdeletebyto;
	/**
     * Description : �õ�����
     * @param void
     * @return  content ����Ϣ����
     */
	public String getContent() {
		return content;
	}
	/**
     * Description : ȡ�÷����߱��
     * @param void
     * @return  fromid �����߱��
     */
	public int getFromid() {
		return fromid;
	}
	/**
     * Description : �õ���������
     * @param void
     * @return  ����������
     */
	public String getFromname() {
		return fromname;
	}
	/**
     * Description : �õ�����Ϣ���
     * @param void
     * @return id int ����Ϣ���
     */
	public int getId() {
		return id;
	}
	/**
     * Description :�õ��Ƿ�ɾ�����͵���Ϣ
     * @param void
     * @return isdeletebyfrom 0Ϊδɾ����1Ϊ��ɾ��
     */
	public int getIsdeletebyfrom() {
		return isdeletebyfrom;
	}
	/**
     * Description :�õ��Ƿ�ɾ�����յ���Ϣ
     * @param void
     * @return isdeletebyto 0Ϊδɾ����1Ϊ��ɾ��
     */
	public int getIsdeletebyto() {
		return isdeletebyto;
	}
	/**
     * Description : �õ��Ƿ��Ѷ�
     * @param void
     * @return isread 0Ϊδ����1Ϊ�Ѷ�
     */
	public int getIsread() {
		return isread;
	}
	/**
     * Description :�õ�����ʱ��
     * @param void
     * @return submittime ����ʱ��
     */
	public String getSubmittime() {
		return submittime;
	}
	/**
     * Description : �õ�����
     * @param void
     * @return ����
     */
	public String getTitle() {
		return title;
	}
	/**
     * Description : �õ������߱��
     * @param void
     * @return  toid �����߱��
     */
	public int getToid() {
		return toid;
	}
	/**
     * Description : ���ý���������
     * @param void
     * @return ����������
     */
	public String getToname() {
		return toname;
	}
	/**
     * Description : ���Ͷ���Ϣ
     * @param db ���ݿ�
     * @return true�����ɹ� false����ʧ��
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
     * Description : ��������
     * @param content ����Ϣ����
     * @return void
     */
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * Description : ���÷�����id
     * @param id ������id
     * @return void
     */
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	/**
     * Description : ���÷���������
     * @param fromname ����������
     * @return void
     */
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	/**
     * Description :���ö���Ϣ���
     * @param id ����Ϣ���
     * @return void
     */
	public void setId(int id) {
		this.id = id;
	}
	/**
     * Description : �����Ƿ�ɾ�����͵���Ϣ
     * @param isdeletebyfrom 0Ϊδɾ����1Ϊ��ɾ��
     * @return void
     */
	public void setIsdeletebyfrom(int isdeletebyfrom) {
		this.isdeletebyfrom = isdeletebyfrom;
	}

	/**
     * Description :�����Ƿ�ɾ�����յ���Ϣ
     * @param isdeletebyto 0Ϊδɾ����1Ϊ��ɾ��
     * @return void
     */
	public void setIsdeletebyto(int isdeletebyto) {
		this.isdeletebyto = isdeletebyto;
	}
	/**
     * Description : �����Ƿ��Ѷ�
     * @param isread 0Ϊδ����1Ϊ�Ѷ�
     * @return void
     */
	public void setIsread(int isread) {
		this.isread = isread;
	}
	/**
     * Description : ���÷���ʱ��
     * @param submittime ����ʱ��
     * @return void
     */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	/**
     * Description : ���ñ���
     * @param  title ����
     * @return  void
     */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Description : ���ý����߱��
     * @param toid �����߱��
     * @return void
     */
	public void setToid(int toid) {
		this.toid = toid;
	}
	/**
     * Description : ���ý���������
     * @param toname ����������
     * @return void
     */
	public void setToname(String toname) {
		this.toname = toname;
	}
	
	

}
