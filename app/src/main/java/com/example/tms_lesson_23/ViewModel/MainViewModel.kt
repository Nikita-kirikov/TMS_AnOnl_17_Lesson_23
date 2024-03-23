package com.example.tms_lesson_23.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms_lesson_23.Model.Note

class MainViewModel : ViewModel() {

    private val _noteList = MutableLiveData<MutableList<Note>>()
    val noteList: LiveData<MutableList<Note>> = _noteList

    fun addItem(header: String, text: String, important: Boolean = false) {
        _noteList.value?.add(Note(header, text, important))
    }
}

