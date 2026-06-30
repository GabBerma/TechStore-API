package cl.techstore.api.service;

import cl.techstore.api.aws.SqsNotificationService;
import cl.techstore.api.exception.ProductoNoEncontradoException;
import cl.techstore.api.model.Producto;
import cl.techstore.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SqsNotificationService sqsNotificationService;

    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe un producto activo con ID: " + id));
    }

    public Producto obtenerPorIdTodos(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe un producto con ID: " + id));
    }

    public Producto crear(Producto producto) {
        Producto productoGuardado = productoRepository.save(producto);

        sqsNotificationService.enviarMensajeProductoCreado(
                productoGuardado.getId(),
                productoGuardado.getNombre(),
                productoGuardado.getCategoria()
        );

        return productoGuardado;
    }

    public Producto modificar(Long id, Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe un producto con ID: " + id));

        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        producto.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe un producto con ID: " + id));

        producto.setActivo(false);
        productoRepository.save(producto);
    }
}