package com.clienteandroid;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ClienteAndroid - ultima modifica��o 28/04.
 * @author Andre N. Darcie , Alex Benevides.
 */

public class ClienteAndroid extends Activity implements OnClickListener {
	private Button btnConectar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnConectar = (Button) findViewById(R.id.btnConectar);
		btnConectar.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		if (view == btnConectar) { // Clicou no bot�o Conectar

			try {
				// Tenta iniciar uma conex�o com o Servidor de Socket
				ConnectionSocket connection = ConnectionSocket.createConnection("192.168.0.100","5000");
				connection.connect();

			} catch (Exception e) {
				// Mostra erro na tela
				Toast.makeText(this,
						"N�o foi poss�vel conectar->" + e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

		}

	}

	/**
	 * M�todo para recuperar ip do aparelho
	 */
	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}
}