package xyz.palaocorona.data.testyourself

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Single
import xyz.palaocorona.base.data.network.onResponse
import xyz.palaocorona.data.testyourself.model.LocaleData
import xyz.palaocorona.data.testyourself.model.Question
import java.util.*
import javax.inject.Inject

class TestYourselfDataSource @Inject constructor(private val apiService: TestYourselfRestService) {
    
    companion object {
        const val TESTS = "tests"
    }
    
    fun getQuestionnaire(): Single<MutableList<Question>> {
        return apiService.getquestionnaire()
            .onResponse()
            .map {
                it.views
            }
    }
    
    fun setResult(answer: MutableList<MutableList<LocaleData>>): Completable {
        return Completable.create { emitter ->
            FirebaseDatabase.getInstance()
                .getReference(TESTS)
                .child(FirebaseAuth.getInstance().uid!!)
                .child(Calendar.getInstance().time.toString())
                .setValue(answer)
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }
}