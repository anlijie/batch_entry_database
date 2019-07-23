package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class BulkAddUtil {

	private static String url = "jdbc:mysql://localhost:3306/test01?rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "root";
    private static int pid = 1;
	
	public static void info() {
		pid++;
		Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);        
            String sql = "INSERT INTO point(x,y,z,rounds) VALUES(?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a,b,c;
            for (int i = 1; i <= 100; i++) {
            		a = rand.nextInt(100);
            		b = rand.nextInt(100);
            		c = rand.nextInt(100);
                    pstm.setInt(1, a);
                    pstm.setInt(2, b);
                    pstm.setInt(3,c);
                    pstm.setInt(4,pid);
                    pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime)); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
       
	}
	
}
