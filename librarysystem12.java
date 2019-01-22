import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
class log extends JFrame implements ActionListener
{

	JLabel l1,l2;
	JTextField t1;
	JPasswordField t2;
	JButton b;
	log()
	{
		setLayout(new GridLayout(3,2));
		l1=new JLabel("Username:");
		l2=new JLabel("Password:");
		b=new JButton("Next");
		t1=new JTextField(30);
		t2=new JPasswordField(30);
		
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(b);
		t1.addActionListener(this);
		t2.addActionListener(this);
		b.addActionListener(this);
		
		t1.requestFocus();

	}
	public void actionPerformed(ActionEvent ae)
	{
				//Class.forName("com.sql.jdbc.Driver");
		//Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ranjan","root","");
		//Statement st=con.createStatement();

		if(ae.getSource()==b)
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
			Statement st=con.createStatement();
	String str="select * from log where username='" + t1.getText() + "' and password='" + t2.getText() +   "'";
ResultSet rs = st.executeQuery(str);			

if(rs.next())
{
	 
	this.setVisible(false);
	
	det d= new det(t1.getText());
	d.resize(400,400);
	d.setVisible(true);
}
		else

		{
		JOptionPane.showMessageDialog(null,"Wrong userid or password");
		t2.setText("");
		t2.requestFocus();
		}			

			con.close();}
catch(Exception ee) {
	ee.printStackTrace();
	System.out.println(ee.getMessage());
}			
			
		}
	}
}




class details extends JFrame implements ActionListener{
JLabel name[],roll[];
JTable t;
JLabel lab;

details(String course, String dept, String sem, String date, String period, String staffid)
{
	
	
	JPanel p1= new JPanel();
		  lab=new JLabel("ROLL NO AND THEIR PERCENTAGE");
		  add(lab, BorderLayout.NORTH);

	  add(p1);	
	//setLayout(new FlowLayout());
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
			Statement st;
			String str="select count(studid) from sheet where status='p' group by studid";
		st=con.createStatement();
		ResultSet rs=st.executeQuery(str);
		ResultSetMetaData rms=rs.getMetaData();
		//int n=rms.getColumnCount();
		int n=0;
	
		while(rs.next())
			n++;
		p1.setLayout(new FlowLayout());
		//p1.setLayout(new GridLayout(n,2));
		roll=new JLabel[n];
		name=new JLabel[n];
		int present[]=new int[n];
		int i=0;
		rs=st.executeQuery(str);
		while(rs.next())
		{
			
			present[i]=rs.getInt(1);
		
		System.out.println(present[i]);
		i++;
		}
		System.out.println("HO");
		
String qry="select count(studid) from sheet group by(studid)";
rs=st.executeQuery(qry);
rms=rs.getMetaData();
int m=0;
while(rs.next())
	m++;
int cl=rms.getColumnCount();
int tot[]=new int[m];
rs=st.executeQuery(qry);

String clabel[]={rms.getColumnLabel(1),"Attendence percentage"};

//clabel[0]=;
//clabel[1]=;
i=0;
while(rs.next())
{
tot[i]=rs.getInt(1);
System.out.println(tot[i]);

i++;
}


String per[]=new String[m];
for(i=0;i<m;i++){
	
per[i]=(present[i]*100)/tot[i]+"%";
System.out.println(per[i]);
					
}
str="select distinct studid from sheet";
		rs=st.executeQuery(str);
i=0;
int r[]=new int[n];
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			r[i]=rs.getInt(1);
		
			/*roll[i]=new JLabel(rs.getInt(1)+"");
			r[i]=rs.getInt(1);
		p1.add(roll[i]);
			name[i]=new JLabel(per[i]+"%");

			p1.add(name[i]);
			*/

		i++;
		}
		String data[][] = new String[n][2];
		
		/*for(i=0;i<cl;i++)
		{
			for(int j=0;j<n;j++)
				data[i][j]=
		}
		}
		*/
		for(i=0;i<n;i++)
			data[i][0]=(r[i]+"");
		for(i=0;i<n;i++)
			data[i][1]=per[i];

		t=new JTable(data,clabel);
JScrollPane sp=new JScrollPane(t);
		
add(sp,BorderLayout.CENTER);
		
		}catch(Exception e)
{ System.out.println(e);
}

	
}

	public void actionPerformed(ActionEvent ae)
	{
//Statement st1;
/*


String str="select studid form sheet";
rs=st.executeQuery(str);
rms=rs.getMetaData();
String title[ ]= new String[rmd.getColumnCount()+1];
for (int i=0;i<(rmd.getColumnCount()+1);i++)
{
title[i]=rmd.getColumnLabel(i+1);
if(i==rmd.getColumnCount())
	title[i]="Attendence Percentage";
}


int i=0,k=0;
while(rs.next())
{
	k++;
}
float data[][] = new float[k][rmd.getColumnCount()+1];
rs=st.executeQuery(str);
while(rs.next())
{
for (int j=0;j<rmd.getColumnCount()+1;j++)
data[i][j]=rs.getString(j+1);
if(i==rmd.getColunCount())
	data[i][j]=per[j];
i++;

}
  JTable table = new JTable(data,title);
JScrollPane sp= new JScrollPane(table);
  panel.add(sp,BorderLayout.CENTER);
this.add(panel);
  }
  */


	
	}
}


class det extends JFrame implements ActionListener
{
	JComboBox cb1,cb2,cb3;
	JPanel p1,p2;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JButton b,b1;
	String u="";
	det(String uname)
	{
		u=uname;
		setLayout(new GridLayout(3,1));
		l1=new JLabel(" Staffid:");
		l2=new JLabel("Staff Name:");
		l3=new JLabel("Enter Course:");
		l4=new JLabel("Enter Subject:");
		l5=new JLabel("Enter Date:");
		l6=new JLabel("Enter Department:");
		l7=new JLabel("Enter period:");
		l8=new JLabel("Enter Semester:");
		p1=new JPanel();
		setLayout(new GridLayout(10,2));
		
		String mn[]={"january","february","mar","april","may","june","july","august","oct","nov","dec"};
JPanel p=new JPanel();
//add(p);
p1=new JPanel();
//add(p1);
//setLayout(new FlowLayout());

cb1=new JComboBox();
cb2=new JComboBox();
cb3=new JComboBox();

for(int i=1;i<=31;i++)
cb1.addItem(i+"");

for(int i=0;i<mn.length;i++)
cb2.addItem(mn[i]);

for(int i=2017;i<=2030;i++)
cb3.addItem(i+"");
cb1.addActionListener(this);
cb2.addActionListener(this);
cb3.addActionListener(this);

//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	
		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		t4=new JTextField(30);
		t5=new JTextField(30);
		t6=new JTextField(30);
		t7=new JTextField(30);
		t8=new JTextField(30);
		b1=new JButton("Status");
		b=new JButton("Attendence sheet");
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
	add(t3);
	add(l4);
		add(t4);
	//setBounds(100,100,200,200);
		//p.setLayout(new GridLayout(1,5));
		add(l5);
		add(t5);
		//add(cb1);
		//add(cb2);
		//add(cb3);
		add(l6);
		add(t6);
		add(l7);
		add(t7);
		add(l8);
		add(t8);
		//p2=new JPanel();
		//p2.setLayout(new GridLayout(1,2));
		
		//add(p2);
		t1.addActionListener(this);
	t5.addActionListener(this);	
		add(b);
		add(b1);
		b.addActionListener(this);
		b1.addActionListener(this);
		
	t2.requestFocus();
	t1.setText(uname);
	}
	
	public void actionPerformed(ActionEvent ae)
	{

t5.setText(cb1.getSelectedItem()+" / "+cb2.getSelectedItem()+" / "+cb3.getSelectedItem());	
		/*
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ranjan","root","");
		Statement st=con.createStatement();
		String str="insert into stdn values '"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"',
		ResultSet rs=st.executeQuery(str);
		while(rs.next())
		{

			t1.setText(rs.getString(1));
			t2.setText(rs.getString(2));
			t3.setText(rs.getString(3));
			t4.setText(rs.getString(4));
			t5.setText(rs.getString(5));
			t6.setText(rs.getString(6));
			t7.setText(rs.getInt(7)+"");
			t8.setText(rs.getInt(8)+"");
		
		}
		con.close();
		}
		catch(Exception ee){}*/
		if(ae.getSource()==b)
		{
			attend at=new attend(t3.getText(),t6.getText(),t8.getText(),t5.getText(),t7.getText(),t1.getText());
			at.resize(500,500);
			at.setVisible(true);
			this.setVisible(false);
		}
		if(ae.getSource()==b1)
		{
			details d=new details(t3.getText(),t6.getText(),t8.getText(),t5.getText(),t7.getText(),t1.getText());
			d.resize(500,500);
			d.setVisible(true);
		}
		
	}

}

class attend extends JFrame implements ActionListener,ItemListener
{
	String state[];
int n,m;

JLabel lab, studid[], studname[];
Checkbox status[];	
JButton b1;
String course, dept,sem,dt, period,staffid;

attend(String course, String dept, String sem, String date, String period, String staffid)
{ 
this.course=course;
this.dept= dept;
this.sem=sem;
this.dt=date;
this.period=period;
this.staffid = staffid;
	
	JPanel p1= new JPanel();
	lab= new JLabel("date"+date + " Period : " + period + " Staff id : " + staffid + " Semester : " + sem + " course : " + course + " (" + dept + ")" );
	  add(lab, BorderLayout.NORTH);
date=date;
	  add(p1);	  
	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
	Statement st=con.createStatement();
	String qry="select * from next where  dept='"+ dept +"' and sem=" +sem  ;
	
	ResultSet rs=st.executeQuery(qry);
	n=0;
	while(rs.next())
		n++;
	m=n;
	p1.setLayout(new GridLayout(n+1,3));
	studid= new JLabel[n];
								
	studname=new JLabel[n];
	status =new Checkbox[n];
	rs=st.executeQuery(qry);
	
	int i=0;
		while(rs.next())
		{
		
		studid[i]=new JLabel(rs.getInt(1)+"");
		studname[i]=new JLabel(rs.getString(2));
		status[i] =new Checkbox("Present",null,true);
		p1.add(studid[i]);
		p1.add(studname[i]);
		p1.add(status[i]);
		i++;	
	
	}
	
	}catch(Exception ee){}
	b1=new JButton("Store");
	
	p1.add(b1);
	b1.addActionListener(this);
}
public void itemStateChanged(ItemEvent ie)
{
/*	state=new String[n];
	for(int i=1;i<=n;i++)
	{
	if(status[i].getState())
	{
		state[i]='p'+"";
	}
		else
			state[i]='a'+"";
	}*/
}
	public void actionPerformed(ActionEvent ae)
	{
	
			try
			{
				
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
	Statement st=con.createStatement();
	for(int i=0;i<m;i++)
	{
	 
	String qry="insert into sheet values(" + studid[i].getText() + ",'" + dt + "','" + (status[i].getState()?"p" : "a") + "'," + period + "," + staffid + ")";
	System.out.println(qry);
	st.executeUpdate(qry);
	}
con.close();				
JOptionPane.showMessageDialog(null,"Record inserted");
			}
		
catch(Exception ee){
	ee.printStackTrace();
	System.out.println(ee.getMessage());
}		
	
		
	
}
}


public class librarysystem12 extends JFrame implements ActionListener
{

	JLabel r;
	librarysystem12()
	{
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	public void actionPerformed(ActionEvent ae)
	{
	
	}
	public static void main(String arg[])
	{
		
		librarysystem12 lbs =new librarysystem12();
		lbs.setVisible(true);
		lbs.resize(300,150);
		
		
		log lg=new log();
		lg.setVisible(true);
		lg.resize(300,150);
		
	}
}