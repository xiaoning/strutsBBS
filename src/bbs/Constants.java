/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : 全局常量定义
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/



package bbs;



/** 
* session使用的常量定义 
* @author keguolin
* @version 1.0.0
*/ 
public final class Constants {
	// Session keys
    /**
     * 当前用户名称
     */
    public static final String USERNAME_KEY = "username"; 
    /**
     * 论坛保存的数组
     */
    public static final String FORUM_LIST_KEY = "forums";  
    /**
     * 主题保存的数组
     */
    public static final String TOPIC_LIST_KEY = "topics";  
    /**
     * 回复保存的数组
     */
    public static final String RESPONSE_LIST_KEY = "responses"; 
    /**
     * 用户保存的数组
     */
    public static final String USER_LIST_KEY = "users";  
    /**
     * 文章类型
     */
    public static final String TALK_TYPE_KEY = "talkType"; 
    /**
     * 当前版块编号
     */
    public static final String CUR_FORUMID_KEY = "CurForumid"; 
    /**
     * 当前版块名称
     */
    public static final String CUR_FORUMNAME_KEY = "CurForumName"; 
    /**
     * 当前主题编号
     */
    public static final String CUR_TOPICID_KEY = "CurTopicid"; 
    /**
     * 当前页面编号
     */
    public static final String CUR_PAGEID_KEY = "CurPageid"; 
    /**
     * 当前版块的页面总数
     */
    public static final String CUR_PAGECOUNT_KEY="Curpagecount";
    /**
     * 管理员
     */
    public static final String MANAGER_CANDIDATE_KEY = "managerCandidates"; 
    /**
     * 编辑的版块编号
     */
    public static final String EDIT_FORUMID_KEY = "editForumid"; 
    /**
     * 编辑的版块名称
     */
    public static final String EDIT_FORUMNAME_KEY = "editForumname"; 
    /**
     * 登陆的用户级别
     */
    public static final String LOGIN_USERGRADE_KEY = "loginUserGrade"; 
    /**
     * 所有版块信息
     */
    public static final String ALLFORUMS_KEY = "AllForums"; 
    /**
     * 搜索用的关键字
     */
    public static final String SEARCH_TOPIC_KEY = "searchTopic"; 
    /**
     * jdbc驱动
     */
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    /**
     * 数据驱动
     */
    public static final String DATABASE_URL = "jdbc:mysql://localhost/BBS?useUnicode=true&characterEncoding=UTF-8"; 
    /**
     * 修改前的主题内容
     */
    public static final String OLD_CONTEND="oldcontent";
    /**
     * 修改前的主题标题
     */
    public static final String OLD_TITLE="oldtitle";
    /**
     * 修改前的主题编号
     */
    public static final String OLD_ID="oldid";
    /**
     * 修改的主题的类型
     */
    public static final String OLD_TYPE="modifytype";
    /**
     * 每页的主题数
     */
    public static final int TOPIC_PAGE_SIZE =4;
    /**
     * 错误消息
     */
    public static final String ERROR_MSG="errormessage";

    /**
     * 修改前的用户名
     */
    public static final String USERNAME="username2"; 
    /**
     * 修改前的用户性别
     */
    public static final String USERSEX="usersex"; 
    /**
     * 修改前的用户邮件
     */
    public static final String USEREMAIL="useremail"; 
    /**
     * 修改前的用户qq
     */
    public static final String USERQQ="userqq";
    /**
     * 修改前的用户签名
     */
    public static final String userSig="usersig";
    /**
     * 修改前的用户id
     */
    public static final String USERID="userid";
    /**
     * 当前登陆用户的id
     */
    public static final String USERIDKEY="useridkey";
    /**
     * 查看用户信息中的用户发帖量
     */
    public static final String USERTOPICNUM="usertopicnum";
    /**
     * 用户列表
     */
    public static final String SEARCH_LIST="searchlist";
    /**
     * 消息列表
     */
    public static final String MESSAGE_LIST="messagelist";
    /**
     * 未读消息数量
     */
    public static final String MESSAGE_Count="messagecount";
    
    public static final String MESSAGE_SHOWTYPE="messageshowtype";
    
    /**
     * 音乐播放前缀1
     */
    public static final String PRE_PLAYER_URL1="<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" " +
    		"codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0\" " +
    		"width=\"290\" height=\"24\"><param name=\"movie\" " +
    		"value=\"http://localhost:8080/BBS/common/soundplayer.swf?soundFile=" ;
    /**
     * 音乐播放前缀2
     */
    public static final String PRE_PLAYER_URL2="&bg=0xCDDFF3&leftbg=0x357DCE&lefticon=0xF2F2F2&rightbg=0x357DCE" +
    		"&rightbghover=0x4499EE&righticon=0xF2F2F2&righticonhover=0xFFFFFF" +
    		"&text=0x357DCE&slider=0x357DCE&track=0xFFFFFF&border=0xFFFFFF" +
    		"&loader=0x8EC2F4&autostart=yes&loop=yes\" />" +
    		"<param name=\"quality\" value=\"high\" />" +
    		"<param value=\"transparent\" name=\"wmode\" />" +
    		"<embed src=\"http://localhost:8080/BBS/common/soundplayer.swf?soundFile=";

    /**
     * 音乐播放后缀
     */
    public static final String POST_PLAYER_URL="&bg=0xCDDFF3&leftbg=0x357DCE&lefticon=0xF2F2F2&rightbg=0x357DCE" +
    		"&rightbghover=0x4499EE&righticon=0xF2F2F2&righticonhover=0xFFFFFF&text=0x357DCE&slider=0x357DCE" +
    		"&track=0xFFFFFF&border=0xFFFFFF&loader=0x8EC2F4&autostart=yes" +
    		"&loop=yes\" width=\"290\" height=\"24\" quality=\"high\" " +
    		"pluginspage=\"http://www.macromedia.com/go/getflashplayer\" " +
    		"type=\"application/x-shockwave-flash\"></embed></object>";
}
