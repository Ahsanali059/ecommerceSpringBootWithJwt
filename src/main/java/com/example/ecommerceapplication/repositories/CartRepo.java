package com.example.ecommerceapplication.repositories;
import java.util.List;

import com.example.ecommerceapplication.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

	@Query("SELECT c FROM Cart c WHERE c.user.email = ?1 AND c.cartId = ?2")
	Cart findCartByEmailAndCartId(String email, Long cartId);

	@Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product p WHERE p.productId = ?1")
	List<Cart> findCartsByProductId(Long productId);
}
