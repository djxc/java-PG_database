package com.dj.DBUtil;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.omg.CORBA.PRIVATE_MEMBER;


public class mysqltest {
    public static void main(String[] args) {
    	mysqltest tMysqltest=new mysqltest();
    	tMysqltest.managerDB();
    	
    	System.out.println("djdj");    	        	
    }
    
    public void managerDB() {
    	String username;
    	String driver;
    	String DB_url;    
    	String password;
    	String sqlstr="SELECT id, firstName,age FROM tb_employee";//定义查询语句
    	String sqlstradd="insert into tb_employee values(7,'姜','晓翠')";
    	String update="UPDATE mydb.tb_employee SET age=age+3 where id=6";	
    	String update1="UPDATE mydb.tb_employee SET age=age+3 wher id=1";	
    	String createTable="create table Student(id int,name varchar(20),age int)";
    	
    	Properties properties=new Properties();
    	try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream( //加载properties文件
					"DBConfig.properties"));
			//从配置文件中读取所需要的属性
			username=properties.getProperty("username");
			driver=properties.getProperty("driver");
			DB_url=properties.getProperty("DB_url");
			password=properties.getProperty("password");
			
			connectmysql conne=new connectmysql(driver,username,password,DB_url);	    	
	    	if (conne.connect()){	    	
	    	//conne.add(sqlstradd);
//	    	conne.add(update);
//	    	conne.add(update1);
//	    	conne.query(sqlstr);
	    	ResultSet resultSet=conne.query(sqlstr);
	    	List stu=list(resultSet);
	    	System.out.println(stu);
	    	//conne.createTable(createTable);
	    	//conne.commit();
	    	conne.closemysql();
	        System.out.println("Goodbye!");
	        }else {
				System.out.println("请输入正确的连接信息！");
			}
		} catch (IOException e) {		
			e.printStackTrace();
		}		
	}   
    public List list(ResultSet resultSet){
    	List list=new ArrayList();
    	try {
			while(resultSet.next()){
			 	 int id  = resultSet.getInt("id");
			      String name = resultSet.getString("firstName");
			      int age=resultSet.getInt("age");
			      student student=new student();
			      student.setId(id);
			      student.setName(name);
			      student.setAge(age);
			      list.add(student);
			      }
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return list;
    }
}

class student{
	private int id;
	private String  name;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
