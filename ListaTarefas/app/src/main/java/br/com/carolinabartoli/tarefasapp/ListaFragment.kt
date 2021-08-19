package br.com.carolinabartoli.tarefasapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.com.carolinabartoli.tarefasapp.adapter.ItemAdapter
import br.com.carolinabartoli.tarefasapp.dao.TarefasDataSource
import br.com.carolinabartoli.tarefasapp.databinding.FragmentListaBinding
import br.com.carolinabartoli.tarefasapp.model.Tarefa


class ListaFragment : Fragment(), ItemAdapter.OnTarefaItemClickListener {

    private var binding: FragmentListaBinding? = null
    private var tarefas = listOf<Tarefa>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tarefas = TarefasDataSource().carregarTarefas()
        binding?.rvTarefas?.adapter = ItemAdapter(requireContext(), tarefas, this)

        binding?.tvQuantidade?.text = tarefas.size.toString()

        binding?.fapCadastrar?.setOnClickListener(){
            val action = ListaFragmentDirections.actionListaFragmentToCadastroFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onTarefaItemClick(tarefaItem: Int) {
        val tarefa = tarefas.get(tarefaItem)
        println(tarefa)

        val action = ListaFragmentDirections.actionListaFragmentToVisualizarFragment(nome = tarefa.nome, oQue = tarefa.oQue, ateQuando = tarefa.atéQuando, imagemId = tarefa.image)
        this.findNavController().navigate(action)
    }
}