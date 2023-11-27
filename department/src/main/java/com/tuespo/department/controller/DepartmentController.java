package com.tuespo.department.controller;

import com.tuespo.department.entity.DepartmentEntity;
import com.tuespo.department.entity.DepartmentModel;
import com.tuespo.department.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @PostMapping("/add")
    public DepartmentEntity departmentEntity(@RequestBody DepartmentEntity departmentEntity){
        return  this.departmentRepo.save(departmentEntity);
    }

    @GetMapping("/{id}")
    public DepartmentEntity departmentById(@PathVariable("id") Long departmentId){
        return  this.departmentRepo.findById(departmentId).get();
    }


    @GetMapping("/all")
    public List<DepartmentEntity> departmentById(){
        return  this.departmentRepo.findAll();
    }

    @PutMapping("/update/{id}")
    public String updateDepartment(@RequestBody DepartmentModel model,@PathVariable Long id) {
       Optional<DepartmentEntity> e= departmentRepo.findById(id);
       if(e!=null) {

           DepartmentEntity entity = e.get();
           entity.setDepartment(model.getDepartment());
           departmentRepo.save(entity);
           return "ADDED_SUCCESSFULLY";
       }
       return "FAILED_UPDATE";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id){
        Optional<DepartmentEntity> e=departmentRepo.findById(id);
        if (e.isPresent()) {
            DepartmentEntity entity = e.get();
            departmentRepo.delete(entity);
            return "DELETED_DEPARTMENT";
        } else {
            return "DEPARTMENT_NOT_FOUND";
        }
    }
}
