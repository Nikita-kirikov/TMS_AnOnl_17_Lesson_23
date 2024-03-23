package com.example.tms_lesson_23.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms_lesson_23.Model.Note

class AllNoteViewModel : ViewModel() {
    private val mainVM = MainViewModel()

    private var _noteList = MutableLiveData<MutableList<Note>>()
    val noteList: LiveData<MutableList<Note>> = _noteList

    fun onListChanged() {
        _noteList = mainVM.noteList as MutableLiveData<MutableList<Note>>
    }
}