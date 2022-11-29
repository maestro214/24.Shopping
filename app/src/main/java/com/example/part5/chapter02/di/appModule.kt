package com.example.part5.chapter02.di

import com.example.part5.chapter02.data.db.provideDB
import com.example.part5.chapter02.data.db.provideToDoDao
import com.example.part5.chapter02.data.network.*
import com.example.part5.chapter02.data.network.provideProductRetrofit
import com.example.part5.chapter02.data.preference.PreferenceManager
import com.example.part5.chapter02.data.repository.DefaultProductRepository
import com.example.part5.chapter02.data.repository.ProductRepository
import com.example.part5.chapter02.domain.product.DeleteOrderedProductListUseCase
import com.example.part5.chapter02.domain.product.GetOrderedProductListUseCase
import com.example.part5.chapter02.domain.product.GetProductItemUseCase
import com.example.part5.chapter02.domain.product.GetProductListUseCase
import com.example.part5.chapter02.domain.product.OrderProductItemUseCase
import com.example.part5.chapter02.presentation.detail.ProductDetailViewModel
import com.example.part5.chapter02.presentation.list.ProductListViewModel
import com.example.part5.chapter02.presentation.main.MainViewModel
import com.example.part5.chapter02.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // ViewModel
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { (productId: Long) -> ProductDetailViewModel(productId, get(), get()) }

    // UseCase
    factory { GetProductListUseCase(get()) }
    factory { GetOrderedProductListUseCase(get()) }
    factory { GetProductItemUseCase(get()) }
    factory { OrderProductItemUseCase(get()) }
    factory { DeleteOrderedProductListUseCase(get()) }

    // Repository
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

    single { PreferenceManager(androidContext()) }

    // Database
    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

}

