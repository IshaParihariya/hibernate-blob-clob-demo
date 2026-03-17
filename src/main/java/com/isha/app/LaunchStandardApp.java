package com.isha.app;

import com.isha.model.StudentInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.*;

public class LaunchStandardApp
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        FileInputStream fis=null;
        File file=null;
        FileReader freader=null;
        byte[] image=null;
        char[] bio=null;
        boolean flag=false;


        // FileInputStream for byte input
        try
        {
            // for byte[] image
            fis = new FileInputStream("C:\\Users\\ishh1\\OneDrive\\Pictures\\Screenshots\\oggy.png");
            image=new byte[fis.available()];// size will be the same as file
            fis.read(image); // Reads file data → stores inside image[]
            // for char[] bio
            file=new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\FirstStaticWebAppTomcat\\hibernatelob.txt");
            freader=new FileReader(file);
            bio=new char[(int)file.length()]; // length for arr should be int so type casting from double to int
            freader.read(bio);
        }
        catch(FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }

        // StudentInfo object
        StudentInfo si=new StudentInfo();
        si.setSname("Isha");
        si.setScity("Surat");
        si.setsImage(image);
        si.setsBio(bio);

        // try and catch block for transaction
        try
        {
            transaction=session.beginTransaction();
            session.persist(si);  // save
            flag=true;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //finally block
        finally
        {
            if(flag==true)
            {
                transaction.commit(); // commit
            }
            else
            {
                transaction.rollback(); // rollback
            }

            try
            {
                fis.close();
                freader.close();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
            session.close();
            sessionFactory.close();
        }
    }
}
