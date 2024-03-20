package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.domain.model.personalization.toMap
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import com.ahmrh.serene.domain.model.user.toMap
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {


    fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {

        auth.signInAnonymously()
            .addOnCompleteListener{ onResult(it.exception) }



    }
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun linkAccount(username: String, email: String, password: String, onResult: (Throwable?) -> Unit) {
        val credential = EmailAuthProvider.getCredential(email, password)

        auth.currentUser!!.linkWithCredential(credential)
            .addOnSuccessListener {
                createUserData(username, email, password, {})
            }
            .addOnCompleteListener { onResult(it.exception) }

    }

    fun createAccount(username: String, email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                createUserData(username, email, password, {})
            }
            .addOnCompleteListener {
                onResult(it.exception)
            }

    }

    private fun createUserData(username : String, email: String, password: String, onResult: (Throwable?) -> Unit){
        val userId = auth.currentUser!!.uid

        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )

        val collectionReference = firestore.collection("users")

        collectionReference
            .document(userId)
            .set(user)
            .addOnSuccessListener { Log.d(PersonalizationRepository.TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(
                PersonalizationRepository.TAG, "Error writing document", e) }

    }

    fun addSelfCareHistory(selfCareId: String, onResult: (Throwable?) -> Unit) {
        val userId = auth.currentUser!!.uid
        val date = Calendar.getInstance().time;

        val selfCareHistory = SelfCareHistory(
            selfCareId,
            date
        )

        val history = selfCareHistory.toMap()

        firestore.collection("users").document(userId)
            .collection("history")
            .add(history)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
            .addOnCompleteListener { onResult(it.exception) }
    }

    companion object{
        const val TAG = "UserRepository"
    }
}