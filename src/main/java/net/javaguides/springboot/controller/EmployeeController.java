package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Company;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.CompanyService;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

// Musi byt anotacia Controller, aby sa vedel spustit
@Controller
public class EmployeeController {

    // premenna EmployeeService je interface, nie trieda
    // skusal som aj EmployeeServiceImpl a slo to
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    // display list of employees
    // GetMapping odkazuje na hlavnu webovu stranku, teda /
    @GetMapping("/")
    public String viewEmployeesPage() {
        return "index";
    }

    @GetMapping("/showAll")
    public String showAll(Model model) {
        // listEmployees je vlastne to, co sa zobrazi show_all.html v resources/templates
        // v prikaze <tr th:each="employee:${listEmployees}">
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        // return "index" sa odkazuje na subor show_all.html
        return "show_all";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "new_employee";
    }

    @GetMapping("/showNewCompanyForm")
    public String showNewCompanyForm(Model model) {
        // create model attribute to bind form data
        Company company = new Company();
        model.addAttribute("company", company);
        return "new_company";
    }

    @PostMapping("/updateEmployeeForm")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/showAll";
    }


    @PostMapping("/saveEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee,
                                 @RequestParam("imageFile") MultipartFile imageFile,
                                 RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            try {
                String uploadDir = System.getProperty("user.dir") + "/uploads"; // pevná cesta mimo Tomcat tmp
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String originalFilename = imageFile.getOriginalFilename();
                String filename = System.currentTimeMillis() + "_" + originalFilename;
                File destination = new File(uploadDir + "/" + filename);

                imageFile.transferTo(destination);
                employee.setImageFilename(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Ulož zamestnanca
            employeeService.saveEmployee(employee);

        }
        return "redirect:/showAll";
    }




    @PostMapping("/saveCompany")
    // koresponduje s <form action="#" th:action="@{/saveEmployee}" th:object="${employee}" v subore new_employee.html
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/showAll";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long userid, Model model) {
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(userid);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/showAll";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}




