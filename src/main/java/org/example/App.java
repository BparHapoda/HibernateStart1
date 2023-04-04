package org.example;

import org.example.models.Course;
import org.example.models.Lesson;
import org.example.models.Student;
import org.example.models.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration=new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.getTransaction();
        transaction.begin();

        University university= University.builder().
        name("ITMO").build();
        Course course1=Course.builder().
        title("Java").build();
        Course course2=Course.builder().
                title("English").build();
        Student student1=Student.builder().name("Дима").surname("Боронин").build();
        Student student2=Student.builder().name("Павел").surname("Степанов").build();
        Lesson lesson1=Lesson.builder().name("Stream Api").course(course1).build();
        Lesson lesson2=Lesson.builder().name("Foreign language").course(course2).build();
        course1.setStudents(List.of(student1,student2));
        course2.setStudents(List.of(student2));
        university.setCourseList(List.of(course1,course2));
        student1.setCourses(List.of(course1,course2));
        student2.setCourses(List.of(course1,course2));

        session.save(university);
        session.save(course1);
        session.save(course2);
        session.save(student1);
        session.save(student2);
        session.save(lesson1);
        session.save(lesson2);

transaction.commit();
session.close();
    }
}
