package com.example.part5.chapter02.domain.product

import com.example.part5.chapter02.domain.UseCase
import com.example.part5.chapter02.data.repository.ProductRepository

internal class DeleteOrderedProductListUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke() {
        return productRepository.deleteAll()
    }

}
