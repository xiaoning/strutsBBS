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

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
/** 
* 数据库类及其基本操作
* @author keguolin
* @version 1.0.0
*/ 
public class DB {

	/**
	 * 数据库连接
	 */
	Connection connect = null;
	/**
	 * 查询结果
	 */
	ResultSet rs = null; 
	/**
     * Description :DB 构造函数
     * 
     * @return DB 构造成功
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
     * Description :关闭数据库
     * @param 无
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
     * Description :ExecSql 进行数据库的删除或更新，插入操作
     * @param sql String Mysql命令语句
     * @return result为0代表失败，不为0成功。
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
     * Description :OpenSql 数据库查询，得到结果列表
     * @param sql String  Mysql命令语句
     * @return ResultSet 结果类表
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
