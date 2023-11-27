package com.tuespot.user.controller;

import com.tuespot.user.entity.UserEntity;
import com.tuespot.user.model.Department;
import com.tuespot.user.model.DepartmentModel;
import com.tuespot.user.model.FindDepartmentByUser;
import com.tuespot.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/addUser")
    public UserEntity insertUserData(@RequestBody UserEntity userEntity){
        return this.userRepo.save(userEntity);
    }

    @GetMapping("/getUserAndDepartmentById/{id}")
    public FindDepartmentByUser findDepartmentByUserId(@PathVariable("id") long id){
        FindDepartmentByUser findDepartmentByUser=new FindDepartmentByUser();
        UserEntity userEntity=this.userRepo.findById(id).get();

        DepartmentModel departmentModel=restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+id,DepartmentModel.class);
        findDepartmentByUser.setUserEntity(userEntity);
        findDepartmentByUser.setDepartmentModel(departmentModel);
        return findDepartmentByUser;
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentModel>> getAllDepartments() {

        DepartmentModel[] dp = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/all", DepartmentModel[].class);

        List<DepartmentModel> departmentList = Arrays.asList(dp);
            if(departmentList.isEmpty()||departmentList==null){
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<List<DepartmentModel>>(departmentList,HttpStatus.OK);
    }


    @GetMapping("/getDepartmentById/{deptid}")
    public  ResponseEntity<DepartmentModel> getDepartmentBy(@PathVariable int deptid){
        DepartmentModel model= restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+deptid,DepartmentModel.class);
        if(model==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<DepartmentModel>(model, HttpStatus.OK);
    }

    @PostMapping("/addDeparment")
    public ResponseEntity<DepartmentModel> insert(@RequestBody DepartmentModel dept){

        DepartmentModel model=restTemplate.postForObject("http://DEPARTMENT-SERVICE/department/add",dept,DepartmentModel.class);
                if(model==null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return  new ResponseEntity<DepartmentModel>(model,HttpStatus.OK);
    }

    @PutMapping("/deptUpdateById/{id}")
    public String updateDepartment(@RequestBody Department model, @PathVariable Long id) {
        restTemplate.put("http://DEPARTMENT-SERVICE/department/update/" + id, model);
        return "UPDATED_DEPARTMENT";
    }

    @DeleteMapping("/deleteDepartmentById/{id}")
    public String DeleteDeparment(@PathVariable Long id){
        restTemplate.delete("http://DEPARTMENT-SERVICE/department/delete/" + id);
        return "DELETE_DEPARTMENT_SUCCESSFULLY";
    }
}
