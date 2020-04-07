package za.co.pact.poc.controller

import org.springframework.web.bind.annotation.*
import za.co.pact.poc.model.Order

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("/api")
class OrderController {

    val orders = listOf(
            Order(1L, "order 1 name", "order 1 description", "2019-01-01"),
            Order(2L, "order 2 name", "order 2 description", "2019-01-01"),
            Order(3L, "order 3 name", "order 3 description", "2019-01-01"),
            Order(4L, "order 4 name", "order 4 description", "2019-01-01")
    )

    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable("id") id: Long): Order? {
        return orders.find { it.id == id }
    }
}