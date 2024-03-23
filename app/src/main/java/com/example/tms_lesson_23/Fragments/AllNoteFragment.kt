package com.example.tms_lesson_23.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tms_lesson_23.Adapter.NoteAdapter
import com.example.tms_lesson_23.ViewModel.AllNoteViewModel
import com.example.tms_lesson_23.ViewModel.MainViewModel
import com.example.tms_lesson_23.databinding.FragmentAllNoteBinding

class AllNoteFragment : Fragment() {

    private var _binding: FragmentAllNoteBinding? = null
    private val binding: FragmentAllNoteBinding
        get() = _binding ?: throw RuntimeException("Fragment is null")

    private val mainVM = MainViewModel()
    private val allNoteVM = AllNoteViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        actions()
        updateList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.recycler.adapter = myAdapter
        myAdapter.submitList(mainVM.noteList.value?.let { ArrayList(it) })
    }

    private fun actions() {
        allNoteVM.noteList.observe(viewLifecycleOwner) {
            allNoteVM.onListChanged()
        }
    }

    private fun updateList() {
        myAdapter.submitList(mainVM.noteList.value?.let { ArrayList(it) })
        myAdapter.notifyItemInserted(myAdapter.itemCount)
    }

    companion object {
        private val myAdapter = NoteAdapter()
    }
}