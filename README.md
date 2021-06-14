# DoubleSymetryMusic 

## Intro

This project is written in Kotlin and following MVVM Clean Architecture principles, with StateFlow/LiveData/Coroutines/DataBinding.


## Build and run

Simply clone the project, import into Android Studio and run.

## Modules

It contains 4 modules in total: 



* **app**: Contains the views fragment/activity and the adapters, as well as custom views, view helper classes, extensions and data binding adapters.
* **presentation**: Contains the ViewModel declaration 
* **domain**: Contains the Repository interface and UseCases 
* **data**: Contains the Repository implementation and the retrofit api calls

## Testing

Each module contains a set of unit tests



## Libraries

* Coroutines
* Koin
* Jetpack (Navigation, Paging, Data Binding, Lifecycle, LiveData, ViewModel)
* Kotlinx Serialization
* Jakewharton retrofit2-kotlinx-serialization-converter
* Retrofit
* Picasso
* JUnit
* Mockk


## Code

[**SourceResultHolder**](https://github.com/oth-libs/DoubleSymetryMusic/blob/master/domain/src/main/java/com/doublesymetrymusic/domain/datasource/DataSourceResultHolder.kt)

A generic class that holds a value with its loading status.


[**DataSourceFlow**](https://github.com/oth-libs/DoubleSymetryMusic/blob/master/data/src/main/java/com/doublesymetrymusic/data/datasource/DataSourceFlow.kt)

This file contains 2 important functions:

**```resultFlow()```** will execute the given ```networkCall``` and return a ```Flow```. It is mainly used for APIs that are not paginable.


**```resultPagingFlow()```** will execute the given ```networkCall```, extract a List using ```extractListItemsand``` and return a ```Flow<PagingData>```, which means this call should be reserved for paging APIs.


[**RemoteDataSource**](https://github.com/oth-libs/DoubleSymetryMusic/blob/master/data/src/main/java/com/doublesymetrymusic/data/datasource/RemoteDataSource.kt)

**```getResult```** will execute the ```call``` function, generally coming from a retrofit service, and map it using the ```transform``` function. This method automatically checks on network state and handles errors, and returns the right ```SourceResultHolder``` wrapper.

All of these files can be put together with Retrofit services (and Room eventually) and have a seamless Repository implementation for use cases.


## Demo

<https://www.dropbox.com/s/eie7iwgvwazgik5/ds.webm> 

