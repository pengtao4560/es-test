package javase.oop.abs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
/***
 * JDBC模板对象:JdbcTemplate
 * @author tarena
 */
public class JdbcTemplate {
	//耦合一个连接池对象
	private DataSource dataSource;
	public JdbcTemplate() {}
	public JdbcTemplate(DataSource dataSouce) {
		this.dataSource=dataSouce;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public int deleteObject(String sql,int id) {
		//1.获取链接
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
		conn=dataSource.getConnection();
		//2.创建statment,并为sql中的"?"赋值
		stmt=conn.prepareStatement(sql);
		stmt.setInt(1, id);
		//3.发送sql
		int rows=stmt.executeUpdate();
		return rows;
		//4.返回结果
		}catch(Exception e) {
		 e.printStackTrace();
		 return -1;
		}finally {
		//4.释放资源
	    if(stmt!=null)try{stmt.close();}catch(Exception e) {}
	    if(conn!=null)try{conn.close();}catch(Exception e) {}
		}
	}
}
