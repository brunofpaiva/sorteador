import java.util.ArrayList;

public class Time {

	private String mNomeTime;
	
	private ArrayList<Jogador> mJogadores;
	
	public Time(String nomeTime) {
		this.mNomeTime = nomeTime;
		mJogadores = new ArrayList<Jogador>();
	}
	
	public String getNomeTime() {
		return mNomeTime;
	}
	
	public boolean addJogador(Jogador jogador) {
		boolean adicionou = false;
		
		if (timeCompleto()) {
			adicionou = false;
		}
		
		if (jogador.getNivel() != Nivel.NORMAL) {
			if (temVaga(jogador.getNivel())) {
				mJogadores.add(jogador);
				adicionou = true;
			}
		} else {
			if (temVagaPraNormal()) {
				mJogadores.add(jogador);
				adicionou = true;
			}
		}
		
		return adicionou;
	}
	
	public boolean timeCompleto() {
		if (mJogadores.size() == 5) {
			return true;
		}
		
		return false;
	}
	
	private boolean temVaga(Nivel nivel) {
		for (Jogador jogador : mJogadores) {
			if (jogador.getNivel() == nivel) {
				return false;
			}
		}
		return true;
	}
	
	private boolean temVagaPraNormal() {
		int numVagas = 2;
		for (Jogador jogador : mJogadores) {
			if (jogador.getNivel() == Nivel.NORMAL) {
				numVagas--;
			}
		}
		
		if (numVagas > 0) {
			return true;
		}
		return false;
	}
	
	private Jogador getJogadorPorNivel(Nivel nivel) {
		if (nivel == Nivel.NORMAL) {
			for (Jogador jogador : mJogadores) {
				if (jogador.getNivel() == nivel) {
					return jogador;
				}
			}
		}
		
		for (Jogador jogador : mJogadores) {
			if (jogador.getNivel() == nivel) {
				return jogador;
			}
		}
		
		return new Jogador("JUCA", Nivel.CABECA);
	}
	
	private Jogador getSegundoNormal() {
		for (int i = mJogadores.size()-1 ; i >= 0; i--) {
			if (mJogadores.get(i).getNivel() == Nivel.NORMAL) {
				return mJogadores.get(i);
			}
		}
		return new Jogador("JOCA", Nivel.NORMAL);
	}
	
	public String toString() {
		String time = "";
		
		time = "Cabeca - " + getJogadorPorNivel(Nivel.CABECA).getNome() + "\n";
		time += "Aspira - " + getJogadorPorNivel(Nivel.ASPIRA).getNome() + "\n";
		time += "Normal - " + getJogadorPorNivel(Nivel.NORMAL).getNome() + "\n";
		time += "Normal - " + getSegundoNormal().getNome() + "\n";
		time += "Anti   - " + getJogadorPorNivel(Nivel.ANTI).getNome();
		
		return time;
	}
}
