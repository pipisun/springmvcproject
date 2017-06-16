package edu.mum.coffee.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/user")
public class OrderController {
	
	@Autowired
	public PersonService personService;
	@Autowired
	public OrderService orderService;

	@GetMapping({ "/createOrder" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createOrder");
		Order order = new Order();
		order.setOrderDate(new Date());
		modelAndView.addObject(order);
		return modelAndView;
	}

	@RequestMapping("/saveOrder")
	public String saveProductPage(@ModelAttribute(value = "order") Order order) {
		System.out.println(order);
		orderService.save(order);
		return "redirect:/user";
	}
	@RequestMapping("/profile")
	public String profilePage(Map<String,Object> map) {
		List<Person> findByEmail = personService.findByEmail("user@qq.com");
		map.put("person", findByEmail.get(0));
		return "profile";
	}
//
//	@RequestMapping("/listPersons")
//	public String listProductPage(Map<String, Object> map) {
//		List<Person> findAllPersons = orderService.findAllPersons();
//		map.put("persons", findAllPersons);
//		return "listPersons";
//	}
////
////	@RequestMapping("/deleteProducts/{id}")
////	public String deleteProductPage(@PathVariable("id") int id) {
////		Product product = personService.getProduct(id);
////		personService.delete(product);
////		return "redirect:/listProducts";
////	}
////
//	@RequestMapping("/updatePerson/{id}")
//	public String updateProductsPage(@PathVariable("id") long id, Map<String, Object> map) {
//		System.out.println(id);
//		Person person = orderService.findById(id);
//		map.put("person", person);
//		return "updatePerson";
//	}
	@ModelAttribute
	public void getProduct(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if(id != null){
			map.put("person",personService.findById(Long.valueOf(id)));
			System.out.println("111111");
		}
		System.out.println("2222222222");
	}
	@RequestMapping("/updateProfile")
	public String updatePersonAndSavePage(Person person) {
		System.out.println("333333");
		personService.savePerson(person);
		System.out.println(person);
		return "redirect:/user";
	}
}
