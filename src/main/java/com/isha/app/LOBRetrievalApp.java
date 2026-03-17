package com.isha.app;

import com.isha.model.StudentInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class LOBRetrievalApp
{
    public static void main(String[] args)
    {

        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        FileOutputStream fos=null;
        FileWriter fw=null;
        boolean flag=false;

        StudentInfo si=session.get(StudentInfo.class,1); // fetching data of id==1
        try
        {
            fos=new FileOutputStream("output/retrieved_oggy.png");
            fw=new FileWriter("output/retrieved_bio.txt");
            fos.write(si.getsImage());
            fw.write(si.getsBio());
        }
        catch (IOException e)
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
            try
            {
                fos.close();
                fw.close();
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
