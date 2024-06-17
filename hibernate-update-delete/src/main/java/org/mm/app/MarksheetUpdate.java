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

public class MarksheetUpdate {

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
			
			System.out.println("Are you sure ??");
			
			String choice = sc.next();
			
			if(choice.equalsIgnoreCase("y"))
			{
				System.out.println("Enter New Name = ");
				String name = sc.next();
				
				System.out.println("Enter New Maths = ");
				int maths = sc.nextInt();
				
				System.out.println("Enter New Science = ");
				int science = sc.nextInt();
				
				System.out.println("Ener New English =");
				int english = sc.nextInt();
				
				Transaction t = s.beginTransaction();
				
				m.setMaths(maths);
				m.setEnglish(english);
				m.setScience(science);
				m.setName(name);
				
				s.update(m);
				
				t.commit();
				
				System.out.println("Record Updated ....");
				
			}
			else
			{
				System.out.println("Process is canceled by user ...");
			}
		}

		s.close();
	}

}
