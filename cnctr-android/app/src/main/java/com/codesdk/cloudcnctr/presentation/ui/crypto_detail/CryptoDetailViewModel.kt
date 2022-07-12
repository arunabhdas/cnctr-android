package com.codesdk.cloudcnctr.presentation.ui.crypto_list.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesdk.cloudcnctr.common.Constants
import com.codesdk.cloudcnctr.common.Resource
import com.codesdk.cloudcnctr.domain.model.Crypto
import com.codesdk.cloudcnctr.domain.model.CryptoDetail
import com.codesdk.cloudcnctr.domain.use_case.get_crypto.GetCryptoUseCase
import com.codesdk.cloudcnctr.domain.use_case.get_cryptos.GetCryptosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Das on 2022-07-11.
 */
@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val getCryptoUseCase: GetCryptoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CryptoDetailState())
    val state: State<CryptoDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {coinId ->
            getCrypto(coinId)
        }
    }
    private fun getCrypto(coinId: String) {
        getCryptoUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoDetailState(crypto = result.data)
                }

                is Resource.Error -> {
                    _state.value = CryptoDetailState(
                        error = result.message ?: "Unexpected error"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CryptoDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class CryptoDetailState(
    val isLoading: Boolean = false,
    val crypto: CryptoDetail? = null,
    val error: String = ""
)