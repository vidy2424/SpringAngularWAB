package org.websparrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.websparrow.dao.ProductDAO;
import org.websparrow.entity.ClientProducts;
import org.websparrow.entity.OurProducts;

@RestController
@RequestMapping("/student")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	@CrossOrigin(origins = "http://localhost:4200")

	// Our Products //

	@PostMapping(value = "/ourProducts")
	public List<OurProducts> addOurproduct(@RequestBody OurProducts ourProducts) {
		// System.out.println(user);

		productDAO.addOurProducts(ourProducts);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		// productDAO.get(ourProducts.getProduct_name());
		// System.out.println(info.getPlanName().toString());
		List<OurProducts> sign2 = productDAO.getAllOurProducts();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(sign2.toString());
		return sign2;
	}

	@RequestMapping(value = "/ourProducts/info")
	public List<OurProducts> getAllOurproduct() {

		List<OurProducts> planinfo = productDAO.getAllOurProducts();
		// System.out.println(planinfo.toString());
		return planinfo;
	}

	@PutMapping("/ourProducts/{id}")
	public String updateOurproducts(@RequestBody OurProducts ourProducts, @PathVariable int id) {

		// List<Info> information = signupDAO.getbyID(id);

		System.out.println("this is id");

		ourProducts.setId(id);

		productDAO.update(ourProducts);

		// info1.update(info1);

		return "";
	}

	@DeleteMapping("/infoDeleteprod/{id}")
	public void deleteOurproduct(@RequestBody OurProducts ourProducts, @PathVariable int id) {
		productDAO.deleteOurProducts(ourProducts);
		System.out.println("this is delete");
	}

	// Client Products //

	@PostMapping(value = "/clientProducts")
	public List<ClientProducts> addClientproduct(@RequestBody ClientProducts clientProducts) {
		// System.out.println(user);

		productDAO.addClientProducts(clientProducts);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		// productDAO.get(ourProducts.getProduct_name());
		// System.out.println(info.getPlanName().toString());
		List<ClientProducts> client = productDAO.getAllCLientproducts();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(client.toString());
		return client;
	}

	@RequestMapping(value = "/clientProducts/products")
	public List<ClientProducts> getAllCLientproduct() {

		List<ClientProducts> clientProductsinfo = productDAO.getAllCLientproducts();
		// System.out.println(planinfo.toString());
		return clientProductsinfo;
	}

	@PutMapping("/clientProducts/{id}")
	public String updateClientproduct(@RequestBody ClientProducts clientProducts, @PathVariable int id) {

		// List<Info> information = signupDAO.getbyID(id);

		System.out.println("this is id");

		clientProducts.setId(id);

		productDAO.updateClientProducts(clientProducts);

		// info1.update(info1);

		return "";
	}

	@DeleteMapping("/clientProducts/{id}")
	public void deleteClientproduct(@RequestBody ClientProducts clientProducts, @PathVariable int id) {
		productDAO.deleteClientProducts(clientProducts);
		System.out.println("this is delete");
	}

}
