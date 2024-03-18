package com.ahmrh.serene.data.repository

import android.util.Log
import com.ahmrh.serene.common.state.ResourceState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val auth: FirebaseAuth
) {


    fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {

        auth.signInAnonymously()
            .addOnCompleteListener{ onResult(it.exception) }

    }
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        val credential = EmailAuthProvider.getCredential(email, password)

        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { onResult(it.exception) }
    }

//    fun createAnonymousAccount(): Flow<ResourceState<AuthResult>> = callbackFlow {
//
//        auth.signInAnonymously()
//            .addOnSuccessListener {
//                trySend(ResourceState.Success(it))
//            }
//            .addOnFailureListener { exception ->
//                trySend(
//                    ResourceState.Failed(exception)
//                )
//            }
//
//        awaitClose{ close() }
//    }

//    fun createAccount(email: String, password: String) : Flow<ResourceState<AuthResult>> = callbackFlow {
//
//    }
//
//     fun authenticate(email: String, password: String): Flow<ResourceState<AuthResult>> = callbackFlow {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//                trySend(ResourceState.Success(it))
//            }
//            .addOnFailureListener { exception ->
//                trySend(
//                    ResourceState.Failed(exception)
//                )
//            }
//
//         awaitClose {
//             close()
//         }
//    }
//
//
//    fun linkAccount(email: String, password: String): Flow<ResourceState<AuthResult>> = callbackFlow {
//
//        val credential = EmailAuthProvider.getCredential(email, password)
//        auth.currentUser!!.linkWithCredential(credential)
//            .addOnSuccessListener {
//                trySend(ResourceState.Success(it))
//            }
//            .addOnFailureListener { exception ->
//                trySend(
//                    ResourceState.Failed(exception)
//                )
//            }
//
//        awaitClose {
//            close()
//        }
//    }

//    fun authenticateWithGoogle() {
//
//        val credential = GoogleAuthProvider.getCredential()
//    }

}