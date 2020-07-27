package com.bancoazteca.countries.di

import com.bancoazteca.countries.module.CountriesService
import com.bancoazteca.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service:CountriesService)
    fun inject(viewModel: ListViewModel)
}