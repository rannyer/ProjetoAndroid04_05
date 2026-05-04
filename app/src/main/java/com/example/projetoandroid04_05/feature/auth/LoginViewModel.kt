package com.example.projetoandroid04_05.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetoandroid04_05.core.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email:String = "",
    val password:String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState : StateFlow<LoginUiState> = _uiState

    fun onEmailChange(value: String){
        _uiState.value = _uiState.value.copy(email = value)
    }
    fun onPasswordChange(value: String){
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun login(onSuccess: () -> Unit){
        val state = _uiState.value

        if(state.email.isBlank() || state.password.isBlank()){
            _uiState.value = state.copy(
                errorMessage = "Preencha email e senha."
            )
            return
        }
        viewModelScope.launch {
            try {
                _uiState.value = state.copy(
                    isLoading = true,
                    errorMessage = null
                )
                authRepository.login(
                    email = state.email,
                    password = state.password
                )
                onSuccess()
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Erro ao fazer login. Verifique suas credenciais."
                )
            }
        }
    }

















}