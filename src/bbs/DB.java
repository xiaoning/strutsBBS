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

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
/** 
* ���ݿ��༰���������
* @author keguolin
* @version 1.0.0
*/ 
public class DB {

	/**
	 * ���ݿ�����
	 */
	Connection connect = null;
	/**
	 * ��ѯ���
	 */
	ResultSet rs = null; 
	/**
     * Description :DB ���캯��
     * 
     * @return DB ����ɹ�
     */
	public DB() {
		
		try {
			Class.forName(Constants.JDBC_DRIVER); 
			connect = DriverManager.getConnection(Constants.DATABASE_URL, "root", "5201413" );

		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * Description :�ر����ݿ�
     * @param ��
     * @return void 
     */
	public void close(){
		if(connect!=null){
			try{
				connect.close();
				connect = null;	
			}catch(SQLException ex) { 
				System.err.println(ex.getMessage());
			}
		}	
	}
	/**
     * Description :ExecSql �������ݿ��ɾ������£��������
     * @param sql String Mysql�������
     * @return resultΪ0����ʧ�ܣ���Ϊ0�ɹ���
     */
	public int ExecSql(String sql) {
		int result = 0;
		try {
			Statement stmt = connect.createStatement();
			result = stmt.executeUpdate(sql);
		} 
		catch(SQLException ex) { 
			System.err.println(ex.getMessage());
			
		}
		return result;
	}
	/**
     * Description :OpenSql ���ݿ��ѯ���õ�����б�
     * @param sql String  Mysql�������
     * @return ResultSet ������
     */
	public ResultSet OpenSql(String sql) {
		try {
			Statement stmt=connect.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} 
		catch(SQLException ex) { 
			ex.printStackTrace();
		}
		return rs;
	}
}
