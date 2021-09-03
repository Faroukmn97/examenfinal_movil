package com.example.examenfinalmovil.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenfinalmovil.R;
import com.example.examenfinalmovil.VolumenActivity;
import com.example.examenfinalmovil.models.JournalModel;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


@NonReusable
@Layout(R.layout.element_list_journal)
public class ListAdapterJ {

    @View(R.id.rvListjorunalx)
    PlaceHolderView mPlaceHolderView;
    @View(R.id.journalmageView)
    ImageView journalmageView;
    @View(R.id.journalmname)
    TextView journalmname;
    @View(R.id.journaldescription)
    TextView journaldescription;

    JournalModel journalModel = new JournalModel();
    private Context context;

    // metodo que se va a cargar cuando se de clic
    @Click(R.id.id_jornul)

    public void clicItem() {
        Intent intent = new Intent(context, VolumenActivity.class);

        intent.putExtra("journal_id", journalModel.getJournal_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }


    public ListAdapterJ(Context context, JournalModel revistas) {
        this.journalModel = revistas;
        this.context = context;
    }

    // agregar al adapter
    @Resolve
    public void onResolved() {
        this.journalmname.setText(journalModel.getName());
        this.journaldescription.setText(Html.fromHtml(journalModel.getDescription()));
        Glide.with(context).load(journalModel.getPortada()).into(this.journalmageView);
    }

}
