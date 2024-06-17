package org.mm.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.mm.model.Marksheet;

public class MarksheetUpdateDelete {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id :- ");
		int id = sc.nextInt();
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();

		SessionFactory sf = new MetadataSources(registry)
		.buildMetadata()
		.buildSessionFactory();
		
		Session s = sf.openSession();
		
		Marksheet m = s.get(Marksheet.class, id);
		
		if(m==null)
		{
			System.out.println("Record Not found ...");
		}
		else
		{
			Transaction t = s.beginTransaction();
			
			s.delete(m);
			
			
			t.commit();
			
			System.out.println("Record Deleted ...");
		}
		
		s.close();

	}

}
