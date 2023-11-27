package com.tuespot.user.model;

import com.tuespot.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindDepartmentByUser {

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }

    private UserEntity userEntity;
    private DepartmentModel departmentModel;

}
