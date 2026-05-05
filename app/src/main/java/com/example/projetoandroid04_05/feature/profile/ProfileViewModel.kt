package com.example.projetoandroid04_05.feature.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetoandroid04_05.core.data.AuthRepository
import com.example.projetoandroid04_05.core.data.UserRepository
import com.example.projetoandroid04_05.core.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = true,
    val user: UserProfile? = null,
    val errorMessage:String? = null
)
class ProfileViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init{
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            try{
                Log.e("PROFILE_VM", "INICIOU O LOAD")

                _uiState.value = ProfileUiState(isLoading = true)

                val user =  userRepository.getProfile()
                Log.e("PROFILE_VM", "REQUISICAO SUCCESS")

                _uiState.value = ProfileUiState(
                    isLoading = false,
                    user = user
                )
                Log.e("PROFILE_VM", user.toString())

            }catch (e: Exception){
                Log.e("PROFILE_VM", "ERRO AO CARREGAR DADOS", e)
                _uiState.value = ProfileUiState(
                    isLoading = false,
                    errorMessage =  "Nao foi possivel carregar o perfil"
                )
            }
        }
    }
    fun logout(onSuccess:() -> Unit){
        viewModelScope.launch {
            authRepository.logout()
            onSuccess()
        }
    }




}