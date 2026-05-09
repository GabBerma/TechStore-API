package cl.techstore.api.service;

import cl.techstore.api.model.Producto;
import cl.techstore.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // LISTAR SOLO ACTIVOS
    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }

    // LISTAR TODOS, INCLUYENDO ELIMINADOS
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // CREAR
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    // MODIFICAR
    public Producto modificar(Long id, Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        producto.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(producto);
    }

    // ELIMINAR (BORRADO LOGICO)
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setActivo(false);
        productoRepository.save(producto);
    }
}