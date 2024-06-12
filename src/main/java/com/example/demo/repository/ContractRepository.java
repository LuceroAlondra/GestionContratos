package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Company;
import com.example.demo.model.Contract;
import com.example.demo.model.Employee;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
	Contract findByIdEmpCom(int idEmpCom);

	Contract findByCompanyId(Company companyId);

	List<Contract> findByIdEmployee(Employee idEmployee);

	List<Contract> findByIdEmployeeAndStartDateLessThanEqualAndUntilDateGreaterThanEqual(Employee idEmployee,
			Date startDate, Date untilDate);

	List<Contract> findByIdEmployeeAndStartDateLessThanAndUntilDateGreaterThanAndIdEmpComNot(Employee idEmployee,
			Date startDate, Date untilDate, int contractId);

}
