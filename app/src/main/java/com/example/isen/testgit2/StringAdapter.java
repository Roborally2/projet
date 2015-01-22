package com.example.isen.testgit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tanguy MERCIER on 22/01/2015.
 */
public class StringAdapter extends BaseAdapter{

    List<Integer> priorite;
    List<String> action;
    LayoutInflater inflater;


    public StringAdapter(Context context,List<String> action, List<Integer> priorite) {
        inflater = LayoutInflater.from(context);
        this.action = action;
        this.priorite=priorite;
    }

    // Fonction générée de base lorsque vous créez un adapter
    // Retourne la taille de l'ArrayList
    public int getCount() {
        return action.size();
    }

    // Fonction générée de base lorsque vous créez un adapter
    // Retourne la position (ligne en cours)
    public Object getItem(int position) {
        return action.get(position);
    }

    // Fonction générée de base lorsque vous créez un adapter
    // Retourne la position (ligne) d'un élément
    public long getItemId(int position) {
        return position;
    }


    /**
     * Classe dans laquelle vous déclarez les éléments
     * qui vont être présents sur une ligne;
     * (ici, éléments du fichier ligne_de_la_listview.xml)
     */
    private class ViewHolder {
        TextView textAction;
        TextView textPriorite;
    }

    // Fonction générée de base lorsque vous créez un adapter
    // Elle va lier la List<String> à la vue (ListView)
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            // On lie les éléments au fichier ligne_de_la_listview.xml
            convertView = inflater.inflate(R.layout.cardsitem, null);
            // On lie les deux TextView déclarés précédemment à ceux du xml
            holder.textAction = (TextView)convertView.findViewById(R.id.textViewAction);
            holder.textPriorite = (TextView)convertView.findViewById(R.id.textViewPriorite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // On défini ici le texte que doit contenir chacun des TextView
        // Le premier affichera l'action de la carte
        holder.textAction.setText(action.get(position));
        // Le second affichera l'élément
        holder.textPriorite.setText(priorite.get(position).toString());
        return convertView;
    }

}

