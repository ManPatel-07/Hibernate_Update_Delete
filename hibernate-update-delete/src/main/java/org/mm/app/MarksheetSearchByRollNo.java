package org.mm.app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.mm.model.Marksheet;

public class MarksheetSearchByRollNo {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Roll No :- ");
		int rollno = sc.nextInt();
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();

		SessionFactory sf = new MetadataSources(registry)
		.buildMetadata()
		.buildSessionFactory();
		
		Session s = sf.openSession();
		
//		List li = s.createQuery("from Marksheet m where m.rollno="+rollno+"")
//					.list();
		
		List li = s.createQuery("from Marksheet m where m.rollno=:rno")
					.setParameter("rno", rollno)
					.list();

		if(li.size()==0)
		{
			System.out.println("Record Not Found ...");
		}
		else
		{
			Marksheet m =(Marksheet) li.get(0);
			
			System.out.println(m.getName()+","+m.getRollno());
		}
		
		s.close();

	}

}
