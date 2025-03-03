package utils;

import java.util.ArrayList;

import model.BaseDeDadosSingleton;
import model.Rastreio;
import model.Registro;

public class BdMock {
	
	public static BaseDeDadosSingleton db = BaseDeDadosSingleton.getInstance();
	
	
	public static void populaDb() {
		Rastreio rastreio;
		
		// rastreio ABC1234
		rastreio = new Rastreio("ABC1234");
		rastreio.addRegistro(new Registro("20/02/2024", "CAMPINAS", "DOCUMENTAÇÃO PRONTA"));
		rastreio.addRegistro(new Registro("21/02/2024", "CAMPINAS", "PACOTE ENVIADO"));
		rastreio.addRegistro(new Registro("21/02/2024", "CAMPINAS", "CHEGOU À UNIDADE"));
		rastreio.addRegistro(new Registro("21/02/2024", "CAMPINAS", "SAIU DA UNIDADE"));
		rastreio.addRegistro(new Registro("23/02/2024", "FEIRA DE SANTANA", "CHEGOU À UNIDADE"));
		rastreio.addRegistro(new Registro("24/02/2024", "FEIRA DE SANTANA", "SAIU DA UNIDADE"));
		rastreio.addRegistro(new Registro("25/02/2024", "SALVADOR", "CHEGOU AO DESTINO FINAL"));
		BaseDeDadosSingleton.getInstance().inserirRastreio(rastreio);
		
		// rastreio ABC4321
		rastreio = new Rastreio("ABC4321");
		rastreio.addRegistro(new Registro("10/02/2025", "MIAMI", "DADO RECEBIDO"));
		rastreio.addRegistro(new Registro("10/02/2025", "MIAMI", "DOCUMENTACAO COMPLETA"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "Receptáculo recebido em instalações de transporte dos EUA"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "RECEBIDO"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "X-RAY SCAN"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "COLOCADO EM CONTAINER"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "PROCESADO"));
		rastreio.addRegistro(new Registro("12/02/2025", "MIAMI", "ENVIADO"));
		rastreio.addRegistro(new Registro("13/02/2025", "MIAMI", "O voo partiu"));
		rastreio.addRegistro(new Registro("14/02/2025", "SÃO PAULO", "CHEGOU NO PAIS"));
		rastreio.addRegistro(new Registro("14/02/2025", "SÃO PAULO", "Entrada aduaneira"));
		rastreio.addRegistro(new Registro("14/02/2025", "SÃO PAULO", "ENVIO LIBERADO"));
		rastreio.addRegistro(new Registro("14/02/2025", "SÃO BERNARDO DO CAMPO", "ENTREGUE A TRANSPORTADORA LOCAL"));
		rastreio.addRegistro(new Registro("17/02/2025", "SÃO BERNARDO DO CAMPO", "EM TRÂNSITO"));
		rastreio.addRegistro(new Registro("21/02/2025", "SALVADOR", "OBJETO Recebido"));
		rastreio.addRegistro(new Registro("21/02/2025", "SALVADOR", "Preparada para a transferencia"));
		rastreio.addRegistro(new Registro("21/02/2025", "SALVADOR", "OBJETO Recebido"));
		BaseDeDadosSingleton.getInstance().inserirRastreio(rastreio);
		
	}

}
