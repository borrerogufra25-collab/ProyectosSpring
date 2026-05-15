package com.salesianostriana.dam.proyectoej09listadosedicion.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoej09listadosedicion.modelo.Producto;
import com.salesianostriana.dam.proyectoej09listadosedicion.servicios.CategoriaService;
import com.salesianostriana.dam.proyectoej09listadosedicion.servicios.ProductoService;

/**
 * Todo lo relacionado con los controllers está explicado en la clase
 * CategoriaController ya que esta de producto es exactamente igual
 */

@Controller
@RequestMapping("/admin/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "admin/list-producto";
	}

	@GetMapping("/nuevo")
	public String nuevaProducto(Model model) {

		model.addAttribute("producto", new Producto());
		// En el formulario de un nuevo producto,
		// debemos elegir con un select una categoría de la lista de categorías que hay,
		// por lo que debemos pasar al modelo dicha lista de categorías.
		// En este caso le hemos llamado categorias y la sacamos con findAll de
		// CategoriaService

		model.addAttribute("categorias", categoriaService.findAll());
		return "admin/form-producto";
	}

	@PostMapping("/nuevo/submit")
	public String submitNuevoProducto(Producto producto, Model model) {

		productoService.save(producto);
		return "redirect:/admin/producto/";

	}

	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model) {

		Optional<Producto> producto = productoService.findById(id);

		if (producto.isPresent()) {
			model.addAttribute("producto", producto.get());
			model.addAttribute("categorias", categoriaService.findAll());
			return "admin/form-producto";
		} else {
			return "redirect:/admin/producto/";
		}

	}

	@GetMapping("/borrar/{id}")
	public String borrarProducto(@PathVariable("id") Long id, Model model) {

		Optional<Producto> producto = productoService.findById(id);

		if (producto.isPresent()) {
			productoService.delete(producto.get());
		}
		return "redirect:/admin/producto/";
	}

}
