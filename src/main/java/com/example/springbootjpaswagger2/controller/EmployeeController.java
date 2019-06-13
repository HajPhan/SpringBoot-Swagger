package com.example.springbootjpaswagger2.controller;

import com.example.springbootjpaswagger2.model.Employee;
import com.example.springbootjpaswagger2.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Api: Đánh dấu một class như là một tài nguyên của Swagger
 * @ApiModel: Cung cấp thêm thông tin về Swagger models
 * @ApiModelProperty: Thêm và thao tác dữ liệu của một thuộc tính models
 * @ApiOperation: Mô tả một hoạt động hoặc thường là một phương thức HTTP theo một đường dẫn cụ thể
 * @ApiParam: Thêm dữ liệu meta-data bổ sung cho các tham số hoạt động
 * @ApiResponse: Mô tả một phản ứng có thể có của một hoạt động
 * @ApiResponses: Một trình bao bọc (wrapper) để cho phép một danh sách nhiều đối tượng ApiResponse.
 *
 */

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System", description = "Operations pertaining to employee int Employee Management System")
//@ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Successfully retrieved list"),
//        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "View a list of avilable employees",response = List.class) // Mô tả một hoạt động hoặc thường là một phương thức HTTP theo một đường dẫn cụ thể
    //@ApiResponses(): Một trình bao bọc cho phép chứa nhiều đối tượng ApiResponse()
    @ApiResponses(value = {
            //@ApiResponse(): mô tả một phản ứng có thể có của một hoạt động
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployees();
    }

    @ApiOperation(value = "Get an employee by id")
    @GetMapping("/employees/{id}")
    //@ApiPram: bổ sung meta-data cho các param
    public Employee getEmployee(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable int id) {

        return this.employeeService.getEmployeesById(id);
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@ApiParam (value = "Employee object store in database table", required = true) @RequestBody Employee employee) {
        try {
            this.employeeService.saveEmployee(employee);
            return new ResponseEntity<String>("Created", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update an employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable int id,
                                            @ApiParam(value = "Update employee object", required = true) @RequestBody Employee employee) {
        try {
            employee.setId(id);
            this.employeeService.saveEmployee(employee);
            return new ResponseEntity<String>("Updated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable int id) {
        try {
            this.employeeService.deleteEmployeeById(id);
            return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
