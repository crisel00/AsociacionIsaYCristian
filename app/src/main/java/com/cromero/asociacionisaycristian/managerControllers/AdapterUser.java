package com.cromero.asociacionisaycristian.managerControllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.managerViews.ProductsActivity;
import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.AdapterUserViewHolder> {
    private ArrayList<User> users;
    private Context context;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    //AdapterUser's constructor
    public AdapterUser(ArrayList<User> users) {
        this.users = users;

        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("User");

    }

    @NonNull
    @Override
    public AdapterUser.AdapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        //The view holder is created
        AdapterUser.AdapterUserViewHolder avh = new AdapterUser.AdapterUserViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.AdapterUserViewHolder holder, int position) {
        User userItem = users.get(position);
        String username = userItem.getUserName();
        String userEmail = userItem.getEmail();
        float userBalance = userItem.getBalance();

        //The store data are put into the layout
        holder.tv_username.setText(username);
        holder.tv_userEmail.setText(userEmail);
        holder.tv_userBalance.setText(String.valueOf(userBalance)+"€");

        //Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database initialization
                database = FirebaseDatabase.getInstance();
                dbReference = database.getReference().child("User").child(userItem.getUid());

                //When an item is pressed an option menu will be showed
                showDialog(v,userItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class AdapterUserViewHolder extends RecyclerView.ViewHolder {
        //Layout items
        private TextView tv_username, tv_userEmail, tv_userBalance;

        //ViewHolder constructor
        public AdapterUserViewHolder(View itemView) {
            super(itemView);

            //The context is given the correct value
            context = itemView.getContext();

            //Layout items initialization
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_userEmail = (TextView) itemView.findViewById(R.id.tv_userEmail);
            tv_userBalance = (TextView) itemView.findViewById(R.id.tv_userBalance);
        }
    }

    //OptionDialog creation method
    private void showDialog(View view,User user) {
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(user.getUserName());

        //Options creation
        CharSequence opciones[] = {view.getResources().getText(R.string.viewOrders),view.getResources().getText(R.string.setBalance)};
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent = new Intent(context, ProductsActivity.class);
                        intent.putExtra("user",user);
                        context.startActivity(intent);
                        break;
                    case 1:
                        grantBalanceDialog(view, user);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void grantBalanceDialog(View view, User user) {
        //Initialization
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(user.getEmail());
        alertDialog.setMessage(view.getResources().getText(R.string.setBalance));

        //Layout
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        //Accept
        alertDialog.setPositiveButton(view.getResources().getText(R.string.accept),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        float balance = Float.parseFloat(input.getText().toString());
                        float oldBalance= user.getBalance();
                        user.setBalance(oldBalance+balance);
                        dbReference.setValue(user);
                    }
                });
        //Cancel
        alertDialog.setNegativeButton(view.getResources().getText(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}