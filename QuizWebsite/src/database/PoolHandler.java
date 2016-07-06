package database;

import java.util.TimerTask;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class PoolHandler extends TimerTask {

	private BasicDataSource dataSource;
	private int minIdle;
	private int maxTotal;
	
	public PoolHandler(BasicDataSource dataSource2, int nLoggedIn) {
		this.dataSource = dataSource2;
		this.minIdle = nLoggedIn / 10 + 1;
		this.maxTotal = nLoggedIn + 1;
	}
	
	@Override
	public void run() {
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxTotal(maxTotal);
		
		System.out.println(
				"Pool size adjusted. # of idle: " + dataSource.getNumIdle() + 
				", max total: " + dataSource.getMaxTotal());
	}

}
