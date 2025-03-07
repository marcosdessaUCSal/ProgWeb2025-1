package model;

import enums.DisciplinaEnum;

public class AulaDto {

	public String id;
	public String disciplina;
	public String codDisciplina;
	public String assunto;
	public String duracao;
	public String data;// formato yyyy-mm-dd
	public String horario;

	public AulaDto() {
		this.id = "";
		this.disciplina = "";
		this.codDisciplina = "";
		this.assunto = "";
		this.duracao = "";
		this.data = "";
		this.horario = "";
	}

	public AulaDto(Aula aula) {
		this.id = Long.toString(aula.getId());
		this.disciplina = this.geraNomeDisc(aula.getCodDisciplina());
		this.codDisciplina = Integer.toString(DisciplinaEnum.getDiscByNome(this.disciplina).getCodigo());
		this.assunto = aula.getAssunto();
		this.duracao = Integer.toString(aula.getDuracao());
		this.data = aula.getData();
		this.horario = aula.getHorario();
	}

	private String geraNomeDisc(int cod) {
		return DisciplinaEnum.getDiscByCodigo(cod).getNome();
	}

	// retorna a data no formato dd/mm/yyyy
	public String getDataPadraoBr() {
		String resposta = "";
		String[] partes = this.data.split("-");
		if (partes.length == 3) {
			resposta = partes[2] + "/" + partes[1] + "/" + partes[0];
		}
		
		return resposta;
	}

	@Override
	public String toString() {
		return "AulaViewDTO [id=" + id + ", disciplina=" + disciplina + ", codDisciplina=" + codDisciplina
				+ ", assunto=" + assunto + ", duracao=" + duracao + ", data=" + data + ", horario=" + horario + "]";
	}

}