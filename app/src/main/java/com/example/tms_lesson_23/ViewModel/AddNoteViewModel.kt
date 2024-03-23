package com.example.tms_lesson_23.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNoteViewModel : ViewModel() {

    private val _header: MutableLiveData<String> = MutableLiveData()
    val header : LiveData<String> = _header

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text : LiveData<String> = _text

    private val _isImportant :MutableLiveData<Boolean> = MutableLiveData(false)
    val isImportant : LiveData<Boolean> = _isImportant

    private val mainVM = MainViewModel()

    fun onHeaderChanged(header: String) {
        if (this._header.value != header) {
            this._header.value = header
        }
    }

    fun onTextChanged(text: String) {
        if (this._text.value != text) {
            this._text.value = text
        }
    }

    fun onImportantChanged(isImportant : Boolean) {
        if (this._isImportant.value != isImportant) {
            this._isImportant.value = isImportant
        }
    }

    fun onAddButtonClicked() {
        if (this._header.value.toString().trim().isNotEmpty() && this._text.value.toString().trim()
                .isNotEmpty()
        ) {
            _isImportant.value?.let { mainVM.addItem(_header.toString(), _text.toString(), it) }
        }
    }
}