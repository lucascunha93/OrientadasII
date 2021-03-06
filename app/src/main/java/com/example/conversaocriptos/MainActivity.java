package com.example.conversaocriptos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WebClientTask mTask;
    private TextView mTvBtc;
    private TextView mTvEth;
    private TextView mTvLtc;
    private TextView mTvXrp;

    private TextView qtdValor;

    private Button btnBtc;
    private Button btnEth;
    private Button btnLtc;
    private Button btnXrp;

    private  double valor_total = 0;

    private ArrayList<Coin> cotacaoes;
    private  Coin  mBTC;
    private Coin  mETH;
   private Coin  mLTC ;
    private Coin  mXRP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebClient requisicao= new WebClient();
        startDownload();
        mTvBtc= findViewById(R.id.tv_value_btc);
        mTvLtc= findViewById(R.id.tv_value_ltc);
        mTvXrp= findViewById(R.id.tv_value_xrp);
        mTvEth= findViewById(R.id.tv_value_eth);

        qtdValor = findViewById(R.id.qtdBtc);

        btnBtc = findViewById(R.id.btnBtc);
        btnEth = findViewById(R.id.btnEth);
        btnLtc = findViewById(R.id.btnLtc);
        btnXrp = findViewById(R.id.btnXrp);

        btnBtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( qtdValor != null ){
                    String valor = qtdValor.getText().toString();
                    double qtd_btc = Double.parseDouble(valor);
                    valor_total = qtd_btc / mBTC.getBuy();

                    String valor_resposta = String.valueOf(valor_total);
                    Toast.makeText(getApplicationContext(), valor_resposta, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "0.00", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( qtdValor != null ){
                    String valor = qtdValor.getText().toString();
                    double qtd_btc = Double.parseDouble(valor);
                    valor_total = qtd_btc / mETH.getBuy();

                    String valor_resposta = String.valueOf(valor_total);
                    Toast.makeText(getApplicationContext(), valor_resposta, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "0.00", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( qtdValor != null ){
                    String valor = qtdValor.getText().toString();
                    double qtd_btc = Double.parseDouble(valor);
                    valor_total = qtd_btc / mLTC.getBuy();

                    String valor_resposta = String.valueOf(valor_total);
                    Toast.makeText(getApplicationContext(), valor_resposta, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "0.00", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnXrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( qtdValor != null ){
                    String valor = qtdValor.getText().toString();
                    double qtd_btc = Double.parseDouble(valor);
                    valor_total = qtd_btc / mXRP.getBuy();

                    String valor_resposta = String.valueOf(valor_total);
                    Toast.makeText(getApplicationContext(), valor_resposta, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "0.00", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    /*Não é necessario mexer aqui */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {

            Toast.makeText(this,"Loading Preços",Toast.LENGTH_LONG).show();
            startDownload();


        }
        return true;
    }


    public void startDownload() {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new WebClientTask();
            mTask.execute();
        }
    }

    class  WebClientTask extends AsyncTask<Void,Void, ArrayList<Coin>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Pronto...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected ArrayList<Coin> doInBackground(Void... strings) {
            ArrayList<Coin> coinsList=new ArrayList<>();
            mBTC = WebClient.getCoin("BTC");
            mETH = WebClient.getCoin("ETH");
            mLTC = WebClient.getCoin("LTC");
            mXRP = WebClient.getCoin("XRP");
            coinsList.add(mBTC);
            coinsList.add(mETH);
            coinsList.add(mLTC);
            coinsList.add(mXRP);
            cotacaoes=coinsList;
            Log.i("BTC",cotacaoes.get(0).getStringBuy());
            Log.i("ETH",cotacaoes.get(1).getStringBuy());
            Log.i("XRP",cotacaoes.get(2).getStringBuy());
            Log.i("LTC",cotacaoes.get(3).getStringBuy());
            return coinsList;
        }

        @Override
        protected void onPostExecute(ArrayList<Coin> coins) {
            super.onPostExecute(coins);
            //     showProgress(false);
            if (coins != null) {

                mTvBtc.setText(coins.get(0).getStringBuy());
                mTvEth.setText(coins.get(1).getStringBuy());
                mTvLtc.setText(coins.get(2).getStringBuy());
                mTvXrp.setText(coins.get(3).getStringBuy());
                Toast.makeText(getApplicationContext(), "Valores Atualizados", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }
        }
    }
}

