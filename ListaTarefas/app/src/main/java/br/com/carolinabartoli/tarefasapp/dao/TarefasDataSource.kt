package br.com.carolinabartoli.tarefasapp.dao

import br.com.carolinabartoli.tarefasapp.R
import br.com.carolinabartoli.tarefasapp.model.Tarefa

class TarefasDataSource {

    fun carregarTarefas(): List<Tarefa>{
        val dados = listOf<Tarefa>(
            Tarefa("Louça", "Lavar a louça suja na pia da cozinha", "Hoje", R.drawable.louca) ,
            Tarefa("Varrer", "Varrer o pó da casa e jogar fora", "Sábado", R.drawable.varrer),
            Tarefa("Estender Roupa", "Estender a roupa no varal de chão", "Amanhã", R.drawable.roupa),
            Tarefa("Banheiro", "Lavar o banheiro e esfregar os azulejos", "Domingo", R.drawable.banheiro)
        )
        return dados;
    }
}