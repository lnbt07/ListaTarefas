package br.com.carolinabartoli.listatarefas_corrigido

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import br.com.carolinabartoli.listatarefas_corrigido.databinding.FragmentVisualizarBinding
import br.com.carolinabartoli.listatarefas_corrigido.model.Tarefa
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class VisualizarFragment() : Fragment() {

    private var binding: FragmentVisualizarBinding?=null
    private lateinit var tarefaId: String
    private var database: DatabaseReference = Firebase.database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tarefaId = it.getString("id").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisualizarBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultar()

        binding?.btConcluir?.setOnClickListener() {
            val action =
                VisualizarFragmentDirections.actionVisualizarFragmentToCadastroFragment(
                    tarefaId
                )
            view.findNavController().navigate(action)
        }
    }

    private fun consultar() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataSnapshot = database.child("tarefas").child(tarefaId).get().await()
            val tarefa = dataSnapshot.getValue<Tarefa>()

            withContext(Dispatchers.Main){
                atualizarTela(tarefa!!)
            }
        }
    }

    private fun atualizarTela(tarefa: Tarefa) {
        binding?.tvNomeTarefa?.text = tarefa.nome
        binding?.tvOQue?.text = tarefa.oQue
        binding?.tvAteQuando?.text = tarefa.ateQuando
    }
}