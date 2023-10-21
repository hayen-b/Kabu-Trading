package kabu.hayen.module

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kabu.hayen.data.dataSource.LoginDataSource
import kabu.hayen.data.dataSource.local.*
import kabu.hayen.data.repository.*
import kabu.hayen.presentation.viewModel.*

val appModule = module {
    single { KabuDatabase.getDatabase(androidApplication()) }

    single<AccountDao> { KabuDatabase.getDatabase(androidApplication()).accountDao() }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    factory<DetailsRepository> { DetailsRepositoryImpl() }

    single<LoginDataSource> { LoginDb() }

    factory<LoginRepository> { LoginRepositoryImpl(get()) }

    factory<NewsRepository> { NewsRepositoryImpl() }

    single<PortfolioDao> { KabuDatabase.getDatabase(androidApplication()).portfolioDao() }

    factory<PortfolioRepository> { PortfolioRepositoryImpl(get()) }

    single<StocksDao> { KabuDatabase.getDatabase(androidApplication()).stocksDao() }

    factory<StocksRepository> { StocksRepositoryImpl(get()) }

    viewModel { BuySellViewModelImpl() }

    viewModel { LoginViewModelImpl() }

    viewModel { DetailsViewModelImpl() }

    viewModel { DiscoverViewModelImpl() }

    viewModel { PortfolioViewModelImpl() }
}