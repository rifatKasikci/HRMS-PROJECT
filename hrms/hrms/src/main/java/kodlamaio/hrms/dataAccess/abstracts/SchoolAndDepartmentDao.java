package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.SchoolAndDepartment;

public interface SchoolAndDepartmentDao extends JpaRepository<SchoolAndDepartment, Integer>{

}
