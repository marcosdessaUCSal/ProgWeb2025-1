package com.tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.model.Rastreio;
import com.tracker.model.Registro;
import com.tracker.repository.RastreioRepository;
import com.tracker.repository.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class IniciaService {

	@Autowired
	private RastreioRepository rastreioRepository;

	@Autowired
	private RegistroRepository registroRepository;
	
	@Transactional
	public void populaDb() {
		Rastreio rastreio;
		List<Registro> regs;
		Integer i = 1;

		// rastreio ABC1234
		regs = new ArrayList<>();
		rastreio = new Rastreio();
		rastreio.setCodigo("ABC1234");
		rastreioRepository.save(rastreio);
		Registro reg;
		reg = new Registro(null, i++, "20/02/2024", "CAMPINAS", "DOCUMENTAÇÃO PRONTA");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "21/02/2024", "CAMPINAS", "PACOTE ENVIADO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "21/02/2024", "CAMPINAS", "CHEGOU À UNIDADE");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "21/02/2024", "CAMPINAS", "SAIU DA UNIDADE");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "23/02/2024", "FEIRA DE SANTANA", "CHEGOU À UNIDADE");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "24/02/2024", "FEIRA DE SANTANA", "SAIU DA UNIDADE");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "25/02/2024", "SALVADOR", "CHEGOU AO DESTINO FINAL");
		reg.setRastreio(rastreio);
		regs.add(reg);
		registroRepository.saveAll(regs);

		// rastreio ABC4321
		i = 1;
		regs = new ArrayList<>();
		rastreio = new Rastreio();
		rastreio.setCodigo("ABC4321");
		rastreioRepository.save(rastreio);
		reg = new Registro(null, i++, "10/02/2025", "MIAMI", "DADO RECEBIDO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "10/02/2025", "MIAMI", "DOCUMENTACAO COMPLETA");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "Receptáculo recebido em instalações de transporte dos EUA");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "RECEBIDO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "X-RAY SCAN");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "COLOCADO EM CONTAINER");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "PROCESADO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "12/02/2025", "MIAMI", "ENVIADO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "13/02/2025", "MIAMI", "O voo partiu");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "14/02/2025", "SÃO PAULO", "CHEGOU NO PAIS");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "14/02/2025", "SÃO PAULO", "Entrada aduaneira");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "14/02/2025", "SÃO PAULO", "ENVIO LIBERADO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "14/02/2025", "SÃO BERNARDO DO CAMPO", "ENTREGUE A TRANSPORTADORA LOCAL");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "17/02/2025", "SÃO BERNARDO DO CAMPO", "EM TRÂNSITO");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "21/02/2025", "SALVADOR", "OBJETO Recebido");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro( null,i++, "21/02/2025", "SALVADOR", "Preparada para a transferencia");
		reg.setRastreio(rastreio);
		regs.add(reg);
		reg = new Registro(null, i++, "21/02/2025", "SALVADOR", "OBJETO Recebido");
		reg.setRastreio(rastreio);
		regs.add(reg);
		
		registroRepository.saveAll(regs);
		

	}

}
