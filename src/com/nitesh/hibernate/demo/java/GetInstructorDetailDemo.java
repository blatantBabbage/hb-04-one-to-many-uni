package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Instructor.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            // get instructor detail obj
            int theId = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            // print instructor detail
            System.out.println(instructorDetail);

            // print associated instructor
            System.out.println(instructorDetail.getInstructor());

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            System.out.println("done");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            //handle conn. leak issue
            session.close();
            factory.close();
        }
    }
}
