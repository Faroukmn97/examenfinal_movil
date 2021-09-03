package com.example.examenfinalmovil.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenfinalmovil.R;
import com.example.examenfinalmovil.VolumenActivity;
import com.example.examenfinalmovil.WebViewActivity;
import com.example.examenfinalmovil.models.JournalModel;
import com.example.examenfinalmovil.models.PublicationsModels;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


@NonReusable
@Layout(R.layout.elements_list_publications)
public class ListAdapterP {

    @View(R.id.rvListpublications)
    PlaceHolderView mPlaceHolderViewP;
    @View(R.id.publicationstittle)
    TextView publicationstittle;
    @View(R.id.publicationsauthors)
    TextView publicationsauthors;
    @View(R.id.publication_html)
    Button publication_html;
    @View(R.id.publicationsdoi)
    TextView publicationsdoi;

    PublicationsModels publicationsModels = new PublicationsModels();
    private Context context;

    // metodo que se va a cargar cuando se de clic
    @Click(R.id.id_publications)

    public void clicItem() {
        Intent intent = new Intent(context, VolumenActivity.class);

    /*    intent.putExtra("journal_id", publicationsModels.getJournal_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);  */
    }


    public ListAdapterP(Context context, PublicationsModels publicationsModels) {
        this.publicationsModels = publicationsModels;
        this.context = context;
    }

    // agregar al adapter
    @Resolve
    public void onResolved() {
        this.publicationstittle.setText(publicationsModels.getTitle());
       this.publicationsauthors.setText(publicationsModels.getAuthors());
       this.publicationsdoi.setText(publicationsModels.getDoi());
       this.publication_html.setOnClickListener(new android.view.View.OnClickListener(){
           @Override
           public void onClick(android.view.View v) {
             /*  String MY_URL = publicationsModels.getDoi();
               context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MY_URL))); */

           Intent intent = new Intent(context, WebViewActivity.class);
           intent.putExtra("doi", publicationsModels.getDoi());
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
           context.startActivity(intent);
    /*    intent.putExtra("journal_id", publicationsModels.getJournal_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);  */
           }
       });
        /* this.publicationstittle.setText(Html.fromHtml(journalModel.getDescription()));
        Glide.with(context).load(journalModel.getPortada()).into(this.journalmageView); */
    }

}
