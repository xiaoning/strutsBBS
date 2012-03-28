/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ȫ�ֳ�������
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/



package bbs;



/** 
* sessionʹ�õĳ������� 
* @author keguolin
* @version 1.0.0
*/ 
public final class Constants {
	// Session keys
    /**
     * ��ǰ�û�����
     */
    public static final String USERNAME_KEY = "username"; 
    /**
     * ��̳���������
     */
    public static final String FORUM_LIST_KEY = "forums";  
    /**
     * ���Ᵽ�������
     */
    public static final String TOPIC_LIST_KEY = "topics";  
    /**
     * �ظ����������
     */
    public static final String RESPONSE_LIST_KEY = "responses"; 
    /**
     * �û����������
     */
    public static final String USER_LIST_KEY = "users";  
    /**
     * ��������
     */
    public static final String TALK_TYPE_KEY = "talkType"; 
    /**
     * ��ǰ�����
     */
    public static final String CUR_FORUMID_KEY = "CurForumid"; 
    /**
     * ��ǰ�������
     */
    public static final String CUR_FORUMNAME_KEY = "CurForumName"; 
    /**
     * ��ǰ������
     */
    public static final String CUR_TOPICID_KEY = "CurTopicid"; 
    /**
     * ��ǰҳ����
     */
    public static final String CUR_PAGEID_KEY = "CurPageid"; 
    /**
     * ��ǰ����ҳ������
     */
    public static final String CUR_PAGECOUNT_KEY="Curpagecount";
    /**
     * ����Ա
     */
    public static final String MANAGER_CANDIDATE_KEY = "managerCandidates"; 
    /**
     * �༭�İ����
     */
    public static final String EDIT_FORUMID_KEY = "editForumid"; 
    /**
     * �༭�İ������
     */
    public static final String EDIT_FORUMNAME_KEY = "editForumname"; 
    /**
     * ��½���û�����
     */
    public static final String LOGIN_USERGRADE_KEY = "loginUserGrade"; 
    /**
     * ���а����Ϣ
     */
    public static final String ALLFORUMS_KEY = "AllForums"; 
    /**
     * �����õĹؼ���
     */
    public static final String SEARCH_TOPIC_KEY = "searchTopic"; 
    /**
     * jdbc����
     */
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    /**
     * ��������
     */
    public static final String DATABASE_URL = "jdbc:mysql://localhost/BBS?useUnicode=true&characterEncoding=UTF-8"; 
    /**
     * �޸�ǰ����������
     */
    public static final String OLD_CONTEND="oldcontent";
    /**
     * �޸�ǰ���������
     */
    public static final String OLD_TITLE="oldtitle";
    /**
     * �޸�ǰ��������
     */
    public static final String OLD_ID="oldid";
    /**
     * �޸ĵ����������
     */
    public static final String OLD_TYPE="modifytype";
    /**
     * ÿҳ��������
     */
    public static final int TOPIC_PAGE_SIZE =4;
    /**
     * ������Ϣ
     */
    public static final String ERROR_MSG="errormessage";

    /**
     * �޸�ǰ���û���
     */
    public static final String USERNAME="username2"; 
    /**
     * �޸�ǰ���û��Ա�
     */
    public static final String USERSEX="usersex"; 
    /**
     * �޸�ǰ���û��ʼ�
     */
    public static final String USEREMAIL="useremail"; 
    /**
     * �޸�ǰ���û�qq
     */
    public static final String USERQQ="userqq";
    /**
     * �޸�ǰ���û�ǩ��
     */
    public static final String userSig="usersig";
    /**
     * �޸�ǰ���û�id
     */
    public static final String USERID="userid";
    /**
     * ��ǰ��½�û���id
     */
    public static final String USERIDKEY="useridkey";
    /**
     * �鿴�û���Ϣ�е��û�������
     */
    public static final String USERTOPICNUM="usertopicnum";
    /**
     * �û��б�
     */
    public static final String SEARCH_LIST="searchlist";
    /**
     * ��Ϣ�б�
     */
    public static final String MESSAGE_LIST="messagelist";
    /**
     * δ����Ϣ����
     */
    public static final String MESSAGE_Count="messagecount";
    
    public static final String MESSAGE_SHOWTYPE="messageshowtype";
    
    /**
     * ���ֲ���ǰ׺1
     */
    public static final String PRE_PLAYER_URL1="<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" " +
    		"codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0\" " +
    		"width=\"290\" height=\"24\"><param name=\"movie\" " +
    		"value=\"http://localhost:8080/BBS/common/soundplayer.swf?soundFile=" ;
    /**
     * ���ֲ���ǰ׺2
     */
    public static final String PRE_PLAYER_URL2="&bg=0xCDDFF3&leftbg=0x357DCE&lefticon=0xF2F2F2&rightbg=0x357DCE" +
    		"&rightbghover=0x4499EE&righticon=0xF2F2F2&righticonhover=0xFFFFFF" +
    		"&text=0x357DCE&slider=0x357DCE&track=0xFFFFFF&border=0xFFFFFF" +
    		"&loader=0x8EC2F4&autostart=yes&loop=yes\" />" +
    		"<param name=\"quality\" value=\"high\" />" +
    		"<param value=\"transparent\" name=\"wmode\" />" +
    		"<embed src=\"http://localhost:8080/BBS/common/soundplayer.swf?soundFile=";

    /**
     * ���ֲ��ź�׺
     */
    public static final String POST_PLAYER_URL="&bg=0xCDDFF3&leftbg=0x357DCE&lefticon=0xF2F2F2&rightbg=0x357DCE" +
    		"&rightbghover=0x4499EE&righticon=0xF2F2F2&righticonhover=0xFFFFFF&text=0x357DCE&slider=0x357DCE" +
    		"&track=0xFFFFFF&border=0xFFFFFF&loader=0x8EC2F4&autostart=yes" +
    		"&loop=yes\" width=\"290\" height=\"24\" quality=\"high\" " +
    		"pluginspage=\"http://www.macromedia.com/go/getflashplayer\" " +
    		"type=\"application/x-shockwave-flash\"></embed></object>";
}
