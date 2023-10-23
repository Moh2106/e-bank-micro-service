package ma.enset.orderservice.web;

import lombok.AllArgsConstructor;
import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.models.Customer;
import ma.enset.orderservice.models.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.services.CustomerRestClientService;
import ma.enset.orderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    OrderRepository orderRepository;
    CustomerRestClientService customerRestClientService;
    InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }



    @GetMapping("/fullOrder/{id}")
    public Order getOrderById(@PathVariable Long id){
        Order order = orderRepository.findById(id).get();
//        Récupérer le customer
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
//        Mettre à jour la valeur du customer
        order.setCustomer(customer);

        order.getProductItem().forEach(productItem -> {
            Product product = inventoryRestClientService.productById(productItem.getProductId());
            productItem.setProduct(product);
        });
        return order;
    }
}
