package com.example.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.model.Contract;
import com.example.demo.model.Employee;
import com.example.demo.service.ContractService;
import com.example.demo.service.CsvReaderService;
import com.opencsv.exceptions.CsvException;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/gestion")
public class ContractController {
	private final ContractService contractService;
	private final CsvReaderService csvReaderService;

	public ContractController(ContractService contractService, CsvReaderService csvReaderService) {
		this.contractService = contractService;
		this.csvReaderService = csvReaderService;
	}

	// Importar CSV de compañía
	@PostMapping("company/import-csv")
	public String importCsv(@RequestParam("filePath") String filePath) throws IOException, CsvException {
		try {
			List<String[]> csvData = csvReaderService.readCsv(filePath);
			contractService.importCompanyDataFromCsv(csvData);
			return "Datos importados exitosamente desde el archivo CSV.";
		} catch (IOException | ParseException e) {
			return "Error al importar datos desde el archivo CSV: " + e.getMessage();
		}
	}

	// Buscar Compañía por ID
	@GetMapping("company/idCompany/{idCompany}")
	public ResponseEntity<?> findByIdCompany(@PathVariable("idCompany") int idCompany) {
		try {
			Company company = contractService.findByIdCompany(idCompany);
			if (company != null) {
				return ResponseEntity.ok(company);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró la empresa con el ID proporcionado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar la empresa: " + e.getMessage());
		}
	}

	// Editar valores de comañía
	@PutMapping("company/actualizar-company/{idCompany}")
	public void actualizarCompany(@PathVariable("idCompany") int idCompany, @RequestBody Company companyActualizada) {
		this.contractService.actualizarCompany(idCompany, companyActualizada);
	}

	// Crear compañía
	@PostMapping("company/crear-company")
	public ResponseEntity<String> crearEmpresa(@RequestBody Company nuevaEmpresa) {
		try {
			contractService.saveCompany(nuevaEmpresa);
			return ResponseEntity.status(HttpStatus.CREATED).body("Empresa creada exitosamente.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear la empresa: " + e.getMessage());
		}
	}

	// Borrar company por ID
	@DeleteMapping("company/deleteCompany/{idCompany}")
	public ResponseEntity<String> deleteCompany(@PathVariable int idCompany) {
		boolean isDeleted = contractService.deleteCompanyById(idCompany);

		if (isDeleted) {
			return ResponseEntity.ok("Empresa eliminada exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró la empresa con el ID proporcionado");
		}
	}

	// Importar Csv de Employee
	@PostMapping("/employee/import-csv")
	public String importEmployeeCsv(@RequestParam("filePath") String filePath) throws IOException, CsvException {
		try {
			List<String[]> csvData = csvReaderService.readCsv(filePath);
			contractService.importEmployeeDataFromCsv(csvData);
			return "Datos de empleados importados exitosamente desde el archivo CSV.";
		} catch (IOException | ParseException e) {
			return "Error al importar datos de empleados desde el archivo CSV: " + e.getMessage();
		}
	}

	// Buscar empleados por ID
	@GetMapping("/employee/idEmployee/{employeeId}")
	public ResponseEntity<?> findEmployeeById(@PathVariable int employeeId) {
		Employee employee = contractService.findEmployeeById(employeeId);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró el empleado con el ID proporcionado");
		}
	}

	// Editar valores de empleado por ID
	@PutMapping("/employee/actualizar-employee/{employeeId}")
	public void actualizarEmployee(@PathVariable("employeeId") int employeeId, @RequestBody Employee updatedEmployee) {
		contractService.updateEmployee(employeeId, updatedEmployee);
	}

	// Crear nuevo empleado
	@PostMapping("employee/crear-employee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee nuevoEmployee) {
		try {
			contractService.saveEmployee(nuevoEmployee);
			return ResponseEntity.status(HttpStatus.CREATED).body("Empleado creado exitosamente.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear el empleado: " + e.getMessage());
		}
	}

	// Borrar empleado por ID
	@DeleteMapping("employee/deleteEmployee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
		boolean isDeleted = contractService.deleteEmployeeyById(employeeId);

		if (isDeleted) {
			return ResponseEntity.ok("Empleado eliminado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró el empleado con el ID proporcionado");
		}
	}

	// Importar CSV de contratos
	@PostMapping("/contract/import-csv")
	public ResponseEntity<String> importContractCsv(@RequestParam("filePath") String filePath) {
		try {
			List<String[]> csvData = csvReaderService.readCsv(filePath);
			contractService.importContractDataFromCsv(csvData);
			return new ResponseEntity<>("Datos de contratos importados exitosamente desde el archivo CSV.",
					HttpStatus.OK);
		} catch (IOException | CsvException e) {
			return new ResponseEntity<>("Error al importar datos de contratos desde el archivo CSV: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Buscar contratos por ID
	@GetMapping("/contract/idContract/{idEmpCom}")
	public ResponseEntity<?> findByIdEmpCom(@PathVariable int idEmpCom) {
		Contract contract = contractService.findByIdEmpCom(idEmpCom);
		if (contract != null) {
			return ResponseEntity.ok(contract);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró el empleado con el ID proporcionado");
		}
	}

	// Actualizar contratos por ID
	@PutMapping("/contract/actualizar-contract/{idEmpCom}")
	public void actualizarContract(@PathVariable("idEmpCom") int idEmpCom, @RequestBody Contract updatedContract) {
		contractService.updateContract(idEmpCom, updatedContract);
	}

	// Crear nuevo contrato
	@PostMapping("contract/crear-contract")
	public ResponseEntity<String> createContract(@RequestBody Contract nuevoContract) {
		try {
			contractService.createsContract(nuevoContract);
			return ResponseEntity.status(HttpStatus.CREATED).body("Contract creado exitosamente.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear el csontracto: " + e.getMessage());
		}
	}

	// Borrar contrato
	@DeleteMapping("contract/deleteContract/{idEmpCom}")
	public ResponseEntity<String> deleteContract(@PathVariable int idEmpCom) {
		boolean isDeleted = contractService.deleteContractById(idEmpCom);

		if (isDeleted) {
			return ResponseEntity.ok("Contrato eliminado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró el contrato con el ID proporcionado");
		}
	}
}