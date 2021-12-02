package br.com.carolinabartoli.listatarefas_corrigido


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.carolinabartoli.listatarefas_corrigido.adapter.ItemAdapter
import br.com.carolinabartoli.listatarefas_corrigido.databinding.FragmentListaBinding
import br.com.carolinabartoli.listatarefas_corrigido.model.Tarefa
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class ListaFragment : Fragment(), ItemAdapter.OnItemClickListener {

    private var binding: FragmentListaBinding? = null
    private var tarefas = mutableListOf<Tarefa>()
    private lateinit var  recyclerView: RecyclerView
    private var database: DatabaseReference = Firebase.database.reference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding!!.rvTarefas
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding?.fabCadastrar?.setOnClickListener(){
            val action = ListaFragmentDirections.actionListaFragmentToCadastroFragment(null)
            view.findNavController().navigate(action)
        }
        database.child("tarefas").addValueEventListener(tarefasListener)
    }

    override fun onItemClick(item: Int, acao: String) {
        val tarefa = tarefas.get(item)
        if(acao.equals("VIEW")) navegarVisualizar(tarefa) else apagarRegistro(tarefa)
    }

    private fun navegarVisualizar(tarefa: Tarefa) {
        val action =
            ListaFragmentDirections.actionListaFragmentToVisualizarFragment(tarefa.id)
        this.findNavController().navigate(action)
    }

    private fun apagarRegistro(tarefa: Tarefa) {
        CoroutineScope(Dispatchers.IO).launch {
            database.child("tarefas").child(tarefa.id).removeValue().await()
            withContext(Dispatchers.Main){
                atualizarTela()
            }
        }
    }

    private fun atualizarTela() {
        recyclerView.adapter = ItemAdapter(tarefas, this)
    }

    override fun onResume() {
        super.onResume()
        atualizarTela()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    val tarefasListener = object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            //val typeIndicator = object : GenericTypeIndicator<HashMap<String, Tarefa>>(){}
            val messages = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, Tarefa>>(){})
            tarefas = mutableListOf<Tarefa>()
            messages?.forEach {k, tarefa -> tarefas.add(tarefa)}
            atualizarTela()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w("ERRO_LEITURA",error.message)
        }

    }
}