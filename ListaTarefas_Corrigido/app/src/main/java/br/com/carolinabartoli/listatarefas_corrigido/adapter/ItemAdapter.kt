package br.com.carolinabartoli.listatarefas_corrigido.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import br.com.carolinabartoli.listatarefas_corrigido.R
import br.com.carolinabartoli.listatarefas_corrigido.model.Tarefa

class ItemAdapter(private val dataset: List<Tarefa>,
                  private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {

    class ItemViewHolder(
        val view: View,
        val onItemClickListener: OnItemClickListener):RecyclerView.ViewHolder(view),
        View.OnClickListener{
        val tvItemNomeTarefa:TextView = view.findViewById(R.id.tvItemNomeTarefa)
        val tvItemOQue:TextView = view.findViewById(R.id.tvItemOQue)
        val tvItemAteQuando:TextView = view.findViewById(R.id.tvItemAteQuando)
        val btVerTarefa: Button = view.findViewById(R.id.btApagar)
        val llTarefas: LinearLayout = view.findViewById(R.id.llTarefas)

        init {
            llTarefas.setOnClickListener(this)
            btVerTarefa.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v!!.id == R.id.llTarefas) {
                onItemClickListener.onItemClick(adapterPosition, "VIEW")
            } else {
                onItemClickListener.onItemClick(adapterPosition, "DELETE")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tarefa = dataset[position]
        holder.tvItemNomeTarefa.text = tarefa.nome
        holder.tvItemOQue.text = tarefa.oQue
        holder.tvItemAteQuando.text = tarefa.ateQuando
        holder.btVerTarefa.setOnClickListener(holder)
    }

    //função utilizada pelo layoutManager para descobrir o tamanho da lista de dados (dataset)
    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(item: Int, acao: String)
    }

}