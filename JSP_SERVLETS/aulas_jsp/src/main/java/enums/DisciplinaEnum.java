package enums;

public enum DisciplinaEnum {
	CALCULO(1, "CÁLCULO"),
	LOGICA(2, "LÓGICA"),
	GEOMETRIA(3, "GEOMETRIA"),
	FISICA(4, "FÍSICA"),
	COMPILADORES(5, "COMPILADORES");

	private int codigo;
	private String nome;

	DisciplinaEnum(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public static DisciplinaEnum getDiscByCodigo(int cod) {
		for (DisciplinaEnum d : DisciplinaEnum.values()) {
			if (cod == d.codigo) {
				return d;
			}
		}
		return null;
	}

	public static DisciplinaEnum getDiscByNome(String nome) {
		for (DisciplinaEnum d : DisciplinaEnum.values()) {
			if (d.getNome().equals(nome)) {
				return d;
			}
		}
		return null;
	}
}
