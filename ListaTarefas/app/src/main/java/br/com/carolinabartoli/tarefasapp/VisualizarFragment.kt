package br.com.carolinabartoli.tarefasapp

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.carolinabartoli.tarefasapp.databinding.FragmentVisualizarBinding


class VisualizarFragment() : Fragment() {

    private var binding: FragmentVisualizarBinding?=null

    private var imagem: Int=0
    private var nome: String =""
    private var oQue: String =""
    private var ateQuando: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagem = it.getInt("imagemId")
            oQue = it.getString("oQue").toString()
            nome = it.getString("nome").toString()
            ateQuando = it.getString("ateQuando").toString()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVisualizarBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ivTarefa?.setImageResource(imagem)
        binding?.tvNomeTarefa?.text = nome
        binding?.tvAteQuando?.text = ateQuando
        binding?.tvOQue?.text = oQue

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}