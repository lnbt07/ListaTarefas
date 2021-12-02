package br.com.carolinabartoli.listatarefas_corrigido


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.carolinabartoli.listatarefas_corrigido.databinding.FragmentCadastroBinding
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

class CadastroFragment : Fragment() {

    private var binding: FragmentCadastroBinding? = null
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
        binding = FragmentCadastroBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!tarefaId.equals("null")) consultar()

        binding?.btSalvar?.setOnClickListener() {
            salvar()
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

    private fun salvar() {
        CoroutineScope(Dispatchers.IO).launch {
            val id = if(!tarefaId.equals("null")) tarefaId else database.child("tarefa").push().key

            val tarefa = Tarefa(
                id = id!!,
                nome = binding?.etNomeTarefa?.text.toString(),
                oQue =  binding?.etOQue?.text.toString(),
                ateQuando = binding?.etAteQuando?.text.toString()
            )

            database.child("tarefas").child(id).setValue(tarefa).await()
        }
    }

    private fun atualizarTela(tarefa: Tarefa) {
        binding?.etNomeTarefa?.text?.append(tarefa.nome)
        binding?.etOQue?.text?.append(tarefa.oQue)
        binding?.etAteQuando?.text?.append(tarefa.ateQuando)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}