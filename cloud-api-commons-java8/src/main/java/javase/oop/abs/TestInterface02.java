package javase.oop.abs;
import javax.sql.DataSource;
public class TestInterface02 {
	public static void main(String[] args) {
		//创建数据源对象(连接池)
		DataSource dataSource=
		DataSourceFactory.newInstance("hikari");
		//创建JdbcTemplate对象并为其注入DataSource
		JdbcTemplate jdbcTemplate=new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		String sql="delete from sys_logs where id=?";
		int rows=jdbcTemplate.deleteObject(sql, 9);
		System.out.println(rows);
	}
}
