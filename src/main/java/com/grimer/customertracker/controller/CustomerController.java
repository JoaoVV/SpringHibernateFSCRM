package com.grimer.customertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grimer.customertracker.entity.Customer;
import com.grimer.customertracker.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "/")
    public String getHome() {
        return "home.html";
    }
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the dao
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers.html";
	}
	
	
	@GetMapping("/showFormForAdd") 
	public String showFormForAdd(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form.html";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		Customer customer = customerService.getCustomer(id);
		
		model.addAttribute("customer", customer);
		
		return "customer-form.html";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		
		customerService.delete(id);
		
		return "redirect:/list";
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {

		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
				
		theModel.addAttribute("customers", theCustomers);

		return "list-customers.html";		
	}
	
}


