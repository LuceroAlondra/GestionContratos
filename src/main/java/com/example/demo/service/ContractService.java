package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.model.Company;
import com.example.demo.model.Contract;
import com.example.demo.model.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContractService {

	private final CompanyRepository companyRepository;
	private final ContractRepository contractRepository;
	private final EmployeeRepository employeeRepository;

	public ContractService(CompanyRepository companyRepository, ContractRepository contractRepository,
			EmployeeRepository employeeRepository) {
		this.companyRepository = companyRepository;
		this.contractRepository = contractRepository;
		this.employeeRepository = employeeRepository;
	}

	public void importCompanyDataFromCsv(List<String[]> csvData) throws ParseException {
		for (String[] row : csvData) {
			Company company = new Company();
			if (row.length > 0 && row[0] != null && !row[0].isEmpty()) {
				// Si el ID está presente en el CSV, lo establecemos en la entidad Company
				company.setIdCompany(Integer.parseInt(row[0]));
			}
			// Si no se proporciona un ID en el CSV, la base de datos generará uno
			// automáticamente
			company.setNameCompany(row[1]);
			company.setCityCompany(row[2]);
			company.setCountryCompany(row[3]);
			company.setCountryCode(row[4]);
			company.setEmailCompany(row[5]);
			company.setSector(row[6]);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date since = dateFormat.parse(row[7]);
			company.setSince(since);

			saveCompany(company);
		}
	}

	public void saveCompany(Company company) {
		companyRepository.save(company);
	}

	public Company findByIdCompany(int idCompany) {
		return this.companyRepository.findByIdCompany(idCompany);
	}

	public void actualizarCompany(int idCompany, Company companyActualizada) {
		Company companyExistente = companyRepository.findByIdCompany(idCompany);

		if (companyExistente != null) {
			companyExistente.setNameCompany(companyActualizada.getNameCompany());
			companyExistente.setCityCompany(companyActualizada.getCityCompany());
			companyExistente.setCountryCompany(companyActualizada.getCountryCompany());
			companyExistente.setCountryCode(companyActualizada.getCountryCode());
			companyExistente.setEmailCompany(companyActualizada.getEmailCompany());
			companyExistente.setSector(companyActualizada.getSector());
			companyExistente.setSince(companyActualizada.getSince());

			companyRepository.save(companyExistente);
		} else {
			throw new EntityNotFoundException("La compañía con ID " + idCompany + " no existe.");
		}
	}

	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}

	public boolean deleteCompanyById(int idCompany) {
		boolean isBBDD = false;
		Company company = this.companyRepository.findByIdCompany(idCompany);
		if (company != null) {
			companyRepository.delete(company);
			isBBDD = true;
		}

		return isBBDD;
	}

	public void importEmployeeDataFromCsv(List<String[]> csvData) throws ParseException {
		for (String[] row : csvData) {
			Employee employee = new Employee();
			if (row[0] != null && !row[0].isEmpty()) {
				employee.setEmployeeId(Integer.parseInt(row[0]));
			}
			employee.setNameEmployee(row[1]);
			employee.setLastName(row[2]);
			employee.setCityEmployee(row[3]);
			employee.setCountryEmployee(row[4]);
			employee.setCountryCodeEmployee(row[5]);
			employee.setEmailEmployee(row[6]);
			employee.setRole(row[7]);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = dateFormat.parse(row[8]);
			employee.setBirthDate(birthDate);

			saveEmployee(employee);
		}
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public Employee findEmployeeById(int employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}

	public void updateEmployee(int employeeId, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException("El empleado con ID " + employeeId + " no existe."));

		existingEmployee.setNameEmployee(updatedEmployee.getNameEmployee());
		existingEmployee.setLastName(updatedEmployee.getLastName());
		existingEmployee.setCityEmployee(updatedEmployee.getCityEmployee());
		existingEmployee.setCountryEmployee(updatedEmployee.getCountryEmployee());
		existingEmployee.setCountryCodeEmployee(updatedEmployee.getCountryCodeEmployee());
		existingEmployee.setEmailEmployee(updatedEmployee.getEmailEmployee());
		existingEmployee.setRole(updatedEmployee.getRole());
		existingEmployee.setBirthDate(updatedEmployee.getBirthDate());

		employeeRepository.save(existingEmployee);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public boolean deleteEmployeeyById(int employeeId) {
		boolean isBBDD = false;
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee != null) {
			employeeRepository.delete(employee);
			isBBDD = true;
		}

		return isBBDD;
	}

	public void importContractDataFromCsv(List<String[]> csvData) {
		for (String[] row : csvData) {
			try {
				Contract contract = new Contract();
				if (row[0] != null && !row[0].isEmpty()) {
					contract.setIdEmpCom(Integer.parseInt(row[0]));
				}
				// Buscar y asignar el Employee por su ID
				Employee employee = findEmployeeById(Integer.parseInt(row[1]));
				contract.setIdEmployee(employee);

				// Buscar y asignar la Company por su ID
				Company company = findByIdCompany(Integer.parseInt(row[2]));
				contract.setCompanyId(company);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = dateFormat.parse(row[3]);
				Date untilDate = dateFormat.parse(row[4]);

				contract.setStartDate(startDate);
				contract.setUntilDate(untilDate);
				contract.setRole(row[5]);

				saveContract(contract);
			} catch (NumberFormatException | ParseException e) {
				// Si ocurre un error al convertir una cadena en un entero o al parsear fechas,
				// se omitirá la fila
				System.err.println("Error al importar datos de contrato desde el archivo CSV: " + e.getMessage());
			}
		}
	}

	public void saveContract(Contract contract) {
		contractRepository.save(contract);
	}

	public Contract findByIdEmpCom(int idEmpCom) {
		return contractRepository.findByIdEmpCom(idEmpCom);
	}

	public void updateContract(int contractId, Contract updatedContract) {
		Optional<Contract> existingContractOptional = contractRepository.findById(contractId);

		if (existingContractOptional.isPresent()) {
			Contract existingContract = existingContractOptional.get();

			// Validación para asegurar que el empleado no esté asignado a dos empresas al
			// mismo tiempo
			if (isEmployeeUpdateAvailable(contractId, updatedContract)) {
				existingContract.getIdEmployee().setNameEmployee(updatedContract.getIdEmployee().getNameEmployee());
				existingContract.getIdEmployee().setLastName(updatedContract.getIdEmployee().getLastName());
				existingContract.getIdEmployee().setCityEmployee(updatedContract.getIdEmployee().getCityEmployee());
				existingContract.getIdEmployee()
						.setCountryEmployee(updatedContract.getIdEmployee().getCountryEmployee());
				existingContract.getIdEmployee()
						.setCountryCodeEmployee(updatedContract.getIdEmployee().getCountryCodeEmployee());
				existingContract.getIdEmployee().setEmailEmployee(updatedContract.getIdEmployee().getEmailEmployee());
				existingContract.getIdEmployee().setRole(updatedContract.getIdEmployee().getRole());
				existingContract.getIdEmployee().setBirthDate(updatedContract.getIdEmployee().getBirthDate());

				existingContract.setCompanyId(updatedContract.getCompanyId());
				existingContract.setStartDate(updatedContract.getStartDate());
				existingContract.setUntilDate(updatedContract.getUntilDate());
				existingContract.setRole(updatedContract.getRole());

				contractRepository.save(existingContract);
				System.out.println("El contrato con ID " + contractId + " ha sido actualizado correctamente.");
			} else {
				throw new IllegalArgumentException(
						"El empleado ya está asignado a otra empresa en el mismo período de tiempo.");
			}
		} else {
			throw new EntityNotFoundException("El contrato con ID " + contractId + " no existe.");
		}
	}

	public boolean deleteContractById(int idEmpCom) {
		boolean isBBDD = false;
		Contract contract = this.contractRepository.findByIdEmpCom(idEmpCom);
		if (contract != null) {
			contractRepository.delete(contract);
			isBBDD = true;
		}

		return isBBDD;
	}

	private boolean isEmployeeUpdateAvailable(int contractId, Contract updatedContract) {
		// Obtener la lista de contratos activos que se superponen con el contrato
		// actualizado
		List<Contract> activeContracts = contractRepository
				.findByIdEmployeeAndStartDateLessThanAndUntilDateGreaterThanAndIdEmpComNot(
						updatedContract.getIdEmployee(), updatedContract.getStartDate(), updatedContract.getUntilDate(),
						contractId);

		// Ver si hay contratos activos que se superponen
		return activeContracts.isEmpty();
	}

	public void createsContract(Contract nuevoContract) {
		// Verificar si el empleado tiene un contrato en la misma fecha
		List<Contract> existingContracts = contractRepository
				.findByIdEmployeeAndStartDateLessThanEqualAndUntilDateGreaterThanEqual(nuevoContract.getIdEmployee(),
						nuevoContract.getStartDate(), nuevoContract.getUntilDate());

		if (!existingContracts.isEmpty()) {
			throw new IllegalStateException("El empleado ya tiene un contrato en la misma fecha.");
		}

		// Guardar el nuevo contrato si no hay conflictos
		contractRepository.save(nuevoContract);
	}

}
