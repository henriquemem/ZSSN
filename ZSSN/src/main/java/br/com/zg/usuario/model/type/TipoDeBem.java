package br.com.zg.usuario.model.type;

public enum TipoDeBem {
	AGUA(4), COMIDA(3), REMEDIO(2), MUNICAO(1);
	
	private int pontuacao;
	
	private TipoDeBem(int pontuacao){
		this.pontuacao = pontuacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}
	
}
