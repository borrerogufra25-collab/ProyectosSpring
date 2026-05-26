package com.salesianostriana.dam.proyectoej10carritomemoria.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.proyectoej10carritomemoria.modelo.Producto;
import com.salesianostriana.dam.proyectoej10carritomemoria.servicios.ProductService;
import com.salesianostriana.dam.proyectoej10carritomemoria.servicios.ShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ProductService productoService;

	public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
		this.shoppingCartService = shoppingCartService;
		this.productoService = productService;
	}

	@GetMapping("/carrito")
	public String showCarrito(Model model) {

		if (model.addAttribute("products", shoppingCartService.getProductsInCart()) == null)

			return "redirect:/";

		return "cart";
	}

	/*
	 * Versión 1 para agregar un producto al carrito: Lanzamos una excepción si no
	 * encontramos el producto y luego se pintará una página de aviso
	 */
	@GetMapping("/productoACarrito/{id}")
	public String productoACarrito(@PathVariable("id") Long id, Model model) {

		// Si existe, extrae el Producto. Si no, lanza la excepción.
		Producto producto = productoService.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + id));

		shoppingCartService.addProducto(producto);

		return "redirect:/carrito";
	}

	/*
	 * Versión 2 para agregar producto al carrito: No se lanza excepción, solo se
	 * agrega si el objeto está dentro del Optional, si no está no hace nada, la 1
	 * es mejor
	 */
	@GetMapping("/productoACarritoV2/{id}")
	public String productoACarritoV2(@PathVariable("id") Long id, Model model) {

		// Solo lo agrega si el Optional tiene un producto dentro
		productoService.findById(id)
				.ifPresent(producto -> shoppingCartService.addProducto(producto));

		return "redirect:/carrito";
	}

	/*
	 * Igualmente para el borrado de un producto del carrito Versión 1 con excepción
	 */
	@GetMapping("/borrarProducto/{id}")
	public String removeProductFromCartV1(@PathVariable("id") Long id) {

		// Extrae el producto si existe; si no, lanza la excepción
		Producto producto = productoService.findById(id)
				.orElseThrow(
						() -> new NoSuchElementException("No se puede borrar: Producto no encontrado con ID " + id));

		shoppingCartService.removeProducto(producto);
		return "redirect:/carrito";
	}

	/*
	 * Versión 2: solo lo borra si está dentro del Optional, si no está no hace nada
	 */
	@GetMapping("/borrarProductoV2/{id}")
	public String removeProductFromCartV2(@PathVariable("id") Long id) {

		// Si el producto existe dentro del Optional, lo elimina del carrito
		productoService.findById(id)
				.ifPresent(producto -> shoppingCartService.removeProducto(producto));

		return "redirect:/carrito";
	}

	/*
	 * Mejor versión de borrado, usar en el servicio un borrar producto del carritoi
	 * por ID
	 */

	// Código ideal si usamos removeProductoPorId(Long id) que es más eficaz
	@GetMapping("/borrarProductoV3/{id}")
	public String removeProductFromCartV3(@PathVariable("id") Long id) {
		shoppingCartService.removeProductoPorId(id); // Más rápido y eficiente
		return "redirect:/carrito";
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {

		Map<Producto, Integer> carrito = shoppingCartService.getProductsInCart();
		double total = 0.0;
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {
				total += p.getPvp() * carrito.get(p);
			}
			return total;
		}
		return 0.0;
	}

	@GetMapping("/carrito/tramitar")
	public String tramitarPedido(Model model) {
		// 1. Obtenemos los productos actuales del carrito desde el servicio de sesión
		Map<Producto, Integer> carrito = shoppingCartService.getProductsInCart();

		// 2. Si el carrito está vacío, lo redirigimos a la página de inicio
		if (carrito == null || carrito.isEmpty()) {
			return "redirect:/";
		}

		// 3. Pasamos el mapa al modelo bajo el atributo "products" que espera el HTML
		model.addAttribute("products", carrito);

		// NOTA: El atributo "total_carrito" ya lo inyectas automáticamente en todo este
		// controlador mediante tu método anotado con @ModelAttribute("total_carrito")

		return "ticket"; // Nombre del archivo HTML (ticket.html)
	}

	@GetMapping("/carrito/vaciar-y-nueva-compra")
	public String vaciarYNuevaCompra() {
		// 1. Obtenemos el mapa del carrito actual
		java.util.Map<Producto, Integer> carrito = shoppingCartService.getProductsInCart();

		// 2. Si el carrito existe, usamos .clear() para vaciar todos los productos en
		// memoria
		if (carrito != null) {
			// Nota: Si getProductsInCart() devuelve un mapa inmutable,
			// deberás añadir un método específico en ShoppingCartService como
			// "vaciarCarrito()"
			// que ejecute un products.clear(); sobre el atributo original de la clase.
			shoppingCartService.vaciarCarrito();
		}

		// 3. Redirigimos al catálogo principal limpio
		return "redirect:/";
	}
}
