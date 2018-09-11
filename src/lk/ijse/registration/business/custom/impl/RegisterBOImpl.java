/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom.impl;

import lk.ijse.registration.business.custom.RegisterBO;
import lk.ijse.registration.dao.custom.RegisterDAO;
import lk.ijse.registration.dto.RegisterDTO;
import lk.ijse.registration.entity.Register;
import lk.ijse.registration.entity.Register_PK;
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
public class RegisterBOImpl implements RegisterBO {

    @Autowired
    private RegisterDAO registerDAO;


    public RegisterBOImpl(){



    }


    public boolean registerStudent(RegisterDTO register) throws Exception {
       try {


           Register register1 = new Register(register.getStID(),register.getcID(),register.getRegDate(),register.getFee());
           registerDAO.save(register1);


           return true;
       }catch (HibernateException exp){
           return false;

       }
    }

    @Override
    public boolean updateregistration(RegisterDTO register) throws Exception {
        try {

            Register_PK register_pk = new Register_PK(register.getStID(),register.getcID());
            Register register1 = registerDAO.find(register_pk);
            register1.setCourse(register1.getCourse());
            register1.setRegDate(register1.getRegDate());
            register1.setFee(register1.getFee());

            return true;


        }catch (HibernateException exp){
            return false;

        }


    }


    public boolean deleteregistration(String stID, String cID) throws Exception {
      try{

          Register_PK register_pk = new Register_PK(stID,cID);
          registerDAO.delete(register_pk);

          return true;
      }catch (HibernateException exp){
          return false;

      }
    }


    public RegisterDTO findById(String stID, String cID) throws Exception {

        try{

            Register_PK register_pk = new Register_PK(stID,cID);
            Register register = registerDAO.find(register_pk);
            RegisterDTO registerDTO = new RegisterDTO(register.getRegister_PK().getStID(),register.getRegister_PK().getcID(),register.getRegDate(),register.getFee());
            return registerDTO;


        }catch (HibernateException exp){
            return null;
        }


    }

    @Override
    public ArrayList<RegisterDTO> getAll() throws Exception {
        try{

            List<Register> allregs = registerDAO.getAll();



           ArrayList<RegisterDTO> dtos = new ArrayList<>();


           for (Register register: allregs){
               RegisterDTO registerDTO = new RegisterDTO(register.getRegister_PK().getStID(),register.getRegister_PK().getcID(),register.getRegDate(),register.getFee());
               dtos.add(registerDTO);
           }
                return dtos;
        }catch (HibernateException exp){
            return null;
        }
        

    }
    
     
    
}
