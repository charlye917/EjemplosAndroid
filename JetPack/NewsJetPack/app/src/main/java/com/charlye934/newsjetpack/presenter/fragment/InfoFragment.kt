package com.charlye934.newsjetpack.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.charlye934.newsjetpa.R
import com.charlye934.newsjetpa.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(article.url != ""){
                loadUrl(article.url)
            }
        }

    }

}