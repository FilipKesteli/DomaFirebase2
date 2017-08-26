package com.kesteli.filip.domafirebase2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Valemate on 25.8.2017..
 */

public class ClanAdapter extends RecyclerView.Adapter<ClanAdapter.MyViewHolder> {

    private Context mContext;
    private List<Clan> clanList;

    //Getting the firebase database
    private static FirebaseDatabase firebaseDatabase;
    //Firebase setup
    private DatabaseReference databaseReference;
//    private DatabaseReference childReference = databaseReference.child("condition");

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public EditText etIme, etPrezime, etGodine, etTehnoIskustvo, etIskustvo, etObrazovanje, etZnanje, etDani, etTehnoDani;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            etIme = (EditText) view.findViewById(R.id.etIme);
            etPrezime = (EditText) view.findViewById(R.id.etPrezime);
            etGodine = (EditText) view.findViewById(R.id.etGodine);
            etTehnoIskustvo = (EditText) view.findViewById(R.id.etTehnoIskustvo);
            etIskustvo = (EditText) view.findViewById(R.id.etIskustvo);
            etObrazovanje = (EditText) view.findViewById(R.id.etObrazovanje);
            etZnanje = (EditText) view.findViewById(R.id.etZnanje);
            etDani = (EditText) view.findViewById(R.id.etDani);
            etTehnoDani = (EditText) view.findViewById(R.id.etTehnoDani);

            title = (TextView) view.findViewById(R.id.title);
//            count = (TextView) view.findViewById(R.id.count);
//            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    //Konstruktor kojim punimo listu
    public ClanAdapter(Context mContext, List<Clan> clanList) {
        this.mContext = mContext;
        this.clanList = clanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_clan, parent, false);

        return new MyViewHolder(itemView);
    }

    SharedPreferences sharedpreferences;

    private String ime, prezime, godine, tehnoIskustvo, iskustvo, obrazovanje, znanje, dani, tehnoDani;

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        getDatabase();
        setupFirebase();

        //TODO Staviti sve podatke u firebase
//        String ime, prezime, godine, tehnoIskustvo, iskustvo, obrazovanje, znanje, dani, tehnoDani;
        Clan clan = clanList.get(position);
        ime = holder.etIme.getText().toString();
        prezime = holder.etPrezime.getText().toString();
        godine = holder.etGodine.getText().toString();
        tehnoIskustvo = holder.etTehnoIskustvo.getText().toString();
        iskustvo = holder.etIskustvo.getText().toString();
        obrazovanje = holder.etObrazovanje.getText().toString();
        znanje = holder.etZnanje.getText().toString();
        dani = holder.etZnanje.getText().toString();
        tehnoDani = holder.etTehnoDani.getText().toString();
//        holder.title.setText(clan.getName());
//        holder.count.setText(clan.getNumOfSongs() + " songs");

        Context applicationContext = ClanActivity.getContextOfApplication();
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String ime_tima = sharedpreferences.getString(POJO.KEY_IME_TIMA, "");

//        sharedpreferences = getSharedPreferences(POJO.KEY_MOJ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();

        try {
            DatabaseReference childNewTeam = databaseReference.child("Timovi").child(ime_tima).child("Clanovi").child("Clan" + ime + prezime);

            childNewTeam.child("Ime").setValue(ime);
            childNewTeam.child("Prezime").setValue(prezime);
            childNewTeam.child("Godine").setValue(godine);
            childNewTeam.child("Iskustvo u danoj tehnologiji").setValue(tehnoIskustvo);
            childNewTeam.child("Iskustvo opcenito").setValue(iskustvo);
            childNewTeam.child("Stupanj obrazovanja").setValue(obrazovanje);
            childNewTeam.child("Znanje u danoj tehnologiji").setValue(znanje);
            childNewTeam.child("Koliko dana covjek nije bio na poslu opcenito").setValue(dani);
            childNewTeam.child("Koliko dana covjek nije radio na danoj tehnologiji").setValue(tehnoDani);
//            childNewTeam.child("Koliko je clanova u vasem timu").setValue(etBrojClanova.getText().toString());
        } catch (DatabaseException e) {
            Log.d("baza clanova", "nije uspjelo");
        }



        // loading album cover using Glide library
//        Glide.with(mContext).load(clan.getThumbnail()).into(holder.thumbnail);

        //TODO: staviti listener na neki gumb na kartici
/*        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    public static FirebaseDatabase getDatabase() {
        //postavljamo moguce pisanje po android internal disku
        //podaci su spremljeni u cache (LOKALNO)
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        return firebaseDatabase;
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_clan, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
//        return clanList.size();
//        Log.d("broj_shared_2", "" + sharedpreferences.getInt(POJO.KEY_BROJ_CLANOVA, 0));
        return sharedpreferences.getInt(POJO.KEY_BROJ_CLANOVA, 2);
    }

    /*public void prepareClanovi() {
        Clan clan = new Clan(ime, prezime, godine, tehnoIskustvo, iskustvo, obrazovanje, znanje, dani, tehnoDani);
        clanList.add(clan);

        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);
    }*/
}






