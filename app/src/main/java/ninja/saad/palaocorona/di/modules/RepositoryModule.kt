package ninja.saad.palaocorona.di.modules

import dagger.Binds
import dagger.Module
import ninja.saad.palaocorona.data.authentication.AuthenticationRepository
import ninja.saad.palaocorona.data.authentication.AuthenticationRepositoryImpl
import ninja.saad.palaocorona.data.dashboard.DashboardRepository
import ninja.saad.palaocorona.data.dashboard.DashboardRepositoryImpl
import ninja.saad.palaocorona.data.faq.FaqRepository
import ninja.saad.palaocorona.data.faq.FaqRepositoryImpl
import ninja.saad.palaocorona.data.testyourself.TestYourselfRepository
import ninja.saad.palaocorona.data.testyourself.TestYourselfRepositoryImpl

@Module
abstract class RepositoryModule {
    
    @Binds
    abstract fun provideFaqRepository(repo: FaqRepositoryImpl): FaqRepository
    
    @Binds
    abstract fun provideAuthenticationRepository(repo: AuthenticationRepositoryImpl): AuthenticationRepository
    
    @Binds
    abstract fun provideTestYourselfRepository(repo: TestYourselfRepositoryImpl): TestYourselfRepository
    
    @Binds
    abstract fun provideDashboardRepository(repo: DashboardRepositoryImpl): DashboardRepository
}