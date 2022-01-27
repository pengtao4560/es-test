package javase.oop.abs;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
/**
  *   连接池工厂
 * @author tarena
 */
public class DataSourceFactory {

	/**工厂方法:简单工厂模式*/
	public static DataSource newInstance(String brand) {
		if("druid".equals(brand)) {
           return newDruidDataSouce();
		}else if("hikari".equals(brand)) {
           return newHiKariCPDataSource();
		}else if("c3p0".equals(brand)) {
           return newC3P0DataSource();
		}
		return null;
	}
	//HikariCP 号称世界上最快的连接池
	private static DataSource newHiKariCPDataSource() {
		HikariDataSource ds=new HikariDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql:///jtsys?serverTimezone=GMT%2B8");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	//c3p0
	private static DataSource newC3P0DataSource(){
		try {
		ComboPooledDataSource ds=
				new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql:///jtsys?serverTimezone=GMT%2B8");
		ds.setUser("root");
		ds.setPassword("root");
		return ds;
		}catch(Exception e) {
		e.printStackTrace();
		return null;
		}
	}
	//druid
	private static DataSource newDruidDataSouce() {
		DruidDataSource ds=new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql:///jtsys?serverTimezone=GMT%2B8");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
}
