package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            // print the course
            System.out.println(tempCourse);

            // print course reviews
            System.out.println(tempCourse.getReviews());

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            System.out.println("done");

        }
        // handle exception
         catch (Exception exc) {
            exc.printStackTrace();
         }
        finally {

            // clean up code
            session.close();
            factory.close();
        }
    }
}
