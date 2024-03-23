package com.example.tms_lesson_23.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.tms_lesson_23.Adapter.SimpleTextWatcher
import com.example.tms_lesson_23.ViewModel.AddNoteViewModel
import com.example.tms_lesson_23.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("Fragment is null")
    private lateinit var button: Button
    private val noteVM = AddNoteViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = binding.save
        super.onViewCreated(view, savedInstanceState)
        actions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun actions() {
        button.setOnClickListener {
            noteVM.onAddButtonClicked()
            createNewNote()
            Toast.makeText(context, "Note has been added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNewNote() {
        with(binding) {
            header.addTextChangedListener(object : SimpleTextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    noteVM.onHeaderChanged(s.toString())
                }
            })

            text.addTextChangedListener(object : SimpleTextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    noteVM.onTextChanged(s.toString())
                }
            })

            if (!important.isChecked) {
                noteVM.onImportantChanged(false)
            } else {
                noteVM.onImportantChanged(true)
            }

            noteVM.header.observe(viewLifecycleOwner) {
                binding.header.setText(it)
            }

            noteVM.text.observe(viewLifecycleOwner) {
                binding.text.setText(it)
            }

            noteVM.isImportant.observe(viewLifecycleOwner) {
                binding.important.isChecked = it
            }

            header.text?.clear()
            text.text?.clear()
            important.isChecked = false
        }
    }
}