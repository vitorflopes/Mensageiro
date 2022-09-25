package com.example.mensageiro.ui.grupo.criarGrupos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentCriarGrupoBinding
import com.example.mensageiro.model.Grupo
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class CriarGrupoFragment : Fragment() {

    private var _binding: FragmentCriarGrupoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CriarGrupoViewModel

    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "TAGAdd"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCriarGrupoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(CriarGrupoViewModel::class.java)


        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }


        binding.btnCriarGrupoCriarGrupo.setOnClickListener {
            val nomeGrupo = binding.etNomeGrupoCriar.text.toString()
            val descricao = binding.etDescricaoGrupo.text.toString()
            val grupo = Grupo(null, null, nomeGrupo, descricao)
            viewModel.cadastrarGrupo(grupo)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(requireActivity())
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }

                val idGrupoCriado = viewModel.msg.value
                Toast.makeText(context, "Grupo criado.\n${idGrupoCriado}", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        return view
    }
}