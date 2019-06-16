package com.example.di

import com.example.plot.viewModel.*
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Module will initialize all viewmodels
 * @author Sumit Lakra
 * @date 04/29/19
 */
internal val viewModelModule = module {
    //initialize all viewmodule here
    viewModel { PlotViewModel() }
    viewModel { AddRoomViewModel() }
    viewModel { RoomDetailViewModel() }
    viewModel { CalculateRentViewModel() }
    viewModel { PayRentViewModel() }
}