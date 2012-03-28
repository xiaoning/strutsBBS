/**
  * Copy Right Information  : ICanon
  * Project                 : bbs
  * JDK version used        : jdk 1.6.0
  * Comments                : ��.mp3�������ӵ�flash�������ľ�̬��
  * Version                 : 1.00
  * Modification history    : 2011.7.10
  * Sr	Date	 Modified By  Why & What is modified
  * 1.	2011.7.10 keguolin     new
  **/

package bbs;
/** 
* ��.mp3����ת��flash�������ľ�̬��
* @author keguolin
* @version 1.0.0
*/ 
public class StandarlizeContent {
	
	/**
     * Description :�����������в���.mp3�ļ�����.mp3����ת��flash�������ķ���
     * @param oldcontent ��������
     * @return �µ���������
     */
	public static String Standarlize(String oldcontent)
	{
		String content="";
		for(int i=0;i<oldcontent.length()-1;i++)
		{
			
			if(oldcontent.charAt(i)=='<' && (oldcontent.charAt(i+1)=='A'||oldcontent.charAt(i+1)=='a'))
			{
				int j=i;
				int i1=i+1;
				int i2=i+1;
				for(;j<oldcontent.length();j++)
				{
					if(i1!=i+1&&i2!=i+1) break;
					if(oldcontent.charAt(j)=='\"')
					{
						if(i1==i+1) i1=j;
						else {i2=j;break;}
					}
				}
				String url=oldcontent.substring(i1+1, i2);
				String type=url.substring(url.length()-3, url.length());
				type=type.toLowerCase();
				if(type.equals("mp3"))
				{
					String flashurl=Constants.PRE_PLAYER_URL1+url+Constants.PRE_PLAYER_URL2+url+Constants.POST_PLAYER_URL;
					content+=flashurl;
					for(;i<oldcontent.length()-2;i++)
					{
						if(oldcontent.charAt(i)=='<'&&oldcontent.charAt(i+1)=='/'&&(oldcontent.charAt(i+2)=='A'||oldcontent.charAt(i+2)=='a'))
						{
							i=i+3;
							break;
						}
					}
				}
				else
				{
					content+=oldcontent.charAt(i);
				}
			}
			else{
				content+=oldcontent.charAt(i);
			}
		}
		return content;
	}
}
