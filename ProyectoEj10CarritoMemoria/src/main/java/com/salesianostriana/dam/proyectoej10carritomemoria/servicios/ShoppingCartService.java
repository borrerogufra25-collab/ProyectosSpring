package com.salesianostriana.dam.proyectoej10carritomemoria.servicios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.proyectoej10carritomemoria.modelo.Producto;
import com.salesianostriana.dam.proyectoej10carritomemoria.repos.ProductoRepository;
import com.salesianostriana.dam.proyectoej10carritomemoria.servicios.base.BaseServiceImpl;

/**
 * Carrito implementado en memoria.
 * 
 * El carrito se implementa con un Map en un Bean de sesión ya que no lo estamos
 * guardando en la base de datos sino en memoria, por lo que permitimos que se
 * quede guardado todo hasta que no se cierra la sesión actúal.
 * 
 * De manera general:
 * 
 * Un Bean es solo una "receta" para crear instancias de una clase de acuerdo
 * con dicha definición por tanto, dicha receta se puede usar para múltiples
 * instancias a lo largo del ciclo de vida de la aplicación.
 * 
 * Spring permite configurar en la definición de los Beans el scope (o ámbbito
 * de vida de los objetos creados a partir de dicha definición).
 * 
 * En este caso, el contenedor de Spring creará una nueva instancia del objeto
 * definido por el Bean, en nuestro caso un objeto del ShoppingCartService para
 * cada una de las sesiones HTTP y entregará esa misma instancia cada vez que
 * reciba una petición dentro de la misma sesión, teniendo disponible la
 * información.
 * 
 * Por otro lado, idealmente esta clase debería implementar a su interfaz
 * correspondiente y aquí solo se implementarían los métodos de dicha interfaz
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService extends BaseServiceImpl<Producto, Long, ProductoRepository> {

	/*
	 * La clave es un producto y el valor es la cantidad del mismo, pero sería mejor
	 * Map <Long, Integer> donde la clave es el id del producto en lugar del
	 * producto entero y el valor es la cantidad del mismo, siendo mucho más rápido
	 * este método para todo
	 */
	private Map<Producto, Integer> products = new HashMap<>();

	/**
	 * Si el producto ya está en el map (en el carrito), solo se incrementará en uno
	 * la cantidad, una unidad más Si el producto no está en el mapa, significa que
	 * no se ha comprado nada de él en este momento, por lo que se añade con
	 * cantidad 1
	 * 
	 * @param producto
	 */

	public void addProducto(Producto p) {
		if (products.containsKey(p)) {
			products.replace(p, products.get(p) + 1);
		} else {
			products.put(p, 1);
		}
	}

	/**
	 * Método que elimina un producto del carrito. Si en el carrito la cantidad de
	 * dicho producto es más de uno baja la cantidad en una unidad y si es uno
	 * elimina el producto entero
	 * 
	 * @param producto
	 */

	public void removeProducto(Producto p) {
		if (products.containsKey(p)) {
			if (products.get(p) > 1)
				products.replace(p, products.get(p) - 1);
			else if (products.get(p) == 1) {
				products.remove(p);
			}
		}
	}

	/*
	 * Podemos añadir un método que borre por id en lugar de por producto, más
	 * eficiente
	 */
	public void removeProductoPorId(Long id) {
		if (this.products == null || id == null) {
			return;
		}

		// Buscamos la llave (Producto) cuyo ID coincida con el buscado
		this.products.keySet()
				.stream()
				.filter(producto -> producto.getId()
						.equals(id))
				.findFirst()
				// Si lo encuentra, borra ese Producto del mapa
				.ifPresent(producto -> this.products.remove(producto));
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista Con esto
	 *         estamos creando un envoltorio del map original, nos permite ver los
	 *         productos en el carrito (hacer get o recorrer, usar stream...) pero
	 *         si intentamos tocarlos o modificarlos no nos deja, si se intenta
	 *         saltará la excepción UnsupportedOperationException
	 */

	public Map<Producto, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(products);
	}

	/* Método para calcular el total del carrito */

	public Double calcularTotal() {

		// Esto se hace para trabajar con una copia no modificable del carrito
		Map<Producto, Integer> carrito = getProductsInCart();
		double total = 0.0;
		// Si el carrito no es nulo (hay carrito)
		if (carrito != null) {
			// Recorremos todos los productos del carrito sumando el precio final de
			// cada producto y multiplicando por la cantidad de cada producto
			// (carrito.get(p)
			for (Producto p : carrito.keySet()) {
				total += p.getPrecioFinal() * carrito.get(p);
			}
			return total;
		}

		return 0.0;
	}

	/* Versión con Streams y lambdas de calcularTotal */

	public Double calcularTotalV2() {
		if (products == null) {
			return 0.0;
		}

		return products.entrySet()
				.stream()
				.mapToDouble(entry -> entry.getKey()
						.getPrecioFinal() * entry.getValue())
				.sum();
	}

	/* Otra versión más con algo más de código de las nuevas versiones de java */
	public Double calcularTotalV3() {
		return Optional.ofNullable(products)
				.stream()
				.flatMap(map -> map.entrySet()
						.stream())
				.mapToDouble(entry -> entry.getKey()
						.getPrecioFinal() * entry.getValue())
				.sum();
	}

	// Para vaciar el carrito entero
	public void vaciarCarrito() {
		if (this.products != null) {
			this.products.clear();
		}
	}

	// Se pueden agregar métodos que calculen descuentos, etc.

}
