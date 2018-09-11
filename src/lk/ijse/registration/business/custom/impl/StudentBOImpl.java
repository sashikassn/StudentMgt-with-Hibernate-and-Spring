/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom.impl;

import lk.ijse.registration.business.custom.StudentBO;
import lk.ijse.registration.dao.custom.StudentDAO;
import lk.ijse.registration.dto.StudentDTO;
import lk.ijse.registration.entity.Student;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SSNLIVE
 */

@Component
@Transactional
public class StudentBOImpl implements StudentBO{

    @Autowired
    private StudentDAO studentDAO;


    public StudentBOImpl(){


    }
    

    
    public boolean addStudent(StudentDTO student)throws Exception{
        
       try {




         Student student1 = new Student(student.getStID(),student.getName(),student.getAddress());
         studentDAO.save(student1);

         return true;

       }catch (HibernateException exp){
           return false;

       }
    }
    
    public ArrayList<StudentDTO> getAllStudents ()throws Exception{
        
   try{


       List<Student> allstudents = studentDAO.getAll();



       ArrayList<StudentDTO> dtos = new ArrayList<>();

       for (Student student: allstudents){
           StudentDTO studentDTOs = new StudentDTO(student.getStID(),student.getName(),student.getAddress());
           dtos.add(studentDTOs);

       }
       return dtos;
   }catch (HibernateException exp){
       return null;

   }
        
    } 
    
    public boolean deleteStudent(String id)throws Exception{
      try{


          studentDAO.delete(id);

          return true;

      }catch (HibernateException exp){
          return false;

      }
    }
    
    public boolean updateStudent(StudentDTO student)throws Exception{

        try{

            Student student1 = studentDAO.find(student.getStID());
            student1.setName(student.getName());
            student1.setAddress(student.getAddress());
            studentDAO.update(student1);

            return true;


        }catch (HibernateException exp){
            return false;
        }


    }
    
    public StudentDTO findStudent(String id)throws Exception{

try{

    Student student = studentDAO.find(id);
    StudentDTO studentDTO = new StudentDTO(student.getStID(),student.getName(),student.getAddress());
    return studentDTO;
}catch (HibernateException exp){
    return null;

}



    }
    
}
