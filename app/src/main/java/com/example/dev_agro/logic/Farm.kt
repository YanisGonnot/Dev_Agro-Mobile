package com.example.dev_agro.logic

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_agro.network.repo.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmViewModel @Inject constructor(
    private val agentRepo: AgentRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _toast = MutableStateFlow<String?>(null)
    val toast: StateFlow<String?> = _toast

    /**
     * Reads the first URI, uploads it to IA, returns description via callback.
     */
    fun generateDescriptionFromFirstPhoto(
        context: Context,
        photoUris: List<Uri>,
        onResult: (String) -> Unit
    ) {
        val first = photoUris.firstOrNull() ?: run {
            _toast.value = "Ajoutez au moins une photo."
            return
        }

        viewModelScope.launch {
            _loading.value = true
            try {
                val cr = context.contentResolver
                val mime = cr.getType(first)           // e.g. image/jpeg
                    ?: "image/jpeg"                    // sensible default

                // Try to keep a proper extension
                val ext = when {
                    mime.endsWith("jpeg") || mime.endsWith("jpg") -> ".jpg"
                    mime.endsWith("png") -> ".png"
                    mime.endsWith("gif") -> ".gif"
                    mime.endsWith("webp") -> ".webp"
                    else -> ".jpg"
                }
                val name = (first.lastPathSegment ?: "photo") +
                        if ((first.lastPathSegment ?: "").contains('.')) "" else ext

                val bytes = cr.openInputStream(first)?.use { it.readBytes() }
                    ?: throw Exception("Impossible de lire l’image.")

                val desc = agentRepo.describeImage(bytes, filename = name, mimeType = mime)
                onResult(desc)
            } catch (e: Exception) {
                _toast.value = e.message ?: "Erreur IA."
            } finally {
                _loading.value = false
            }
        }
    }


    // Best-effort filename from Uri (optional)
    private fun getFileNameGuess(uri: Uri): String? = uri.lastPathSegment?.substringAfterLast('/')
}