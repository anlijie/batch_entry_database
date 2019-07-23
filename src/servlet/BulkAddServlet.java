package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BulkAddUtil;

/**
 * Servlet implementation class BulkAddServlet
 */
@WebServlet("/BulkAddServlet")
public class BulkAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public void destroy() {
    	
		super.destroy(); 
	}
  
    
	public void init() throws ServletException {
	    this.timerTask();
	}
	
	public void timerTask() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			 private int count = 0;
			@Override
			public void run() {
				count++;
				BulkAddUtil.info();
				if(count == 5) {
					System.out.println("录入数据结束！");
					timer.cancel();
				}
			}
		},2000,2000);
	}
    
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BulkAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
