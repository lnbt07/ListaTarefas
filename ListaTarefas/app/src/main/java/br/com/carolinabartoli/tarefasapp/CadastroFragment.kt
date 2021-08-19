package br.com.carolinabartoli.tarefasapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.carolinabartoli.tarefasapp.databinding.FragmentCadastroBinding

class CadastroFragment : Fragment() {

    private var binding: FragmentCadastroBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}