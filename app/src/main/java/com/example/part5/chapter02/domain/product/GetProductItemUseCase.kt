package com.example.part5.chapter02.domain.product

import com.example.part5.chapter02.domain.UseCase
import com.example.part5.chapter02.data.entity.product.ProductEntity
import com.example.part5.chapter02.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke(productId: Long): ProductEntity? {
        return productRepository.getProductItem(productId)
    }

}
