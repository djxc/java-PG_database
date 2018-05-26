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
    
    //�û��ṩ���ݿ�ģ�1���û��� 2���� 3���ݿ��ѯ��� 4Ҫ���ӵ����ݿ�����
    public connectmysql(String driver,String user,String pass,String DB_url){
    	this.driver=driver;
    	this.USER=user;
    	this.PASS=pass;    	
    	this.DB_URL=DB_url;
    }
    //���ݿ����Ӻ������ɹ�����true
	public boolean connect() {
    	try{
    	   Class.forName(driver);//"com.mysql.jdbc.Driver");
    	    
           // ������
           System.out.println("�������ݿ�...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);
           System.out.println("�������ݿ�ɹ���");
           return true;
    	}catch(Exception e){
    		System.out.println(e);
    		return false;
    	}
	}
	//���ݿ��ѯ����
	public ResultSet query(String sqlstr){
		try{ // ִ�в�ѯ
        System.out.println("���ڲ�ѯ����");       
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlstr);           
		}catch(Exception e){
			System.out.println(e);			
			}
		return rs;
	}
	//���ݿ���ӡ����º���
	public void add(String sqlstr){
		try{ // ִ�в�ѯ
	        System.out.println("�������\\���¡���");
	        conn.setAutoCommit(false);  //���ò��Զ�ִ��
	        stmt = conn.createStatement();
	        stmt.executeUpdate(sqlstr);	  	     
	        System.out.println("��ӳɹ�");
			}catch(Exception e){
				System.out.println(e);
				try {
					conn.rollback();  //����ع�
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}		
	}
	//�������
	public void createTable(String sqlstr){
		try{ // ִ�в�ѯ
	        System.out.println("���ڴ�������");
	        conn.setAutoCommit(false);  //���ò��Զ�ִ��
	        stmt = conn.createStatement();
	        stmt.execute(sqlstr);	  	     
	        System.out.println("�����ɹ�");
			}catch(Exception e){
				System.out.println(e);
				try {
					conn.rollback();  //����ع�
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}		
	}
	//sql���ִ�к���
 	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	//�ر���������
	public void closemysql(){
		try{
		 rs.close();
         stmt.close();
         conn.close();
		}catch(Exception e){System.out.println(e);}
	}
}
