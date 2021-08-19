package br.com.carolinabartoli.tarefasapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.carolinabartoli.tarefasapp.R
import br.com.carolinabartoli.tarefasapp.model.Tarefa

class ItemAdapter(private val context: Context, private val dataset: List<Tarefa>, private val onTarefaItemClickListener: OnTarefaItemClickListener):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view:View, val onTarefaItemClickListener: OnTarefaItemClickListener):RecyclerView.ViewHolder(view), View.OnClickListener{
        val ivItemTarefa:ImageView = view.findViewById(R.id.ivItemTarefa)
        val tvItemNomeTarefa:TextView = view.findViewById(R.id.tvItemNomeTarefa)
        val tvItemOQue:TextView = view.findViewById(R.id.tvItemOQue)
        val tvItemAteQuando:TextView = view.findViewById(R.id.tvItemAteQuando)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onTarefaItemClickListener.onTarefaItemClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout, onTarefaItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tarefa = dataset[position]
        holder.tvItemNomeTarefa.text = tarefa.nome
        holder.tvItemOQue.text = tarefa.oQue
        holder.tvItemAteQuando.text = tarefa.atéQuando
        holder.ivItemTarefa.setImageResource(tarefa.image)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    interface OnTarefaItemClickListener{
        fun onTarefaItemClick(tarefaItem:Int)
    }

}