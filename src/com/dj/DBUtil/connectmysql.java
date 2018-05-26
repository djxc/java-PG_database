package com.dj.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectmysql {
	private String USER ;
    private String PASS ;
    private String driver;
    Connection conn = null;
    ResultSet rs;
    Statement stmt ;   
    boolean connstate;        
    private String DB_URL;
    
    //用户提供数据库的：1、用户名 2密码 3数据库查询语句 4要链接的数据库名称
    public connectmysql(String driver,String user,String pass,String DB_url){
    	this.driver=driver;
    	this.USER=user;
    	this.PASS=pass;    	
    	this.DB_URL=DB_url;
    }
    //数据库连接函数，成功返回true
	public boolean connect() {
    	try{
    	   Class.forName(driver);//"com.mysql.jdbc.Driver");
    	    
           // 打开链接
           System.out.println("连接数据库...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);
           System.out.println("连接数据库成功！");
           return true;
    	}catch(Exception e){
    		System.out.println(e);
    		return false;
    	}
	}
	//数据库查询函数
	public ResultSet query(String sqlstr){
		try{ // 执行查询
        System.out.println("正在查询……");       
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlstr);           
		}catch(Exception e){
			System.out.println(e);			
			}
		return rs;
	}
	//数据库添加、更新函数
	public void add(String sqlstr){
		try{ // 执行查询
	        System.out.println("正在添加\\更新……");
	        conn.setAutoCommit(false);  //设置不自动执行
	        stmt = conn.createStatement();
	        stmt.executeUpdate(sqlstr);	  	     
	        System.out.println("添加成功");
			}catch(Exception e){
				System.out.println(e);
				try {
					conn.rollback();  //出错回滚
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}		
	}
	//创建表格
	public void createTable(String sqlstr){
		try{ // 执行查询
	        System.out.println("正在创建……");
	        conn.setAutoCommit(false);  //设置不自动执行
	        stmt = conn.createStatement();
	        stmt.execute(sqlstr);	  	     
	        System.out.println("创建成功");
			}catch(Exception e){
				System.out.println(e);
				try {
					conn.rollback();  //出错回滚
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}		
	}
	//sql语句执行函数
 	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	//关闭数据连接
	public void closemysql(){
		try{
		 rs.close();
         stmt.close();
         conn.close();
		}catch(Exception e){System.out.println(e);}
	}
}
